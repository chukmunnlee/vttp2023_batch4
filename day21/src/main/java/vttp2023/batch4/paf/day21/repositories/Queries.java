package vttp2023.batch4.paf.day21.repositories;

public class Queries {

   public static final String SQL_SELECT_BOOK_BY_TITLE = """
      select book_id, title, authors, description, pages, rating, image_url
         from book2018
         where title like ?
         limit ? offset ?
   """;

   public static final String SQL_SELECT_BOOK_BY_FORMAT_AND_RATING = """
      select book_id, title, authors, description, pages, rating, image_url
         from book2018
         where format = ? and rating > ?
   """;
   
}
