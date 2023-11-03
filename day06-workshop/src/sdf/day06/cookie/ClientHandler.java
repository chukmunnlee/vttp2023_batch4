package sdf.day06.cookie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler {

   private final Socket socket;
   private final Cookie cookie;

   public ClientHandler(Socket client, Cookie cookie) {
      this.socket = client;
      this.cookie = cookie;
   }

   public void start() throws Exception {

      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      OutputStream os = socket.getOutputStream();
      OutputStreamWriter ows = new OutputStreamWriter(os);
      BufferedWriter bw = new BufferedWriter(ows);

      boolean stop = false;

      while (!stop) {
         String line = br.readLine();
         int count = 1;
         line = line.trim();
         if (line.length() <= 0)
            continue;
         String[] tokens = line.split(" ");

         System.out.printf(">>> '%s'\n", line);
         System.out.println(">>> " + tokens.length);

         switch (tokens[0]) {
            case Constants.COOKIE:
               if (tokens.length > 1)
                  count = Integer.parseInt(tokens[1]);
               cookie.get(count).stream()
                  .map(l -> "%s\n".formatted(l))
                  .forEach(l -> {
                     try { 
                        System.out.println(">>> line = " + l);
                        bw.write(l); 
                        bw.flush();
                     } catch (Exception ex) { }
                  });
                  bw.write("end\n");
                  bw.flush();
               break;

            case Constants.END:
               System.out.println("Bye bye");
               stop = true;
               break;

            default:
               bw.write("end\n");
               bw.flush();
         }

      }

      is.close();
      os.close();
      socket.close();
   }
   
}
