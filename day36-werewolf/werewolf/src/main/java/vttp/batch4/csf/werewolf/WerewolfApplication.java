package vttp.batch4.csf.werewolf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		System.out.printf(">>> GameId: %s\n", gameId);

		List<String> names = gameSvc.injectPlayers(gameId, 4);
		System.out.printf(">>> players: %s\n", names);

		String toRemove = names.get(2);
		System.out.printf(">> to remote: %s\n", toRemove);

		gameSvc.leaveGame(gameId, toRemove);

		System.exit(0);
		*/
	}

}
