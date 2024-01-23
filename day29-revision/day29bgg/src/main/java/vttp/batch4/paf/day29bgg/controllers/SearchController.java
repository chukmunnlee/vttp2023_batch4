package vttp.batch4.paf.day29bgg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.batch4.paf.day29bgg.models.Game;
import vttp.batch4.paf.day29bgg.repositories.BGGRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping
public class SearchController {

   @Autowired
   private BGGRepository bggRepo;

   @GetMapping("/search")
   public ModelAndView search(@RequestParam String title) {
      List<Game> results = bggRepo.findGamesByName(title);
      System.out.println("search result: \n" + results);
      ModelAndView mav = new ModelAndView("search");
      mav.addObject("games", results);
      return mav;
   }
   
   
}
