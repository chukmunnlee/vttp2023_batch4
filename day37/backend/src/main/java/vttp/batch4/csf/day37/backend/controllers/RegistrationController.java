package vttp.batch4.csf.day37.backend.controllers;

import java.io.StringReader;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Controller
@RequestMapping(path="/api")
public class RegistrationController {

	@PostMapping(path="/user")
	@ResponseBody
	public ResponseEntity<String> postUser(@RequestBody String payload) {
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject json = reader.readObject();

		System.out.printf(">>> user: %s\n", json.toString());

		return ResponseEntity.ok("{}");
	}
}

