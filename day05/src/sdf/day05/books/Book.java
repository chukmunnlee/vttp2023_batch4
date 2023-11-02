package sdf.day05.books;

public class Book {

   private String title;
   private String publisher;

   public Book(String title, String publisher) {
      this.title = title;
      this.publisher = publisher;
   }

   public String getTitle() { return title; }
   public void setTitle(String title) { this.title = title; }

   public String getPublisher() { return publisher; }
   public void setPublisher(String publisher) { this.publisher = publisher; }
   
}
