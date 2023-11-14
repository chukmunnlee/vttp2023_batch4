package sdf.revision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart implements AutoCloseable {

   private List<String> items = new LinkedList<>();
   private String name;

   public ShoppingCart(String name) {
      this.name = name;
   }

   public void add(String i) throws SQLException { }

   @Override 
   public void close() throws Exception {
      try (FileWriter fw = new FileWriter(name + ".db")) {
         BufferedWriter bw = new BufferedWriter(fw);
         items.stream()
            .forEach(i -> {
               try {
                  bw.write(i + "\n");
               } catch (Exception ex) { }
            });
      }
   }
   
}
