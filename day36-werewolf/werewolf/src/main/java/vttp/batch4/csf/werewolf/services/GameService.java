package vttp.batch4.csf.werewolf.services;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import vttp.batch4.csf.werewolf.messages.JoinGameRequest;
import vttp.batch4.csf.werewolf.messages.StartGameRequest;
import vttp.batch4.csf.werewolf.models.Game;
import vttp.batch4.csf.werewolf.models.Player;
import vttp.batch4.csf.werewolf.respositories.GameRepository;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

@Service
public class GameService {

	public static final String[] NAMES = { 
		"barney", "wilma", "betty", "bambam", "pebbles", "dino",
		"bender", "leela", "fry", "amy", "zoidberg", "zapp", "kif", "hermes", "professor",
		"holmer", "bart", "lisa", "marge", "maggie", "krusty", "milhouse", "apu"
	};

	// Not configurable
	public static final int MAX_PLAYERS = 15;

	@Value("${game.instance.max}")
	private int gameInstanceMax;

	@Value("${game.instance.max}")
	private double gameInstanceDurationHours;

	private long maxDuration;

	@Autowired
	private GameRepository gameRepo;

	private List<String> names = new LinkedList<>();

	@PostConstruct
	public void init() {
		maxDuration = Math.round(gameInstanceDurationHours * (1000 * 60 * 60));
		for (String n: NAMES)
			names.add(n);

		// Shuffle twice
		final Random rand = new SecureRandom();
		Collections.shuffle(names, rand);
		Collections.shuffle(names, rand);
	}

	public boolean exceededGameInstanceLimit() {
		final long fromTime = (new Date()).getTime() - maxDuration;
		gameRepo.deleteGamesBeforeTimestamp(fromTime);
		return gameRepo.getGamesAfterTimestamp(fromTime) >= gameInstanceMax;
	}

	public int getPlayerCountByGameId(String gameId) {
		Optional<Game> gameOpt = gameRepo.getGameByGameId(gameId);
		if (gameOpt.isEmpty())
			return 0;
		Game game = gameOpt.get();
		return game.getPlayers().size();
	}

	public String createGame() {
		final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		final String gameId = uuid.substring(0, 8);
		final String secret = uuid.substring(8);
		gameRepo.createGame(gameId, secret);
		return "%s,%s".formatted(gameId, secret);
	}

	public String joinGame(JoinGameRequest req) {
		Optional<Game> gameOpt = gameRepo.getGameByGameId(req.gameId());
		if (gameOpt.isEmpty())
			return "Cannot find game %s".formatted(req.gameId());

		Game game = gameOpt.get();
		if (game.isStarted()) 
			return "Cannot join. The game has started";

		if (game.getPlayers().size() >= MAX_PLAYERS)
			return "There are 15 players in this game. You cannot join the game";

		Optional<Player> playerOpt= game.getPlayers().stream()
			.filter(player -> req.username().trim().toLowerCase().equals(player.getUsername()))
			.findFirst();
		if (playerOpt.isPresent())
			return "The name %s has been taken".formatted(req.username());

		final String secret = UUID.randomUUID().toString().substring(0, 8);
		Player player = new Player();
		// Normalize it before saving
		player.setUsername(req.username().trim().toLowerCase());
		player.setSecret(secret);
		gameRepo.addPlayerToGame(req.gameId(), player);

		return "%s%s".formatted(PREFIX_SECRET, secret);
	}

	public Optional<String> startGame(StartGameRequest req) {
		Optional<Game> gameOpt = gameRepo.getGameByGameId(req.gameId());
		if (gameOpt.isEmpty())
			return Optional.of("Cannot find game %s".formatted(req.gameId()));

		Game game = gameOpt.get();
		if (req.moderator()) {
			if (game.isStarted()) 
				return Optional.of("The game has started. Cannot restart game");

			int playerCount = game.getPlayers().size();
			if (playerCount < 7) 
				return Optional.of("Less than 7 players. Cannot restart game");

			if (!game.getSecret().equals(req.secret()))
				return Optional.of("You are not the moderator. Cannot start game");

			// Assign roles to players
			List<Roles> roles = randomizeRoles(playerCount);
			boolean result = gameRepo.assignRoles(req.gameId(), req.secret(), roles);
			
			// start game
			gameRepo.startGame(game.getGameId(), req.secret(), true);
			return Optional.empty();
		}

		if (!game.isStarted())
			return Optional.of("Game has not start. Please wait for game to start");

		List<Player> player = game.getPlayers().stream()
			.filter(p -> p.getUsername().equals(req.name()) && p.getSecret().equals(req.secret()))
			.limit(1)
			.toList();

		if (player.size() <= 0)
			return Optional.of("Cannot find player %s in the game".formatted(req.name()));

		// TODO: Get role and return 
		String role = "%s%s".formatted(PREFIX_ROLE, player.get(0).getRole());

		return Optional.of(role);
	}

	public List<Player> getPlayers(String gameId, String secret) {
		Optional<Game> opt = gameRepo.getGameByGameId(gameId);
		if (opt.isEmpty())
			return List.of();

		Game game = opt.get();
		boolean role = game.getSecret().equals(secret);

		return game.getPlayers().stream()
				.map(player -> {
					Player p = new Player();
					p.setUsername(player.getUsername());
					p.setDead(role? player.isDead(): false);
					p.setRole(role? player.getRole(): "redacted");
					return p;
				})
				.toList();
	}

	public boolean deleteGame(String gameId, String secret) {
		return gameRepo.deleteGameByGameId(gameId, secret);
	}

	public boolean leaveGame(String gameId, String username, String secret) {
		return gameRepo.deletePlayerFromGame(gameId, username, secret);
	}

	// For testing
	public List<String> injectPlayers(String gameId, int count) {
		count = count > NAMES.length? MAX_PLAYERS: count;
		return names.stream().limit(count)
			.map(name -> {
				Player player = new Player();
				player.setUsername(name);
				player.setSecret("abcd1234");
				return player;
			})
			.filter(player -> gameRepo.addPlayerToGame(gameId, player))
			.map(player -> player.getUsername())
			.toList();
	}


	private List<Roles> randomizeRoles(int playerCount) {
		List<Roles> roles = new LinkedList<>();
		roles.add(Roles.Doctor);
		roles.add(Roles.Seer);
		roles.add(Roles.Werewolf);
		roles.add(Roles.Werewolf);
		for (int i = 0; i < (playerCount - 4); i++)
			roles.add(Roles.Villager);
		Collections.shuffle(roles);
		Collections.shuffle(roles);
		return roles;
	}
}
