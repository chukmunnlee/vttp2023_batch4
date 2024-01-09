package vttp2023.batch4.paf.day21;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import vttp2023.batch4.paf.day21.models.Book;
import vttp2023.batch4.paf.day21.models.BookSummary;

public class Utils {

   public static BookSummary toBookSummary(SqlRowSet rs) {
      BookSummary b = new BookSummary();
      b.setBookId(rs.getString("book_id"));
      b.setTitle(rs.getString("title"));
      return b;
   }

   public static Book toBook(SqlRowSet rs) {
      Book b = new Book();
      b.setBookId(rs.getString("book_id"));
      b.setTitle(rs.getString("title"));
      b.setAuthors(rs.getString("authors"));
      b.setDescription(rs.getString("description"));
      b.setPages(rs.getInt("pages"));
      b.setRating(rs.getFloat("rating"));
      b.setImage(rs.getString("image_url"));
      return b;
   }
   
}
