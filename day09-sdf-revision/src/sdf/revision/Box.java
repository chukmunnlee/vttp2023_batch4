package sdf.revision;

// method 2
public class Box implements Damageable {

   private int life = 5;

   public Box() {
      life = 5;
   }

   public void takeDamage(int d) {
      life -= d;
   }
   public void heal() { }
   
}
