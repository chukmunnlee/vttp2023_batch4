package vttp2024.batch4.csf.backend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	//@Autowired
	//private GamesRepository gamesRepo;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//List<Game> games = gamesRepo.findGameByName("carcassonne");
		//System.out.println(games);
	}

}
