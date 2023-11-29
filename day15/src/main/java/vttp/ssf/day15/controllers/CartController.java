package vttp.ssf.day15.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.LinkedList;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf.day15.Utils;
import vttp.ssf.day15.models.Item;
import vttp.ssf.day15.repositories.CartRepository;

@Controller
@RequestMapping(path="/cart")
public class CartController {

   public static final String ATTR_ITEM = "item";
   public static final String ATTR_CART = "cart";

   @Autowired 
   private CartRepository cartRepo;

   @GetMapping
   public String getCart(@RequestParam String name) {

      if (cartRepo.hasCart(name)) {

      }

      return "cart";
   }

   @PostMapping(path = "/checkout")
   public ModelAndView postCartCheckout(HttpSession sess) {
      ModelAndView mav = new ModelAndView("cart");

      List<Item> cart = Utils.getCart(sess);
      System.out.printf("Checking out cart: %s\n", cart);

      sess.invalidate();

      mav.addObject(ATTR_ITEM, new Item());
      mav.setStatus(HttpStatusCode.valueOf(200));

      return mav;
   }

   @PostMapping
   public ModelAndView postCart(@Valid @ModelAttribute(ATTR_ITEM) Item item
         , BindingResult bindings, HttpSession sess) {

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

      mav.setStatus(HttpStatusCode.valueOf(200));
      return mav;
   }
   
}
