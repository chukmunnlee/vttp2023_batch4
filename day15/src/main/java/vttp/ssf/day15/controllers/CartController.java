package vttp.ssf.day15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;
import java.util.LinkedList;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf.day15.Utils;
import vttp.ssf.day15.models.Item;
import vttp.ssf.day15.services.CartService;

@Controller
@RequestMapping(path="/cart")
public class CartController {

   private Logger logger = Logger.getLogger(CartController.class.getName());

   public static final String ATTR_ITEM = "item";
   public static final String ATTR_CART = "cart";
   public static final String ATTR_USERNAME = "username";

   @Autowired 
   private CartService cartSvc;

   @GetMapping
   public String getCart(@RequestParam String name, Model model, HttpSession sess) {

      List<Item> cart = cartSvc.getCart(name);

      logger.info("CART: %s - %s\n".formatted(name, cart));

      sess.setAttribute(Utils.ATTR_CART, cart);

      model.addAttribute(ATTR_ITEM, new Item());
      model.addAttribute(ATTR_CART, cart);
      model.addAttribute(ATTR_USERNAME, name);

      return "cart";
   }

   @PostMapping(path = "/checkout")
   public String postCartCheckout(HttpSession sess, @RequestParam String username) {
      //ModelAndView mav = new ModelAndView("cart");

      List<Item> cart = Utils.getCart(sess);
      System.out.printf("Checking out cart: %s\n", cart);

      cartSvc.save(username, cart);

      sess.invalidate();

      // mav.addObject(ATTR_ITEM, new Item());
      // mav.setStatus(HttpStatusCode.valueOf(200));

      return "redirect:/";
   }

   @PostMapping
   public ModelAndView postCart(@Valid @ModelAttribute(ATTR_ITEM) Item item
         , BindingResult bindings, @RequestParam String username, HttpSession sess) {

      System.out.printf("item: %s\n", item);
      System.out.printf("error: %b\n", bindings.hasErrors());

      ModelAndView mav = new ModelAndView("cart");

      if (bindings.hasErrors()) {
         mav.setStatus(HttpStatusCode.valueOf(400));
         return mav;
      }

      List<Item> cart = Utils.getCart(sess);
      cart.add(item);

      mav.addObject(ATTR_ITEM, new Item());
      mav.addObject(ATTR_CART, cart);
      mav.addObject(ATTR_USERNAME, username);

      mav.setStatus(HttpStatusCode.valueOf(200));
      return mav;
   }
   
}
