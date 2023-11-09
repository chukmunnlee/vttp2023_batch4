package sdf.day08.btree;

import java.util.Stack;

public class BinaryTree {

   private final BTreeNode root;

   public BinaryTree(int initValue) {
      root = new BTreeNode(initValue);
   }

   public void printTreeWithRecursion() {
      printTree(root);
   }
   public void printTree(BTreeNode btNode) {
      // go left
      if (btNode.hasLeft())
         printTree(btNode.getLeft());

      // print
      System.out.printf(" %s", btNode.getValue());

      // go right
      if (btNode.hasRight())
         printTree(btNode.getRight());
   }

   public void printTree() {
      BTreeNode curr = root;
      Stack<BTreeNode> stack = new Stack<>();
		stack.push(curr);
      while (!stack.empty()) {
         // push a
			curr = stack.peek();
			System.out.printf(">> stack: %s\n", stack);
         // push b
         if (curr.hasLeft()) {
				stack.push(curr.getLeft());
            continue;
         }
         // print a
         System.out.printf("** %d", curr.getValue());
         // push c
         if (curr.hasRight()) {
				stack.push(curr.getRight());
            continue;
         } else {
				stack.pop();
			}
         // pop a
      }
      System.out.println();

   }

   public void add(int value) {
      BTreeNode curr = root;
      BTreeNode toInsert;
      while (true) {
         // If it is already in the tree
         if (value == curr.getValue())
            return;
         else if (value < curr.getValue()) {
            if (curr.hasLeft())
               curr = curr.getLeft();
            else {
               toInsert = new BTreeNode(value);
               curr.setLeft(toInsert);
               return;
            }
         } else {
            if (curr.hasRight())
               curr = curr.getRight();
            else {
               toInsert = new BTreeNode(value);
               curr.setRight(toInsert);
               return;
            }
         }
      }
   }
}
