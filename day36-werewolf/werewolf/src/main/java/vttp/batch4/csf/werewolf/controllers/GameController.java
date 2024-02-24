package vttp.batch4.csf.werewolf.controllers;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.batch4.csf.werewolf.models.CreateGameResponse;
import vttp.batch4.csf.werewolf.models.DeleteGameResponse;
import vttp.batch4.csf.werewolf.models.GameDetailResponse;
import vttp.batch4.csf.werewolf.models.JoinGameRequest;
import vttp.batch4.csf.werewolf.models.JoinGameResponse;
import vttp.batch4.csf.werewolf.models.StartGameRequest;
import vttp.batch4.csf.werewolf.models.StartGameResponse;
import vttp.batch4.csf.werewolf.services.GameService;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

	public static final String X_SECRET = "X-SECRET";

	private Logger logger = Logger.getLogger(GameController.class.getName());

	@Autowired
	private GameService gameSvc;

	// Create a new game
	@PostMapping(path="/game", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postGame() {
		CreateGameResponse resp; 
		if (gameSvc.exceededGameInstanceLimit()) {
			resp = new CreateGameResponse("invalid", "invalid",
					"""
						Cannot create any new game.
						Exceeded the number of running games.
					"""
			);
			return ResponseEntity.status(409).body(resp.toJson().toString());
		} 

		final String createGameResp = gameSvc.createGame();
		final String gameId = getGameId(createGameResp);
		final String secret = getSecret(createGameResp);

		resp = new CreateGameResponse(gameId, secret, "Success");
		logger.info("Creating new game: gameId=%s".formatted(gameId));
		return ResponseEntity.status(201).body(resp.toJson().toString());
	}

	// Join an existing game
	@PostMapping(path="/game/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postGameGameId(@PathVariable String gameId,
			@RequestBody String payload) {
		JoinGameRequest req = JoinGameRequest.toJoinGameRequest(payload);
		JoinGameResponse resp;

		Optional<String> opt = gameSvc.joinGame(req);
		if (opt.isPresent()) {
			resp = new JoinGameResponse(gameId, opt.get());
			return ResponseEntity.status(400).body(resp.toJson().toString());
		}

		resp = new JoinGameResponse(gameId, "Joined game %s as %s".formatted(gameId, req.username()));
		return ResponseEntity.status(201).body(resp.toJson().toString());
	}

	@PostMapping(path="/game/{gameId}/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postStartGame(@PathVariable String gameId
			, @PathVariable String name, @RequestBody String payload
			, @RequestHeader(name=X_SECRET, defaultValue="abcd1234") String secret) {

		StartGameRequest req = StartGameRequest.toStartGameRequest(payload);
		if (req.moderator())
			req = req.updateStartGameRequest(secret);

		StartGameResponse resp;

		Optional<String> opt = gameSvc.startGame(req);
		if (req.moderator()) {
			if (!opt.isEmpty()) {
				resp = new StartGameResponse(false, opt.get(), gameId, "moderator", "");
				return ResponseEntity.status(400).body(resp.toJson().toString());
			}

			resp = new StartGameResponse(true, "", gameId, "moderator", "");
			return ResponseEntity.ok(resp.toJson().toString());
		}

		String role = opt.get();
		if (!role.startsWith(PREFIX_ROLE)) {
			resp = new StartGameResponse(false, opt.get(), gameId, name, "");
			return ResponseEntity.status(400).body(resp.toJson().toString());
		}

		role = role.substring(PREFIX_ROLE.length());
		resp = new StartGameResponse(true, "", gameId, name, role);

		return ResponseEntity.ok(resp.toJson().toString());
	}

	// Delete a game
	@DeleteMapping(path="/game/{gameId}")
	@ResponseBody
	public ResponseEntity<String> deleteGame(@PathVariable String gameId
			, @RequestHeader(name=X_SECRET, defaultValue="abcd1234") String secret) {
		final boolean result = gameSvc.deleteGame(gameId, secret);
		final DeleteGameResponse resp = new DeleteGameResponse(result
				, "Delete game %s status: %b".formatted(gameId, result));
		logger.info("Deleting  game: gameId=%s, result=%b".formatted(gameId, result));
		return ResponseEntity.ok(resp.toJson().toString());
	}

	// Get player count
	@GetMapping(path="/game/{gameId}/players/count")
	@ResponseBody
	public ResponseEntity<String> getPlayersCount(@PathVariable String gameId) {
		final int count = gameSvc.getPlayerCountByGameId(gameId);
		GameDetailResponse resp = new GameDetailResponse();
		resp.setGameId(gameId);
		resp.setPlayerCount(count);
		return ResponseEntity.ok("");
	}

	private String getGameId(String createGame) {
		return createGame.split(",")[0];
	}
	private String getSecret(String createGame) {
		return createGame.split(",")[1];
	}
}


