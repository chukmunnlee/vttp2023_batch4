package vttp2024.batch4.csf.backend.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2024.batch4.csf.backend.models.Game;

@Repository
public class GamesRepository {

	@Autowired
	private MongoTemplate template;

	/*
	 * db.games.find({ name: { $regex: 'abc', $options: 'i' })
	 * 	.projection({ _id: 0, gid: 1, name: 1, url: 1, image: 1 })
	 * 	.sort({ name: -1 })
	 */
	public List<Game> findGameByName(String name) {
		Criteria criteria = Criteria.where("name")
				.regex(name, "i");
		Query query = Query.query(criteria)
			.with(Sort.by(Direction.ASC, "name"));
		query.fields().exclude("_id", "year", "ranking", "users_rated");

		return template.find(query, Document.class, "games")
			.stream()
			.map(d -> {
				return new Game(d.getInteger("gid"), d.getString("name"),
					d.getString("url"), d.getString("image"));
			})
			.toList();
	}
}
