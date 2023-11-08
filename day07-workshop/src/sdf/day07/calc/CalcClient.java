package sdf.day07.calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CalcClient {

   public static void main(String[] args) throws Exception {
      final Socket sock = new Socket("localhost", 3000);

      final InputStreamReader isr = new InputStreamReader(sock.getInputStream());
      final BufferedReader br = new BufferedReader(isr);
      final OutputStreamWriter osw = new OutputStreamWriter(sock.getOutputStream());
      final BufferedWriter bw = new BufferedWriter(osw);

      final Console cons = System.console();

      boolean stop = false;
      while (!stop) {
         String line = cons.readLine("> ");
         line = line.trim();
         stop = "exit".equals(line);

         bw.write(line + "\n");
         bw.flush();

         if (stop)
            continue;

         line = br.readLine();
         System.out.printf(">> result: %s\n", line);
      }

      // Close the stream
      bw.flush();
      osw.flush();
      osw.close();
      isr.close();
      sock.close();
   }
   
}
