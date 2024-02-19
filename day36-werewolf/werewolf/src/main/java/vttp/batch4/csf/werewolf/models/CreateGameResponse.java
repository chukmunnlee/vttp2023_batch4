package vttp.batch4.csf.werewolf.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public record CreateGameResponse(String gameId, String secret, String message) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add(F_GAMEID, gameId)
			.add(F_SECRET, secret)
			.add(F_MESSAGE, message)
			.build();
	}
}
