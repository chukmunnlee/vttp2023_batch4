package sdf.day05.boardgames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

   public static final int COL_NAME = 0;
   public static final int COL_YEAR = 1;
   public static final int COL_MIN = 2;
   public static final int COL_MAX = 3;

   public static final String[] LABELS = { 
      "30 - 60", "60 - 120", "120 - 180", "180 -"
   };

   public static void main(String[] args) throws IOException {

      try (FileReader fr = new FileReader(args[0])) {
         BufferedReader br = new BufferedReader(fr);
         Map<Integer, List<Boardgame>> categorized = br.lines()
            .skip(1)
            .map(line -> line.trim().split(","))
            .map(columns -> new Boardgame(columns[COL_NAME]
               , columns[COL_YEAR]
               , Integer.parseInt(columns[COL_MIN])
               , Integer.parseInt(columns[COL_MAX])))
            .collect(Collectors.groupingBy(game -> {
               int dur = game.getDuration();
               if ((dur >= 30) && (dur < 60))
                  return 0;
               else if ((dur >= 60) && (dur < 120))
                  return 1;
               else if ((dur >= 120) && (dur < 180))
                  return 2;
               return 3;
            }));

         for (int i = 0; i < LABELS.length; i++) {
            System.out.printf("Duration: %s mins\n", LABELS[i]);
            for (Boardgame bg: categorized.get(i))
               System.out.printf("  %s: %s\n", bg.getName(), bg.getYear());
         }
      }
   }

}