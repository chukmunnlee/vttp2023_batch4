package sdf.revision;

public class Monster implements Damageable {

   protected int life = 10;

   public void takeDamage(int d) {
      life -= d;
   }
   public void heal() { }

   public void move() {

   }
   
}
