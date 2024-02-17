package vttp.batch4.csf.werewolf.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record DeleteGameResponse(boolean success, String message) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("success", success)
			.add("message", message)
			.build();
	}
}
