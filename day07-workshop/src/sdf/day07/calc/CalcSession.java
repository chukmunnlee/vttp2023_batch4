package sdf.day07.calc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CalcSession implements Runnable {

   private final RevPolishCalc calc = new RevPolishCalc();
   private final Socket sock;

   public CalcSession(Socket sock) {
      this.sock = sock;
   }

   @Override
   public void run() {
      try {
         start();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void start() throws Exception {
      // Open streams
      final InputStreamReader isr = new InputStreamReader(sock.getInputStream());
      final BufferedReader br = new BufferedReader(isr);
      final OutputStreamWriter osw = new OutputStreamWriter(sock.getOutputStream());
      final BufferedWriter bw = new BufferedWriter(osw);

      boolean stop = false;
      while (!stop) {
         String line = br.readLine();
         line = line.trim();

         if ("exit".equals(line)) {
            stop = true;
            continue;
         }

         Float result = calc.evaluate(line);

         System.out.printf("line: %s, result: %f\n", line, result);

         bw.write("%5.2f\n".formatted(result));
         bw.flush();
      }

      // Close the stream
      bw.flush();
      osw.flush();
      osw.close();
      isr.close();
      sock.close();
   }
   
}
