package vttp2024.batch4.csf.backend.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record Game(int gameId, String name, String url, String image) { 
	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("gameId", gameId())
			.add("name", name())
			.add("url", url())
			.add("image", image())
			.build();
	}
}
