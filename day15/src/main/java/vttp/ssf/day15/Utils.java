package vttp.ssf.day15;

import jakarta.servlet.http.HttpSession;
import vttp.ssf.day15.models.Item;

import java.util.List;
import java.util.LinkedList;

public class Utils {

   public static final String ATTR_CART = "cart";

   public static final String BEAN_REDIS = "myredis";

   public static List<Item> getCart(HttpSession sess) {
      List<Item> cart = (List<Item>)sess.getAttribute(ATTR_CART);
      if (null == cart) {
         cart = new LinkedList<>();
         sess.setAttribute(ATTR_CART, cart);
      }
      return cart;
   }
   
}
