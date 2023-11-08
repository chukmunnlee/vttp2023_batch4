package sdf.day07.calc;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServer implements Runnable {

   public static void main(String[] args) throws Exception {

      final ExecutorService thrPool = Executors.newFixedThreadPool(2);

      // Create a server socket
      try (ServerSocket server = new ServerSocket(3000)) {
         while (true) {
            final Socket client = server.accept();
            CalcSession sess = new CalcSession(client);
            Runnable r = () -> { System.out.printf("I am also a lambda"); };

            thrPool.submit(sess);
            thrPool.submit(r);
         }
      }
   }

   @Override
   public void run() {
      System.out.printf("Runnable in ThreadedServer class");
   }
   
}
