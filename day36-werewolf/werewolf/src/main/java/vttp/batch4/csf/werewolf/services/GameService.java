package vttp.batch4.csf.werewolf.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.azam.ulidj.ULID;
import jakarta.annotation.PostConstruct;
import vttp.batch4.csf.werewolf.respositories.GameRepository;

@Service
public class GameService {

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
		final String gameId = UUID.randomUUID().toString().substring(0, 8);
		final String secret = ULID.random();
		gameRepo.createGame(gameId, secret);
		return "%s,%s".formatted(gameId, secret);
	}

	public boolean deleteGame(String gameId, String secret) {
		return gameRepo.deleteGameByGameId(gameId, secret);
	}
}
