package vttp.batch4.csf.werewolf.models;

import org.bson.Document;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public class Player {

	private String username;
	private String role = "not set";
	private boolean dead = false;

	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return this.username; }

	public void setRole(String role) { this.role = role; }
	public String getRole() { return this.role; }

	public void setDead(boolean dead) { this.dead = dead; }
	public boolean getDead() { return this.dead; }
	public boolean isDead() { return this.dead; }

	public static Player toPlayer(Document doc) {
		Player player = new Player();
		player.setUsername(doc.getString(F_USERNAME));
		player.setRole(doc.getString(F_ROLE));
		player.setDead(doc.getBoolean(F_DEAD, false));
		return player;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.put(F_USERNAME, username);
		doc.put(F_ROLE, role);
		doc.put(F_DEAD, dead);
		return doc;
	}

	@Override
	public String toString() {
		return "Player{username=%s, role=%s, dead=%b, players=%s}"
			.formatted(username, role, dead);
	}
}
