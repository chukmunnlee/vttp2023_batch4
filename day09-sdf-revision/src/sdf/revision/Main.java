package sdf.revision;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

   public static void main(String... args) throws Exception {

      Path tmpPath = Paths.get("/opt/tmp");
      for (Path p: tmpPath)
         System.out.printf("%s\n", p);

      boolean dir = Files.isDirectory(tmpPath);
      System.out.printf("dir: %b\n", dir);

      if (dir)
         Files.newDirectoryStream(tmpPath)
            .forEach(p -> System.out.printf(">> %s\n", p));

      File f = new File("/opt/tmp");
      dir = f.isDirectory();
      System.out.printf("File.isDirectory: %b\n", dir);
      for (File _f: f.listFiles())
         System.out.printf("listFiles: %s\n", _f);

      boolean t = true;
      try (MyResource myRes = new MyResource()) {
         System.out.println("In try with resource block");
         if (t)
            throw new Exception("We have an error");
         System.out.println("Exiting try with resource block");
      }

      try (ShoppingCart cart = new ShoppingCart("fred")) {
         cart.add("apple");
         cart.add("orange");
         cart.add("pear");
      }
   }
}