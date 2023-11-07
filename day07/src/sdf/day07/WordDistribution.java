package sdf.day07;

import java.util.HashMap;
import java.util.Map;

public class WordDistribution {

   private final Map<String, Map<String, Integer>> dist = new HashMap<>();

   public void addWord2(String root, String next) {

      Map<String, Integer> countMap = dist.get(root);
      if (null == countMap) {
         countMap = new HashMap<>();
         dist.put(root, countMap);
      }

      if (!countMap.containsKey(next))
         countMap.put(next, 0);

      // 0 or the existing count
      int count = countMap.get(next);
      count += 1;
      countMap.put(next, count);
   }

   public void addWord(String root, String next) {
      // Check if we have encountered root 
      if (!dist.containsKey(root)) {
         // Create the count map
         Map<String, Integer> countMap = new HashMap<>();
         // add next, set count to 1
         countMap.put(next, 1);
         dist.put(root, countMap);
         return;
      }

      // Implicit else
      // We have encounted the root word
      Map<String, Integer> countMap = dist.get(root);
      if (!countMap.containsKey(next)) {
         countMap.put(next, 1);
      } else {
         Integer count = countMap.get(next);
         count += 1;
         countMap.put(next, count);
      }
      return;
   }

   public void dumpDistribution() {
      for (String root: dist.keySet()) {
         System.out.printf("%s\n", root);
         Map<String, Integer> countMap = dist.get(root);
         for (String next: countMap.keySet())
            System.out.printf("\t%s: %d\n", next, countMap.get(next));
      }
   }
}
