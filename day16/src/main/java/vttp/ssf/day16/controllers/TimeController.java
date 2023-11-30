package vttp.ssf.day16.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/time", produces = MediaType.TEXT_HTML_VALUE)
public class TimeController {

   @GetMapping
   public String getTime(Model model) {
      String currTime = (new Date()).toString();
      model.addAttribute("time", currTime);
      return "time";
   }
   
}
