package sdf.revision;

public class Godzilla extends Monster {

   private int life;

   public Godzilla() {
      life = 30;
   }

   public void takeDamage(int d) {
      life -= d * .5;
   }
   
}
