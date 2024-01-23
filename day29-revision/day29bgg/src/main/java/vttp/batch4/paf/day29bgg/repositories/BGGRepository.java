package vttp.batch4.paf.day29bgg.repositories;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import vttp.batch4.paf.day29bgg.models.Comment;
import vttp.batch4.paf.day29bgg.models.Game;

@Repository
public class BGGRepository {

   @Autowired
   private MongoTemplate template;

   /*
      db.games.aggregate([
        { $match: { name: { $regex: "detective", $options: "i" }} },
        { $project: { _id: "$gid", name: 1, ranking: 1 } },
        {
          $lookup: {
            from: 'comments',
            foreignField: 'gid',
            localField: '_id',
            as: 'comments'
          }
        }
      ])
    */
   public List<Game> findGamesByName(String title) {
      MatchOperation matchName = Aggregation.match(
         Criteria.where("name").regex(title, "i")
      );

      ProjectionOperation projectGameFields = Aggregation.project("name", "ranking")
            .and("gid").as("_id");

      LookupOperation joinComments = Aggregation.lookup(
            // join collection, local field, foreign field, field to add to
            "comments", "_id", "gid", "comments");

      Aggregation pipeline = Aggregation.newAggregation(
         matchName, projectGameFields, joinComments
      );

      AggregationResults<Document> results = template.aggregate(
            pipeline, "games", Document.class);

      List<Game> games = new LinkedList<>();

      for (Document d: results) {
         int _id = d.getInteger("_id");
         String name = d.getString("name");
         int ranking = d.getInteger("ranking");
         List<Comment> comments = new LinkedList<>();
         for (Document e: d.getList("comments", Document.class)) {
            String user = e.getString("user");
            int rating = e.getInteger("rating");
            String text = e.getString("c_text");
            comments.add(new Comment(user, rating, text));
         }
         games.add(new Game(_id, name, ranking, comments));
      }

      return games;
   }
   
}
