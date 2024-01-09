package vttp2023.batch4.paf.day21.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
   
   @Autowired
   private JdbcTemplate template;

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

      SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_BOOK_BY_TITLE
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
