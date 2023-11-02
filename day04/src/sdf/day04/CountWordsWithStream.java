package sdf.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CountWordsWithStream {

   public static void main(String[] args) throws Exception {
      // Open the file
      try (FileReader fr = new FileReader(args[0])) {
         BufferedReader br = new BufferedReader(fr);

         // What is this Optional?
         /* 
         Optional<Integer> opt = br.lines()
            // Clean the line
            .map(line -> line.trim().replaceAll("[^\\sa-zA-Z0-9]", ""))
            // Remove empty lines
            .filter(line -> line.length() > 0)
            // line -> words 
            .map(line -> line.split(" "))
            // words -> count
            .map(words -> words.length)
            .reduce((acc, x) -> acc + x);

         int count = 0;

         if (opt.isPresent()) {
            count = opt.get();
            System.out.printf("Number of words in %s is %d\n", args[0], count);
         } else {
            System.out.println("No result");
         }
         */
         //long count = 
         //List<String> wordList =
         br.lines()
            // Clean the line
            .map(line -> line.trim().replaceAll("[^\\sa-zA-Z0-9]", ""))
            // Remove empty lines
            .filter(line -> line.length() > 0)
            // line -> words 
            .map(line -> line.split(" "))
            .flatMap(words -> Stream.of(words))
            .map(word -> word.toLowerCase())
            .distinct()
            .forEach(word -> {
               System.out.printf("forEach = %s\n", word);
            });
            //.toList();
            //.count();

         //for (String w: wordList)
            //System.out.printf("word = %s\n", w);

         //System.out.printf("count: %d\n", count);
      }
   }
   
}
