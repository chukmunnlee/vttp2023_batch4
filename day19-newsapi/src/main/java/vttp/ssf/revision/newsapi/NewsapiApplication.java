package vttp.ssf.revision.newsapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.revision.newsapi.models.CountryCode;
import vttp.ssf.revision.newsapi.services.NewsService;

@SpringBootApplication
public class NewsapiApplication implements CommandLineRunner {

	@Autowired
	private NewsService newsSvc;

	public static void main(String[] args) {
		SpringApplication.run(NewsapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<CountryCode> codes = newsSvc.getCountryCode();

		System.out.printf("---- country code: %s\n", codes);
	}
}
