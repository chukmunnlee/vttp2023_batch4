package vttp.batch4.paf.day29bgg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch4.paf.day29bgg.models.Game;
import vttp.batch4.paf.day29bgg.repositories.BGGRepository;

@SpringBootApplication
public class Day29bggApplication implements CommandLineRunner {

	@Autowired
	private BGGRepository bggRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day29bggApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// List<Game> games = bggRepo.findGamesByName("detective");
		// System.out.printf("count: %d\n", games.size());
		// for (Game g: games)
		// 	System.out.println(g);
	}

}
