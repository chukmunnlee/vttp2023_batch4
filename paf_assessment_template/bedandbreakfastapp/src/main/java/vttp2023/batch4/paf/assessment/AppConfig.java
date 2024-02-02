package vttp2023.batch4.paf.assessment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// IMPORTANT: DO NOT MODIFY THIS FILE UNLESS REQUESTED TO DO SO
// If this method is changed, any assessment task relying on this method will
// not be marked
@Configuration
public class AppConfig {

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	@Bean
	public MongoTemplate createMongoTemplate() {
		final MongoClient client = MongoClients.create(mongoUri);
		return new MongoTemplate(client, "bedandbreakfast");
	}
}
