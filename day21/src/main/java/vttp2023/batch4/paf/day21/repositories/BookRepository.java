package vttp2023.batch4.paf.day21.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day21.models.Book;
import vttp2023.batch4.paf.day21.models.BookSummary;
import vttp2023.batch4.paf.day21.Utils;

@Repository
public class BookRepository {
   
   @Autowired
   private JdbcTemplate template;

   public List<BookSummary> findBooksOrderByTitle(int limit, int offset) {

      SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOKS_ORDER_BY_TITLE
            , limit, offset);  

      List<BookSummary> books = new LinkedList<>();
      while (rs.next())
         books.add(Utils.toBookSummary(rs));

      return books;
   }

   public Optional<Book> findBookById(String bookId) {
      SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_ID , bookId);

      if (!rs.next())
         return Optional.empty();

      return Optional.of(Utils.toBook(rs));
   }

   public void findBooksByFormatAndRating(String format, float rating) {

      SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_FORMAT_AND_RATING
            , format, rating);  

      while (rs.next()) {
         String bookId = rs.getString("book_id");
         String title = rs.getString("title");
         String image = rs.getString("image_url");
         int pages = rs.getInt("pages");
         float _rating = rs.getFloat("rating");
         System.out.printf("%s, %s, %d, %.3f, %s\n", bookId, title, pages, _rating, image);
      }

      System.out.println("\n\nQuery completed");
   }

   public void findBooksByTitle(String keyword) {

      String toSearch = "%" + keyword + "%";

      SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOKS_BY_TITLE
            , toSearch, 10, 0);

      while (rs.next()) {
         String bookId = rs.getString("book_id");
         String title = rs.getString("title");
         String image = rs.getString("image_url");
         int pages = rs.getInt("pages");
         float rating = rs.getFloat("rating");
         System.out.printf("%s, %s, %d, %.3f, %s\n", bookId, title, pages, rating, image);
      }

      System.out.println("\n\nQuery completed");

   }
}
