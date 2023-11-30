package vttp.ssf.day16.controllers;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path="/time", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeRestController {

   @GetMapping
   public ResponseEntity<String> getTimeAsJSON() {
      JsonObject resp = Json.createObjectBuilder()
            .add("time", (new Date()).toString())
            .build();
      return ResponseEntity.ok(resp.toString());
   }

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> postTimeAsJSON(@RequestBody String payload) {
      JsonReader jsonReader = Json.createReader(new StringReader(payload));
      JsonObject data = jsonReader.readObject();
      System.out.printf(">>> data = %s\n", data.toString());

      JsonObject resp = Json.createObjectBuilder()
            .add("status", "modifed")
            .add("adjustment", data.getInt("adjustment"))
            .build();

      return ResponseEntity.status(201)
            .header("my header", "fred")
            .body(resp.toString());
   }
   
}
