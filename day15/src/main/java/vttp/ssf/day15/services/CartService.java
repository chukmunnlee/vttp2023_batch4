package vttp.ssf.day15.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.ssf.day15.Utils;
import vttp.ssf.day15.models.Item;
import vttp.ssf.day15.repositories.CartRepository;

@Service
public class CartService {

   @Autowired 
   private CartRepository cartRepo;

   public List<Item> getCart(String name) {
      if (cartRepo.hasCart(name))
         return cartRepo.getCart(name);
      return new LinkedList<>();
   }

   public void save(String name, List<Item> cart) {
      cartRepo.deleteCart(name);
      cartRepo.addCart(name, cart);
   }
   
}
