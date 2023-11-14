package sdf.revision;

import java.sql.SQLException;

public class ShoppingCartMain {

   public static void main(String[] args) throws Exception {
      try (ShoppingCart cart = new ShoppingCart("fred")) {
         cart.add("apple");
      } catch (SQLException ex) {
      }
   }
   
}
