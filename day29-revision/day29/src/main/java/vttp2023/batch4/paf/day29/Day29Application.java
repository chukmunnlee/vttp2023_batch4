package vttp2023.batch4.paf.day29;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import vttp2023.batch4.paf.day29.components.MessagePoller;

@SpringBootApplication
@EnableAsync
public class Day29Application implements CommandLineRunner {

	@Autowired
	private MessagePoller poller;

	public static void main(String[] args) {
		SpringApplication.run(Day29Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>> @Autowired: ");
		poller.printTemplate();

		MessagePoller manual = new MessagePoller();
		System.out.println(">>> manual @Autowired: ");
		manual.printTemplate();

		poller.start();
	}

}
