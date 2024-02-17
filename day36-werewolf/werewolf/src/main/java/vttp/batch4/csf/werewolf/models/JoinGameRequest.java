package vttp.batch4.csf.werewolf.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public record JoinGameRequest(String gameId, String username) {

	public static JoinGameRequest toJoinGameRequest(String jsonStr) {
		JsonReader reader = Json.createReader(new StringReader(jsonStr));
		JsonObject json = reader.readObject();
		return new JoinGameRequest(
				json.getString("gameId", "abcd1234"), 
				json.getString("username", "fredflintstone"));
	}
}
