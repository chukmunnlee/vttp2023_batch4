package vttp.batch4.csf.werewolf.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.batch4.csf.werewolf.models.CreateGameResponse;
import vttp.batch4.csf.werewolf.models.DeleteGameResponse;
import vttp.batch4.csf.werewolf.models.JoinGameRequest;
import vttp.batch4.csf.werewolf.services.GameService;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

	private Logger logger = Logger.getLogger(GameController.class.getName());

	@Autowired
	private GameService gameSvc;

	@PostMapping(path="/game", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postGame() {
		final String gameId = gameSvc.createGame();
		logger.info("Creating new game: gameId=%s".formatted(gameId));
		final CreateGameResponse resp = new CreateGameResponse(gameId, "Success");
		return ResponseEntity.ok(resp.toJson().toString());
	}

	@PostMapping(path="/game/{gameId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postGameGameId(@PathVariable String gameId,
			@RequestBody String payload) {
		JoinGameRequest req = JoinGameRequest.toJoinGameRequest(payload);
		return null;
	}

	@DeleteMapping(path="/game/{gameId}")
	@ResponseBody
	public ResponseEntity<String> deleteGame(@PathVariable String gameId) {
		final boolean result = gameSvc.deleteGame(gameId);
		final DeleteGameResponse resp = new DeleteGameResponse(result
				, "Delete game %s status: %b".formatted(gameId, result));
		logger.info("Deleting  game: gameId=%s, result=%b".formatted(gameId, result));
		return ResponseEntity.ok(resp.toJson().toString());
	}
}


