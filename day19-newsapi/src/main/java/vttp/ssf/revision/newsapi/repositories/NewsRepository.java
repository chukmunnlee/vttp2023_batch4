package vttp.ssf.revision.newsapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonArray;

@Repository
public class NewsRepository {

   @Value("${newsapi.cache.timeout.mins}")
   long timeout;

   @Autowired @Qualifier("newsCache")
   private RedisTemplate<String, String> template;

   public void cacheNews(String country, String category, JsonArray news) {
      String key = mkKey(country, category);
      template.opsForValue()
         .set(key, news.toString(), timeout, TimeUnit.MINUTES);
   }

   public Optional<String> getNews(String country, String category) {
      String key = mkKey(country, category);
      String value = template.opsForValue().get(key);
      return Optional.ofNullable(value);
      //if (null == value)
      //   return Optional.empty();
      //return Optional.of(value);
   }

   private String mkKey(String country, String category) {
      return "%s-%s".formatted(country, category);

   }
   
}
