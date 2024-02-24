package vttp.batch4.csf.werewolf.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public record StartGameResponse(boolean success, String message, String gameId, String name, String role) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add(F_SUCCECSS, success)
			.add(F_MESSAGE, message)
			.add(F_GAMEID, gameId)
			.add(F_USERNAME, name)
			.add(F_ROLE, role)
			.build();
	}
}
