package vttp.batch4.csf.day38.articles.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.day38.articles.models.Article;

@Repository
public class ArticleRepository {

	@Value("${collection.articles}")
	private String colArticles;

	@Autowired
	private MongoTemplate template;

	public String saveArticle(Article article) {
		Document doc = template.insert(article.toDocument(), colArticles);
		return doc.getObjectId("_id").toHexString();
	}

}
