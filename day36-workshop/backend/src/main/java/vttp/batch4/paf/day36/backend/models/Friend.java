package vttp.batch4.paf.day36.backend.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public record Friend(String email, String name, String phone) {

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("email", email())
			.add("name", name())
			.add("phone", phone())
			.build();
	}

	public static Friend toFriend(String str) {
		JsonReader reader = Json.createReader(new StringReader(str));
		JsonObject json = reader.readObject();
		return new Friend(json.getString("email"), json.getString("name")
				, json.getString("phone"));
	}
}
    
