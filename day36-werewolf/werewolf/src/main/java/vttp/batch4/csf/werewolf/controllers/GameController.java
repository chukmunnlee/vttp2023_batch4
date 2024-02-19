package vttp.batch4.csf.werewolf.controllers;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.batch4.csf.werewolf.models.CreateGameResponse;
import vttp.batch4.csf.werewolf.models.DeleteGameResponse;
import vttp.batch4.csf.werewolf.models.JoinGameRequest;
import vttp.batch4.csf.werewolf.models.JoinGameResponse;
import vttp.batch4.csf.werewolf.models.Player;
import vttp.batch4.csf.werewolf.services.GameService;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

	public static final String X_SECRET = "X-SECRET";

	private Logger logger = Logger.getLogger(GameController.class.getName());

	@Autowired
	private GameService gameSvc;

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

	@DeleteMapping(path="/game/{gameId}")
	@ResponseBody
	public ResponseEntity<String> deleteGame(@PathVariable String gameId
			, @RequestHeader(X_SECRET) String secret) {
		final boolean result = gameSvc.deleteGame(gameId, secret);
		final DeleteGameResponse resp = new DeleteGameResponse(result
				, "Delete game %s status: %b".formatted(gameId, result));
		logger.info("Deleting  game: gameId=%s, result=%b".formatted(gameId, result));
		return ResponseEntity.ok(resp.toJson().toString());
	}

	private String getGameId(String createGame) {
		return createGame.split(",")[0];
	}
	private String getSecret(String createGame) {
		return createGame.split(",")[1];
	}
}


