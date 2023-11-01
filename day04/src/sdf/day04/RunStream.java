package sdf.day04;

import java.util.LinkedList;
import java.util.List;

public class RunStream {

   public static void main(String[] args) {
      List<Integer> nums = new LinkedList<>();
      for (int i = 1; i < 10; i++)
         nums.add(i);

      List<Integer> processed = 
         nums.stream()
            // Predicate.test(v) -> boolean
            .filter(v -> 0 == (v % 2))
            // Function.apply(v) -> r
            .map(v -> {
               return v * 2;
            })
            .toList();

      System.out.printf("> processed: %s\n", processed);

      int total = 
         nums.stream()
            // Predicate.test(v) -> boolean
            .filter(v -> 0 == (v % 2))
            // Function.apply(v) -> r
            .map(v -> {
               return v * 2;
            })
            .reduce(0, (acc, v) -> acc + v);

      System.out.printf(">> total: %d\n", total);
   }

}
