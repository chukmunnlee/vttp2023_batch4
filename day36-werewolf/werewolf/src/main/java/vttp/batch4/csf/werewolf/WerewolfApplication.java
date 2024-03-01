package vttp.batch4.csf.werewolf;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch4.csf.werewolf.messages.StartGameRequest;
import vttp.batch4.csf.werewolf.services.GameService;

@SpringBootApplication
public class WerewolfApplication implements CommandLineRunner {

	@Autowired
	private GameService gameSvc;

	public static void main(String[] args) {
		SpringApplication.run(WerewolfApplication.class, args);
	}

	@Override
	public void run(String... args) {
		/*
		String output = gameSvc.createGame();
		String gameId = output.split(",")[0];
		String secret = output.split(",")[1];
		System.out.printf(">>> GameId: %s\n", gameId);

		List<String> names = gameSvc.injectPlayers(gameId, 8);
		System.out.printf(">>> players: %s\n", names);

		System.out.println("==== starting game ====");
		StartGameRequest req = new StartGameRequest(gameId, "moderator", true, secret);
		Optional<String> opt = gameSvc.startGame(req);
		if (opt.isPresent())
			System.out.printf(">>> from startGame: %s\n", opt.get());

		//String toRemove = names.get(2);
		//System.out.printf(">> to remote: %s\n", toRemove);

		//gameSvc.leaveGame(gameId, toRemove);

		System.exit(0);
		*/
	}

}
