package sdf.revision;

public class GameMain {

   public static void main(String[] args) {
      LaserGun gun = new LaserGun();
      Damageable m = new Monster();
      Damageable g = new Godzilla();
      Damageable k = new KingKong();
      Damageable b = new Box();

      //gun.shoot(m);
      gun.shoot(g);
      gun.shoot(k);
      gun.shoot(b);

      // Cannot instantiate an interface
      // Damageable dd = new Damageable();

      Damageable[] destroyThese = new Damageable[5];
      destroyThese[0] = new Monster();
      destroyThese[1] = new KingKong();
      destroyThese[2] = new KingKong();
      destroyThese[3] = new Godzilla();
      destroyThese[5] = new Box();

      for (Damageable dd: destroyThese)
         gun.shoot(dd);
   }
   
}
