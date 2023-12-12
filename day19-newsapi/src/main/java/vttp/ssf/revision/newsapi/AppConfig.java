package vttp.ssf.revision.newsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private Integer redisPort;

	@Value("${spring.redis.database}")
	private Integer redisDatabase;

	@Value("${spring.redis.username}")
	private String redisUsername;

	@Value("${spring.redis.password}")
	private String redisPassword;

	@Bean("newsCache")
	public RedisTemplate<String, String> redisTemplateFactory() {
		final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
		config.setDatabase(redisDatabase);
		if (redisUsername.trim().length() > 0) {
			config.setUsername(redisUsername);
			config.setPassword(redisPassword);
		}

		final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
		final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
		jedisFac.afterPropertiesSet();

		final RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisFac);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new StringRedisSerializer());

		return template;
	}
}
