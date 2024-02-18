package vttp.batch4.csf.werewolf.respositories;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

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
	 * db.games.deleteOne({ _id: gameId })
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
		doc.put(F_PLAYERS, new LinkedList<Document>());
		doc = template.insert(doc, C_GAMES);
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
