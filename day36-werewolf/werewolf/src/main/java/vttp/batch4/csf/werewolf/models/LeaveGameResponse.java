package vttp.batch4.csf.werewolf.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public record LeaveGameResponse(String gameId, String message) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add(F_GAMEID, gameId)
			.add(F_MESSAGE, message)
			.build();
	}
}

