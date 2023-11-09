package sdf.day09.cart;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

   private List<String> items = new LinkedList<>();
   final String name;

   public ShoppingCart(String name) {
      this.name = name;
   }
   public ShoppingCart(String name, List<String> items) {
      this.name = name;
      this.items = items;
   }

   public void load(List<String> items) {
      this.items = items;
   }

   public void add(String item) {
      this.items.add(item);
   }
   public void remove(String item) {
      items = items.stream()
            .filter(i -> !item.equals(item))
            .toList();
   }

   public String getName() { return this.name; }
   
}
