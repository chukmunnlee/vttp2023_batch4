package sdf.day04;

import java.io.BufferedReader;
import java.io.FileReader;

public class CountWordsFromFile {

   public static void main(String[] args) throws Exception {
      // Open the file
      FileReader fr = new FileReader(args[0]);
      BufferedReader br = new BufferedReader(fr);

      String line;
      while ((line = br.readLine()) != null) {
         System.out.printf("> line: %s\n", line);
      }

      br.close();
      
   }
   
}
