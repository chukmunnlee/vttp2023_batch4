package vttp2023.batch4.paf.day21.models;

public class Book {

   // book_id, title, authors, description, pages, rating, image_url

   private String bookId;
   private String title;
   private String authors;
   private String description;
   private int pages;
   private float rating;
   private String image;

   public String getBookId() { return bookId; }
   public void setBookId(String bookId) { this.bookId = bookId; }

   public String getTitle() { return title; }
   public void setTitle(String title) { this.title = title; }

   public String getAuthors() { return authors; }
   public void setAuthors(String authors) { this.authors = authors; }

   public String getDescription() { return description; }
   public void setDescription(String description) { this.description = description; }

   public int getPages() { return pages; }
   public void setPages(int pages) { this.pages = pages; }

   public float getRating() { return rating; }
   public void setRating(float rating) { this.rating = rating; }

   public String getImage() { return image; }
   public void setImage(String image) { this.image = image; }

   @Override
   public String toString() {
      return "Book [bookId=" + bookId + ", title=" + title + ", authors=" + authors + ", description=" + description
            + ", pages=" + pages + ", rating=" + rating + ", image=" + image + "]";
   }

}
