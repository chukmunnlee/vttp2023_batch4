package vttp.batch4.csf.werewolf.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import vttp.batch4.csf.werewolf.models.Game;
import vttp.batch4.csf.werewolf.models.JoinGameRequest;
import vttp.batch4.csf.werewolf.models.Player;
import vttp.batch4.csf.werewolf.respositories.GameRepository;

@Service
public class GameService {

	// Not configurable
	public static final int MAX_PLAYERS = 15;

	@Value("${game.instance.max}")
	private int gameInstanceMax;

	@Value("${game.instance.max}")
	private double gameInstanceDurationHours;

	private long maxDuration;

	@Autowired
	private GameRepository gameRepo;

	@PostConstruct
	public void init() {
		maxDuration = Math.round(gameInstanceDurationHours * (1000 * 60 * 60));
	}

	public boolean exceededGameInstanceLimit() {
		final long fromTime = (new Date()).getTime() - maxDuration;
		return gameRepo.getGamesAfterTimestamp(fromTime) >= gameInstanceMax;
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
		if (game.getPlayers().size() >= MAX_PLAYERS)
			return Optional.of("There are 15 players in this game. You cannot join");

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

	public boolean deleteGame(String gameId, String secret) {
		return gameRepo.deleteGameByGameId(gameId, secret);
	}
}
