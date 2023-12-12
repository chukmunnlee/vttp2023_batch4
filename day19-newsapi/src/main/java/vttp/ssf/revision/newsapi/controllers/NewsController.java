package vttp.ssf.revision.newsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.revision.newsapi.Utils;
import vttp.ssf.revision.newsapi.models.News;
import vttp.ssf.revision.newsapi.services.NewsService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller @RequestMapping
public class NewsController {

   @Autowired
   private NewsService newsSvc;

   // GET / /index.html
   @GetMapping(path={"/", "/index.html"})
   public ModelAndView getIndex() {
      ModelAndView mav = new ModelAndView("index");
      mav.addObject("codes", newsSvc.getCountryCode());
      mav.addObject("categories", Utils.CATEGORY);
      return mav;
   }

   // GET /news?country=sg&category=technology
   @GetMapping("/news")
   public ModelAndView getMethodName(@RequestParam MultiValueMap<String, String> params) {
      ModelAndView mav = new ModelAndView("news");
      String country = params.getFirst("country");
      String category = params.getFirst("category");

      List<News> news = newsSvc.getNews(country, category);

      mav.addObject("country", country);
      mav.addObject("category", category);
      mav.addObject("news", news);

      return mav;
   }
   
   
}