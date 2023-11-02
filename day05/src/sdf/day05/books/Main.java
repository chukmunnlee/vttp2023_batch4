package sdf.day05.books;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

   public static final int COL_TTILE = 1;
   public static final int COL_PUBLISHER = 11;

   public static void main(String[] args) throws Exception {

      if (args.length <= 0) {
         System.err.println("Missing book CSV");
         System.exit(1);
      }

      System.out.printf("Processing %s\n", args[0]);

      try (FileReader fr = new FileReader(args[0])) {
         BufferedReader br = new BufferedReader(fr);
         // br.readLine()
         Map<String, List<Book>> classfied = br.lines()
               .skip(1)
               // String -> String[]
               .map(row -> row.trim().split(","))
               // String[] -> Book
               .map(fields -> new Book(fields[COL_TTILE], fields[COL_PUBLISHER]))
               // groupingBy -> returns a value that classifies the input
               .collect(Collectors.groupingBy(book -> book.getPublisher()));

         for (String publisher: classfied.keySet()) {
            List<Book> books = classfied.get(publisher);
            System.out.printf("%s (%d)\n", publisher, books.size());
            for (Book book: books)
               System.out.printf("\t%s\n", book.getTitle());
         }
      }
   }
}