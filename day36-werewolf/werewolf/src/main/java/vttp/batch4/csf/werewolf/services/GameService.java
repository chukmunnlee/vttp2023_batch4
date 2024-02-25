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
import vttp.batch4.csf.werewolf.models.Game;
import vttp.batch4.csf.werewolf.models.JoinGameRequest;
import vttp.batch4.csf.werewolf.models.Player;
import vttp.batch4.csf.werewolf.models.StartGameRequest;
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

	public List<String> injectPlayers(String gameId, int count) {
		count = count > NAMES.length? MAX_PLAYERS: count;
		return names.stream().limit(count)
			.map(name -> {
				Player player = new Player();
				player.setUsername(name);
				return player;
			})
			.filter(player -> gameRepo.addPlayerToGame(gameId, player))
			.map(player -> player.getUsername())
			.toList();
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

	public Optional<String> joinGame(JoinGameRequest req) {
		Optional<Game> gameOpt = gameRepo.getGameByGameId(req.gameId());
		if (gameOpt.isEmpty())
			return Optional.of("Cannot find game %s".formatted(req.gameId()));

		Game game = gameOpt.get();
		if (game.isStarted()) 
			return Optional.of("Cannot join. The game has started");

		if (game.getPlayers().size() >= MAX_PLAYERS)
			return Optional.of("There are 15 players in this game. You cannot join the game");

		Optional<Player> playerOpt= game.getPlayers().stream()
			.filter(player -> req.username().trim().toLowerCase().equals(player.getUsername()))
			.findFirst();
		if (playerOpt.isPresent())
			return Optional.of("The name %s has been taken".formatted(req.username()));

		Player player = new Player();
		// Normalize it before saving
		player.setUsername(req.username().trim().toLowerCase());
		gameRepo.addPlayerToGame(req.gameId(), player);

		return Optional.empty();
	}

	public Optional<String> startGame(StartGameRequest req) {
		Optional<Game> gameOpt = gameRepo.getGameByGameId(req.gameId());
		if (gameOpt.isEmpty())
			return Optional.of("Cannot find game %s".formatted(req.gameId()));

		Game game = gameOpt.get();
		if (req.moderator()) {
			if (game.isStarted()) 
				return Optional.of("The game has started. Cannot restart game");

			//if (game.getPlayers().size() < 7) 
			//	return Optional.of("Less than 7 players. Cannot restart game");
			
			// start game
			// TODO: Assign roles to players
			gameRepo.startGame(game.getGameId(), true);
			return Optional.empty();
		}

		if (!game.isStarted())
			return Optional.of("Game has not start. Please wait for game to start");

		// TODO: Get role and return 
		String role = "%s%s".formatted(PREFIX_ROLE, "some role");

		return Optional.of(role);
	}

	public boolean deleteGame(String gameId, String secret) {
		return gameRepo.deleteGameByGameId(gameId, secret);
	}

	public boolean leaveGame(String gameId, String username) {
		return gameRepo.deletePlayerFromGame(gameId, username);
	}
}
