package sdf.day08.btree;

public class BTreeNode {

   private final int value;
   private BTreeNode left = null;
   private BTreeNode right = null;

   public BTreeNode(int value) {
      this.value = value;
   }

   public int getValue() { return value; }

   public BTreeNode getLeft() { return left; }
   public void setLeft(BTreeNode left) { this.left = left; }
   public boolean hasLeft() { return null != left; }

   public BTreeNode getRight() { return right; }
   public void setRight(BTreeNode right) { this.right = right; }
   public boolean hasRight() { return null != right; }


   
}
