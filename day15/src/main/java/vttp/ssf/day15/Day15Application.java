package vttp.ssf.day15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class Day15Application implements CommandLineRunner {

	// 
	@Autowired @Qualifier(Utils.BEAN_REDIS)
	private RedisTemplate<String, String> template;

	public static void main(String[] args) {
		SpringApplication.run(Day15Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf(">>> redistemplate: %s\n", template);
	}

}
