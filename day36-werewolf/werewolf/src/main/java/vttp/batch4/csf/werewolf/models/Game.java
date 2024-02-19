package vttp.batch4.csf.werewolf.models;

import java.util.Date;
import java.util.List;

import org.bson.Document;

import java.util.LinkedList;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public class Game {

	private String gameId;
	private String secret;
	private Date createdOn;
	private List<Player> players = new LinkedList<>();

	public void setGameId(String gameId) { this.gameId = gameId; }
	public String getGameId() { return this.gameId; }

	public void setSecret(String secret) { this.secret = secret; }
	public String getSecret() { return this.secret; }

	public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }
	public Date getCreatedOn() { return this.createdOn; }

	public void setPlayers(List<Player> players) { this.players = players; }
	public List<Player> getPlayers() { return this.players; }
	public void addPlayer(Player player) { this.players.add(player); }

	public static Game toGame(Document doc) {
		Game game = new Game();
		game.setGameId(doc.getString(F_ID));
		game.setSecret(doc.getString(F_SECRET));
		game.setCreatedOn(doc.getDate(F_CREATED_ON));
		doc.getList(F_PLAYERS, Document.class).stream()
			.map(Player::toPlayer)
			.forEach(game::addPlayer);
		return game;
	}

	@Override
	public String toString() {
		return "Game{gameId=%s, secret=%s, createOn=%s, players=%s}"
			.formatted(gameId, secret, createdOn.toString(), players);
	}
}
