package vttp2023.batch4.paf.day21.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch4.paf.day21.models.Book;
import vttp2023.batch4.paf.day21.models.BookSummary;
import vttp2023.batch4.paf.day21.repositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping
public class BookController {

   @Autowired 
   private BookRepository bookRepo;

   // GET /, GET /index.html
   @GetMapping(path = {"/", "/index.html"})
   public ModelAndView getIndex(
      @RequestParam(defaultValue = "10") int limit,
      @RequestParam(defaultValue = "0") int offset) {

      List<BookSummary> results = bookRepo.findBooksOrderByTitle(limit, offset);

      ModelAndView mav = new ModelAndView("index");
      mav.setStatus(HttpStatusCode.valueOf(200));
      mav.addObject("books", results);

      return mav;
   }
   
   // GET /book/{bookId}
   @GetMapping("/book/{bookId}")
   public ModelAndView getBookById(@PathVariable String bookId) {

      Optional<Book> opt = bookRepo.findBookById(bookId);
      // Note: need to check opt.isPresent()
      Book book = opt.get();

      ModelAndView mav = new ModelAndView("book-detail");
      mav.setStatus(HttpStatusCode.valueOf(200));
      mav.addObject("book", book);

      return mav;
   }

   @GetMapping(path="/book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody 
   public ResponseEntity<String> getBookByIdAsJson(@PathVariable String bookId) {
      Optional<Book> opt = bookRepo.findBookById(bookId);
      // Note: need to check opt.isPresent()
      Book book = opt.get();

      // Create the JSON object
      String json = "{ \"title\": \"" + book.getTitle() + "\" }";

      return ResponseEntity.ok(json);

   }
   
}
