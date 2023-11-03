package sdf.day06.cookie;

import java.net.Socket;

public class Client {

   public static void main(String[] args) throws Exception {

      int port = 3000;
      if (args.length > 0)
         port = Integer.parseInt(args[0]);

      Socket socket = new Socket("localhost", port);
      CookieSession sess = new CookieSession(socket);
      sess.start();

   }
   
}
