package sdf.day09.cart;

import java.util.LinkedList;
import java.util.List;

public class CartDatabase {

   public CartDatabase(String dbDir) {
   }

   public ShoppingCart load(String user) {
      // Load the user's card
      return new ShoppingCart(user);
   }

   public void save(ShoppingCart cart) {
      // Save the user's cart
   }

   
}
