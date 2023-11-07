package sdf.day07;

import java.io.BufferedReader;
import java.io.FileReader;

import sdf.day07.WordDistribution;

// java -cp classes sdf.day07.Main a_file.txt
public class Main {

   public static void main(String[] args) throws Exception {

      WordDistribution dist = new WordDistribution();

      // Open the file for reading
      try (FileReader fr = new FileReader(args[0])) {
         BufferedReader br = new BufferedReader(fr);
         String line;
         while (null != (line = br.readLine())) {
            // Do not process blank lines, remove all punctuations
            line = line.trim().replaceAll("[^a-zA-Z0-9 ]", "");
            if (line.length() <= 0)
               continue;
            // Convert text to lower case
            line = line.toLowerCase();
            // Split the line
            String[] words = line.split(" ");
            System.out.printf("line: %s\n", line);
            for (int i = 0; i < words.length - 1; i++) {
               // curr and next
               String curr = words[i];
               String next = words[i + 1];
               System.out.printf("   :curr: %s, next: %s\n", curr, next);
               dist.addWord2(curr, next);
            }
         }
      }

      System.out.printf("\n\n======= distributions =============\n");
      dist.dumpDistribution();
   }

}