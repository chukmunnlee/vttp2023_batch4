package sdf.revision;

public class LaserGun {

   private int hurtValue = 3;

   public void shoot(Damageable damaged) {
      damaged.takeDamage(hurtValue);
   }
}
