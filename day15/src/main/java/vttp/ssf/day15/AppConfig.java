package vttp.ssf.day15;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// This is a configuration
@Configuration
public class AppConfig {

   private Logger logger = Logger.getLogger(AppConfig.class.getName());

   // Inject the properties from application.properties 
   // into the configuration
   @Value("${spring.redis.host}")
   private String redisHost;

   @Value("${spring.redis.port}")
   private Integer redisPort;

   @Value("${spring.redis.username}")
   private String redisUser;

   @Value("${spring.redis.password}")
   private String redisPassword;

   @Value("${spring.redis.database}")
   private Integer redisDatabase;

   @Bean(Utils.BEAN_REDIS)
   public RedisTemplate<String, String> createRedisConnection() {
      // Create a redis configuration
      RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
      config.setHostName(redisHost);
      config.setPort(redisPort);
      config.setDatabase(redisDatabase);
      // Only set the username and passwrd if they are set
      if (!"NOT_SET".equals(redisUser.trim())) {
         config.setUsername(redisUser);
         config.setPassword(redisPassword);
      }

      logger.log(Level.INFO, "Using Redis database %d".formatted(redisPort));
      logger.log(Level.INFO
         , "Using Redis password is set: %b".formatted(redisPassword != "NOT_SET"));

      JedisClientConfiguration jedisClient = JedisClientConfiguration
            .builder().build();
      JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
      jedisFac.afterPropertiesSet();

      RedisTemplate<String, String> template = new RedisTemplate<>();
      template.setConnectionFactory(jedisFac);

      template.setKeySerializer(new StringRedisSerializer());
      template.setValueSerializer(new StringRedisSerializer());
      template.setHashKeySerializer(new StringRedisSerializer());
      template.setHashValueSerializer(new StringRedisSerializer());

      return template;
   }
   
}
