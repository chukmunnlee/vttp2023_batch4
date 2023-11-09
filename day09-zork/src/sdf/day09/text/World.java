package sdf.day09.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static sdf.day09.text.Constants.*;

public class World {

	private final String file;
	private final Map<String, Room> rooms = new HashMap<>();
	private String start;
	private Room currRoom;

	public World(String file) { this.file = file; }

	public RoomDescription start() {
		currRoom = rooms.get(start);
		return new RoomDescription(currRoom);
	}

	public RoomDescription evaluate(String command) {
		String[] terms = command.split(" ");
		switch (terms[0]) {
			case GO:
				if (currRoom.isAccessible(terms[1])) {
					String roomId = currRoom.getRoom(terms[1]).get();
					currRoom = rooms.get(roomId);
					return new RoomDescription(currRoom);
				}

				break;
		}
	}

	public void parse() throws Exception {

		try (FileReader fr = new FileReader(file)) {
			BufferedReader br = new BufferedReader(fr);
			String line;
			Room room = null;

			while (null != (line = br.readLine())) {
				line = line.trim();
				if ((line.length() <= 0) || line.startsWith(COMMENT))
					continue;
				String[] terms = line.split(":");

				switch (terms[0]) {
					case ROOM:
						room = new Room(terms[1]);
						saveRoom(room);
						break;

					case NAME:
						room.setName(terms[1]);
						break;

					case DESCRIPTION:
						room.setDescription(terms[1]);
						break;

					case START:
						start = terms[1];
						break;

					default:
				}
			}
		}
	}

	private void saveRoom(Room room) {
		rooms.put(room.getRoomId(), room);
	}
}
