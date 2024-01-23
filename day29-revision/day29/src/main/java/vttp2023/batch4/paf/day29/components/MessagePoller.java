package vttp2023.batch4.paf.day29.components;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MessagePoller {

   private Logger logger = Logger.getLogger(MessagePoller.class.getName());

   @Autowired @Qualifier("myredis")
   public RedisTemplate<String, String> template;

   public void printTemplate() {
      System.out.println(">>>>> template: " + template);
   }

   @Async
   public void start() {
      Runnable run = () -> {
         ListOperations<String, String> listOpr = template.opsForList();
         //listOpr.leftPush("sales", "STARTING.....");
         logger.info("Start polling sales queue...");
         while (true) {
            logger.info("Polling...");
            String value = listOpr.rightPop("sales", Duration.ofSeconds(10));
            if ((null == value) || ("" == value.trim())) {
               logger.info("No data repolling...");
               continue;
            }
            // Process data
            logger.info(">>>>>> DATA: %s".formatted(value));
         }
      };
      ExecutorService thrPool = Executors.newFixedThreadPool(1);
      thrPool.submit(run);
   }

   
}
