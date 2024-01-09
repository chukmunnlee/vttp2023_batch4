package vttp2023.batch4.paf.day21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch4.paf.day21.repositories.BookRepository;

@SpringBootApplication
public class Day21Application implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day21Application.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println("----------------------------");
		bookRepo.findBooksByTitle("murder");

		System.out.println("\n----------------------------");
		bookRepo.findBooksByTitle("asdfasdfasd");

		System.out.println("\n----------------------------");

		bookRepo.findBooksByFormatAndRating("paperback", 4);
	}

}
