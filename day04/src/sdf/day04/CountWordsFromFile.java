package sdf.day04;

import java.io.BufferedReader;
import java.io.FileReader;

public class CountWordsFromFile {

   public static void main(String[] args) throws Exception {
      // Open the file
      FileReader fr = new FileReader(args[0]);
      BufferedReader br = new BufferedReader(fr);

      String line;
      int count = 0;
      while ((line = br.readLine()) != null) {
         // Clean the line
         line = line.trim().replaceAll("[^\\sa-zA-Z0-9]", "");
         // Check if it is line is empty
         if (line.length() <= 0)
            continue;
         // Split the line into words delimited by single space
         String[] words = line.split(" ");
         // Count the number of words and add to total
         count += words.length;
      }

      br.close();

      System.out.printf("Number of words in %s is %d\n", args[0], count);
   }
   
}
