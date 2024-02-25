package vttp.batch4.csf.werewolf.respositories;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import vttp.batch4.csf.werewolf.models.Game;
import vttp.batch4.csf.werewolf.models.Player;

import static vttp.batch4.csf.werewolf.respositories.Constants.*;

@Repository
public class GameRepository {

	@Autowired
	private MongoTemplate template;

	/*
	 * db.games.find({ created_on: { $gte: timestamp } }).count()
	 */
	public int getGamesAfterTimestamp(long timestamp) {
		Date afterDate = new Date(timestamp);
		Criteria criteria = Criteria.where(F_CREATED_ON).gte(afterDate);
		Query query = Query.query(criteria);
		return (int)template.count(query, C_GAMES);
	}

	/*
	 * db.games.deleteMany({ created_on: { $lt: timestamp } })
	 */
	public long deleteGamesBeforeTimestamp(long timestamp) {
		Date beforeDate = new Date(timestamp);
		Criteria criteria = Criteria.where(F_CREATED_ON).lt(beforeDate);
		Query query = Query.query(criteria);
		DeleteResult result = template.remove(query, C_GAMES);
		return result.getDeletedCount();
	}

	/*
	 * db.games.deleteOne({ _id: gameId, secret: 'abcd1234' })
	 */
	public boolean deleteGameByGameId(String gameId, String secret) {
		Criteria criteria = Criteria.where(F_ID).is(gameId)
				.and(F_SECRET).is(secret);
		Query query = Query.query(criteria);
		DeleteResult result = template.remove(query, C_GAMES);
		return result.getDeletedCount() > 0;
	}

	/*
	 * db.insert({ _id: gameId, created_on: time })
	 */
	public void createGame(String gameId, String secret) {
		Document doc = new Document();
		doc.put(F_ID, gameId);
		doc.put(F_SECRET, secret);
		doc.put(F_CREATED_ON, new Date());
		doc.put(F_STARTED, false);
		doc.put(F_PLAYERS, new LinkedList<Document>());
		doc = template.insert(doc, C_GAMES);
	}

	/*
	 * db.games.find({ _id: 'abcd1234' })
	 */
	public Optional<Game> getGameByGameId(String gameId) {
		Criteria criteria = Criteria.where(F_ID).is(gameId);
		Query query = Query.query(criteria);

		List<Document> result = template.find(query, Document.class, C_GAMES);
		if (result.size() <= 0)
			return Optional.empty();

		return Optional.of(Game.toGame(result.getFirst()));
	}

	/*
	 * db.games.updateOne(
	 * 	{ _id: 'abcd1234' },
	 * 	{ $push: { players: { <player doc> } } }
	 * )
	 */
	public boolean addPlayerToGame(String gameId, Player player) {
		Criteria criteria = Criteria.where(F_ID).is(gameId);
		Query query = Query.query(criteria);

		Update updateOps = new Update()
			.push(F_PLAYERS, player.toDocument());

		var result = template.updateFirst(query, updateOps, Document.class, C_GAMES);

		return result.getModifiedCount() == 1;
	}

	/*
	 * db.games.updateOne(
	 * 	{ _id: 'abcd1234' },
	 * 	{ $set: { started: true } }
	 * )
	 */
	public boolean startGame(String gameId, boolean started) {
		Criteria criteria = Criteria.where(F_ID).is(gameId);
		Query query = Query.query(criteria);

		Update updateOps = new Update()
			.set(F_STARTED, started);

		var result = template.updateFirst(query, updateOps, Document.class, C_GAMES);

		return result.getModifiedCount() == 1;
	}

	/*
	 * db.games.updateOne(
	 * 	{ _id: 'abcd1234' },
	 * 	{ $pull: { players: { username: 'fred' } } }
	 * )
	 */
	public boolean deletePlayerFromGame(String gameId, String username) {
		Criteria criteria = Criteria.where(F_ID).is(gameId);
		Query query = Query.query(criteria);

		Document user = new Document(F_USERNAME, username);

		Update updateOps = new Update()
			.pull(F_PLAYERS, user);

		return template.updateFirst(query, updateOps, Document.class, C_GAMES).getModifiedCount() == 1;
	}

	/*
	 * db.games.aggregate([
	 * 	{ $match: { _id: 'abcd1234' } },
	 * 	{ $project: { count: { $size: "$players" } } } 
	 * ])
	 */
	public int getPlayersInGame(String gameId) {

		MatchOperation matchGameId = Aggregation.match(Criteria.where(F_ID).is(gameId));
		ProjectionOperation countPlayers = Aggregation.project()
				.and(AggregationExpression.from(
					MongoExpression.create("""
						$size: "$players"
					"""
					)
				)).as(F_COUNT) ;

		Aggregation pipeline = Aggregation.newAggregation(matchGameId, countPlayers);
		List<Document> results = template.aggregate(pipeline, C_GAMES, Document.class)
				.getMappedResults();

		if (results.size() <= 0)
			return 0;

		Document doc = results.getFirst();
		return doc.getInteger(F_COUNT, 0);
	}
}
