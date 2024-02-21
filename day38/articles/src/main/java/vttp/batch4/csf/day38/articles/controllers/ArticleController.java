package vttp.batch4.csf.day38.articles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import vttp.batch4.csf.day38.articles.models.Article;
import vttp.batch4.csf.day38.articles.services.ArticleService;

@Controller
@RequestMapping
public class ArticleController {

	@Autowired
	private ArticleService articleSvc;

	@PostMapping(path="/article", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView postArticle(@RequestPart MultipartFile image,
			@RequestPart String title, @RequestPart String text) {

		ModelAndView mav;

		Article article = new Article(title, text, "");

		try {
			article = articleSvc.saveArticle(article, image);
			mav = new ModelAndView("article");
			mav.addObject("article", article);
			mav.setStatus(HttpStatusCode.valueOf(200));

		} catch (Exception ex) {
			mav = new ModelAndView("error");
			mav.addObject("error", ex.getMessage());
			mav.setStatus(HttpStatusCode.valueOf(500));
		}

		return mav;
	}
}
