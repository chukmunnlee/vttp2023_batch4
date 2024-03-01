package vttp.batch4.csf.werewolf.messages;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public record JoinGameRequest(String gameId, String username) {

	public static JoinGameRequest toJoinGameRequest(String jsonStr) {
		JsonReader reader = Json.createReader(new StringReader(jsonStr));
		JsonObject json = reader.readObject();
		return new JoinGameRequest(
				json.getString(F_GAMEID, "abcd1234"), 
				json.getString(F_USERNAME, "fredflintstone"));
	}
}
