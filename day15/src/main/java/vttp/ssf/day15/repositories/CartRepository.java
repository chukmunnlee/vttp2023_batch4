package vttp.ssf.day15.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.day15.Utils;

@Repository
public class CartRepository {

   @Autowired @Qualifier(Utils.BEAN_REDIS)
   private RedisTemplate<String, String> template;

   public boolean hasCart(String name) {
      return template.hasKey(name);
   }
   
}
