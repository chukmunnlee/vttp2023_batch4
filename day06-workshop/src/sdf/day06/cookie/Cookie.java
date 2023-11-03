package sdf.day06.cookie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {

   private final List<String> cookies; 
   private final int cookieCount;
   private final Random rand = new SecureRandom();

   public Cookie(String file) throws Exception {
      try (FileReader fr = new FileReader(file)) {
         BufferedReader br = new BufferedReader(fr);
         cookies = br.lines()
               .map(line -> line.trim())
               .toList();
         cookieCount = cookies.size();
      }
   }

   public String get() {
      return get(1).get(0);
   }
   public List<String> get(int count) {
      List<String> toReturn = new LinkedList<>();
      for (int i = 0; i < count; i++) {
         int idx = rand.nextInt(cookieCount);
         toReturn.add(cookies.get(idx));
      }
      return toReturn;
   }
   
}
