package vttp.batch4.csf.werewolf.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public record StartGameRequest(String gameId, String name, boolean moderator, String secret) {

	public StartGameRequest updateStartGameRequest(String secret) {
		return new StartGameRequest(gameId, name, moderator, secret);
	}

	public static StartGameRequest toStartGameRequest(String jsonStr) {
		JsonReader reader = Json.createReader(new StringReader(jsonStr));
		JsonObject json = reader.readObject();
		return new StartGameRequest(
				json.getString(F_GAMEID, "abcd1234"), 
				json.getString(F_USERNAME, "fredflintstone"),
				json.getBoolean(F_MODERATOR, false),
				"");
	}
}
