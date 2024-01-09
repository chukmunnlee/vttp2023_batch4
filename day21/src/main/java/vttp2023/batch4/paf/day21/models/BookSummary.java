package vttp2023.batch4.paf.day21.models;

public class BookSummary {

   private String bookId;
   private String title;

   public String getBookId() { return bookId; }
   public void setBookId(String bookId) { this.bookId = bookId; }

   public String getTitle() { return title; }
   public void setTitle(String title) { this.title = title; }

   @Override
   public String toString() {
      return "BookSummary [bookId=" + bookId + ", title=" + title + "]";
   }
}
