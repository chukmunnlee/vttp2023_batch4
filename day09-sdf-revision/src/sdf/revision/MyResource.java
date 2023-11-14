package sdf.revision;

public class MyResource implements AutoCloseable {

   @Override
   public void close() throws Exception {
      System.out.println("Closing the resource");
   }
   
}
