package vttp.batch4.csf.day38.articles.models;

import org.bson.Document;

public record Article(String title, String text, String url) {

	public Article updateUrl(String url) {
		return new Article(title, text, url);
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.put("title", title);
		doc.put("text", text);
		doc.put("url", url);
		return doc;
	}
}
