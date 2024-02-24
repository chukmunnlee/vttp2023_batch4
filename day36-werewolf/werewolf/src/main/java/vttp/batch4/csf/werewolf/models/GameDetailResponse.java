package vttp.batch4.csf.werewolf.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

public class GameDetailResponse {

	private String gameId;
	private int playerCount;

	public void setGameId(String gameId) { this.gameId = gameId; }
	public String getGameId() { return this.gameId; }

	public void setPlayerCount(int playerCount) { this.playerCount = playerCount; }
	public int getPlayersCount() { return this.playerCount; }

	public JsonObject toPlayerCountResponse() {
		return Json.createObjectBuilder()
			.add(F_GAMEID, gameId)
			.add(F_COUNT, playerCount)
			.build();
	}
}
