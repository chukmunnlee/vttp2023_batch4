package sdf.day06.cookie;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

   public static final Integer DEFAULT_PORT = 3000;

   public static void main(String[] args) throws Exception {

      int port = DEFAULT_PORT;
      String cookieFile = "";
      switch (args.length) {
         case 1:
            cookieFile = args[0];
            break;
         case 2:
            cookieFile = args[0];
            port = Integer.parseInt(args[1]);
            break;
         default:
            System.err.println("Argument error");
            System.exit(1);
      }

      // Load the cookie file
      System.out.printf("Loading cookie file %s\n", cookieFile);
      Cookie cookie = new Cookie(cookieFile);

      System.out.printf("Starting server on port %d\n", port);
      ServerSocket server = new ServerSocket(port);

      while (true) {
         Socket client = server.accept();
         System.out.println("New client connection");

         ClientHandler handler = new ClientHandler(client, cookie);
         handler.start();

      }

   }
}