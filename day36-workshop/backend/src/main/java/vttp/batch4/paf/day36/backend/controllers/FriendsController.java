package vttp.batch4.paf.day36.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch4.paf.day36.backend.models.Friend;
import vttp.batch4.paf.day36.backend.services.FriendsService;

@Controller
@CrossOrigin
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendsController {

	@Autowired
	private FriendsService friendsSvc;

	@PostMapping(path="/friend", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postFriend(@RequestBody String payload) {

		System.out.printf(">>> POST payload: %s\n", payload);

		Friend f = Friend.toFriend(payload);

		if (friendsSvc.add(f)) {
			JsonObject resp = Json.createObjectBuilder()
					.add("status", 200)
					.build();

			// 200 -> then()
			return ResponseEntity.ok(resp.toString());
		}

		JsonObject err = Json.createObjectBuilder()
				.add("message", "Cannot add friend")
				.add("status", 400)
				.build();

		// 400 -> catch()
		return ResponseEntity.status(400).body(err.toString());
	}

	@GetMapping(path="/friends")
	@ResponseBody
	public ResponseEntity<String> getFriends() {

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

		friendsSvc.getFriends().stream()
				.map(Friend::toJson)
				.forEach(arrBuilder::add);

		return ResponseEntity.ok(arrBuilder.build().toString());
	}

}
