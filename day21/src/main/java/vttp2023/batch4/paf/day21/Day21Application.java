package vttp2023.batch4.paf.day21;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch4.paf.day21.models.Book;
import vttp2023.batch4.paf.day21.models.BookSummary;
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

		// System.out.println("----------------------------");
		// bookRepo.findBooksByTitle("murder");
 
		// System.out.println("\n----------------------------");
		// bookRepo.findBooksByTitle("asdfasdfasd");
 
		// System.out.println("\n----------------------------");
 
		// bookRepo.findBooksByFormatAndRating("paperback", 4);

		//List<BookSummary> result = bookRepo.findBooksOrderByTitle(10, 0);
		//System.out.println(result);

		// Optional<Book> opt = bookRepo.findBookById("c17062455");
		// if (opt.isPresent())
		// 	System.out.print(opt.get());
		// else
		// 	System.out.println("Not found");
	}

}
