package vttp2023.batch4.paf.day22;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch4.paf.day22.models.TaskSummary;
import vttp2023.batch4.paf.day22.services.TaskService;

@SpringBootApplication
public class Day22Application implements CommandLineRunner {

	@Autowired
	private TaskService taskSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day22Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//List<TaskSummary> results = taskSvc.getTasksAsSummaries();
		//System.out.println(results);
		/*
		Contact c = new Contact();
		c.setEmail("betty@gmail.com");
		c.setName("Betty");
		c.setDob(new Date(1960, 2, 28));
		c.setPhone("55567895");
		c.setComments("this is a commnent");

		if (!bffRepo.contactExists(c.getEmail())) {
			boolean result = bffRepo.insertContact(c);
			System.out.printf("Inserted: %b\n", result);
		} else {
			System.out.printf(">>>>> Email %s exists\n", c.getEmail());
		}
		*/

	}

}