package vttp.batch4.csf.day38.articles.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vttp.batch4.csf.day38.articles.models.Article;
import vttp.batch4.csf.day38.articles.repositories.ArticleRepository;
import vttp.batch4.csf.day38.articles.repositories.ImageRepository;

@Service
public class ArticleService {

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private ArticleRepository articleRepo;

	public Article saveArticle(Article article, MultipartFile imageFile) throws Exception {

		String imageId = UUID.randomUUID().toString().substring(0, 8);

		String url = imageRepo.save(imageId, imageFile.getContentType()
				, imageFile.getInputStream(), imageFile.getSize());

		article = article.updateUrl(url);

		articleRepo.saveArticle(article);

		return article;
	}
}
