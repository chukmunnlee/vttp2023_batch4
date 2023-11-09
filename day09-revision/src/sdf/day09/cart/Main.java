package sdf.day09.cart;

import java.io.Console;

// Static import
import static sdf.day09.cart.Commands.*;

public class Main {

   public static void main(String[] args) throws Exception {

      // Create an instance of the database
      // java -cp classes sdf.day09.cart.Main db
      CartDatabase db = new CartDatabase(args[0]);

      // Get an instance of Console
      final Console cons = System.console();
      boolean stop = false;
      ShoppingCart cart = null;

      while (!stop) {
         String line = cons.readLine("> ");
         line = line.trim().replaceAll("\\s+", " ");
         if (line.length() <= 0)
            continue;
         String[] terms = line.split(" ");
         switch (terms[0]) {
            case CMD_LOAD: // load fred
               cart = db.load(terms[1]);
               break;

            case CMD_SAVE: // save
               db.save(cart);
               break;

            case CMD_ADD: // add apple orange pear
               for (int i = 1; i < terms.length; i++)
                  cart.add(terms[i]);
               break;

            default:
         }

      }

   }

}