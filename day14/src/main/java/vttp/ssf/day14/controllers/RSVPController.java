package vttp.ssf.day14.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/rsvp")
public class RSVPController {

   @GetMapping
   public String getRSVP(@RequestParam MultiValueMap<String, String> form,
         @RequestParam String name, Model model) {

      System.out.printf("map GET: %s\n", form);
      System.out.printf("name: %s\n", name);

      model.addAttribute("name", form.getFirst("name"));

      return "thankyou";
   }

   @PostMapping
   public ModelAndView postRSVP(@RequestBody MultiValueMap<String, String> form) {
      System.out.printf("map POST: %s\n", form);
      String name = form.getFirst("name").trim().toLowerCase();
      ModelAndView mav = new ModelAndView();
      if (!"fred".equals(name)) {
         mav.setViewName("whoareyou");
         mav.setStatus(HttpStatusCode.valueOf(400));
      } else {
         mav.setViewName("thankyou");
         mav.setStatus(HttpStatusCode.valueOf(201));
      }
      mav.addObject("name", name);
      return mav;
   }

}
