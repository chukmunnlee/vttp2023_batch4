package vttp.ssf.day16;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@SpringBootApplication
public class Day16Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Day16Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Get a JsonObjectBuilder and build the JSON builder
		// JsonObjectBuilder builder = Json.createObjectBuilder();
		// builder = builder.add("firstName", "fred");
		// builder = builder.add("lastName", "flintstone");
		// builder = builder.add("age", 50);
		// builder = builder.add("married", true);
		// builder = builder.add("height", 5.9);

		// // Create the JSON object
		// JsonObject fred = builder.build();

		JsonObject fred = Json.createObjectBuilder()
				.add("firstName", "fred")
				.add("lastName", "flintstone")
				.add("age", 50)
				.add("married", true)
				.add("height", 5.9)
				.build();

		JsonObject wilma = Json.createObjectBuilder()
				.add("firstName", "wilma")
				.add("lastName", "flintstone")
				.add("age", 50)
				.add("married", true)
				.add("height", 5.7)
				.add("spouse", fred)
				.build();

		JsonArray flintstones = Json.createArrayBuilder()
				.add(fred)
				.add(wilma)
				.build();

		System.out.printf(">>> wilma:\n %s\n", wilma.toString());
		System.out.println("------------------------------\n");
		System.out.printf(">>> flintstones:\n %s\n", flintstones.toString());

		String name = wilma.getString("firstName", "not set");
		boolean married = wilma.getBoolean("married");
		Integer age = wilma.getInt("age", -1);
		float height = (float)wilma.getJsonNumber("height").doubleValue();
		JsonObject spouse = wilma.getJsonObject("spouse");

		System.out.println("------------------------------\n");

		for (int i = 0; i < flintstones.size(); i++) {
			JsonObject o = flintstones.getJsonObject(i);
			System.out.printf("%d >>>> %s\n", i, o.toString());
		}

		System.out.println("------STREAM------------------\n");
		flintstones.stream()
			.map(v -> v.asJsonObject())
			.forEach(jo -> {
				System.out.printf("STREAM: %s\n", jo.toString());
			});

	}

}
