package vttp.batch4.csf.werewolf.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class GameService {

	public String createGame() {
		final String gameId = UUID.randomUUID().toString().substring(0, 8);
		return gameId;
	}

	public boolean deleteGame(String gameId) {
		return true;
	}
}
