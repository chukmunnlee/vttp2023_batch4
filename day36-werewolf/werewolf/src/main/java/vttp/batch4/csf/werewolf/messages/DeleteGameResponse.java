package vttp.batch4.csf.werewolf.messages;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public record DeleteGameResponse(boolean success, String message) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add(F_SUCCECSS, success)
			.add(F_MESSAGE, message)
			.build();
	}
}
