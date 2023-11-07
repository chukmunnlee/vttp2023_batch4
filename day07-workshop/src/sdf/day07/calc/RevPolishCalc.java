package sdf.day07.calc;

import java.util.Stack;

public class RevPolishCalc {

   public static final String OP_ADD = "+";
   public static final String OP_SUB = "-";
   public static final String OP_DIV = "/";
   public static final String OP_MUL = "*";

   private final Stack<Float> stack = new Stack<>();

   public float evaluate2(String line) {
      String[] terms = line.trim().split(" ");
      float ans = 0f, lhs = 0f, rhs = 0f;
      for (String t: terms) {
         if (OP_ADD.equals(t)) {
            rhs = stack.pop();
            lhs = stack.pop();
            ans = lhs + rhs;
            stack.push(ans);
         } else if (OP_SUB.equals(t)) {
            rhs = stack.pop();
            lhs = stack.pop();
            ans = lhs - rhs;
            stack.push(ans);
         } else if (OP_MUL.equals(t)) {
            rhs = stack.pop();
            lhs = stack.pop();
            ans = lhs * rhs;
            stack.push(ans);
         } else if (OP_DIV.equals(t)) {
            rhs = stack.pop();
            lhs = stack.pop();
            ans = lhs / rhs;
            stack.push(ans);
         } else {
            stack.push(Float.parseFloat(t));
         }
      }
      return ans;
   }

   public float evaluate(String line) {
      String[] terms = line.trim().split(" ");
      float ans = 0f;
      for (String t: terms) {
         switch (t) {
            case OP_ADD:
            case OP_SUB:
            case OP_DIV:
            case OP_MUL:
               float rhs = stack.pop();
               float lhs = stack.pop();
               if (OP_ADD.equals(t))
                  ans = lhs + rhs;
               else if (OP_SUB.equals(t))
                  ans = lhs - rhs;
               else if (OP_DIV.equals(t))
                  ans = lhs / rhs;
               else 
                  ans = lhs * rhs;
               stack.push(ans);
               break;

            default:
               stack.push(Float.parseFloat(t));
         }
      }
      return ans;
   }

}