package sdf.day09.text;

import java.io.Console;

public class Main {

	public static void main(String[] args) throws Exception {

		World world = new World(args[0]);
		world.parse();

		RoomDescription desc = world.start();

		Console cons = System.console();
		while (true) {
			// print the room description
			String line = cons.readLine("> ");
			desc = world.evaluate(line);
		}

	}
}
