package sdf.day04;

import java.util.LinkedList;
import java.util.List;

public class Run {

   //static Operation add = (x, y) -> x + y;
	//
	public static int apply(Operation oper, int x, int y) {
		return oper.perform(x, y);
	}

   public static void main(String[] args) {

		Operation o = (x, y) -> x * y;

		apply(o, 3, 4);

      List<Integer> nums = new LinkedList<>();
      for (int i = 1; i <= 10; i++)
         nums.add(i);

      System.out.println(">>> nums: " + nums);

      int total = 0;
      for (int i = 0; i < nums.size(); i++) {
         // filter out all the even
         int curr = nums.get(i);
         // Filter
         if (0 != (curr % 2))
            continue;
         // Map
         curr *= 2;
         // Reduce
         total += curr;
      }
      System.out.printf("total: %d\n", total);

   }
   
}
