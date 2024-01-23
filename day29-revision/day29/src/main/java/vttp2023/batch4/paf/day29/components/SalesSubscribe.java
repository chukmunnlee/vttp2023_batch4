package vttp2023.batch4.paf.day29.components;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class SalesSubscribe implements MessageListener {

   @Override
   public void onMessage(Message msg, byte[] pattern) {
      String txt = new String(msg.getBody());
      System.out.printf(">>>> FROM subscription: %s\n", txt);
   }
   
}
