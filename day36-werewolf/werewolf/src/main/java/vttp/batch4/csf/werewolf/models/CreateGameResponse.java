package vttp.batch4.csf.werewolf.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record CreateGameResponse(String gameId, String message) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("gameId", gameId)
			.add("message", message)
			.build();
	}
}
