package sdf.day05.boardgames;

public class Boardgame {

   private String name;
   private String year;
   private Integer minTime;
   private Integer maxTime;

   public Boardgame(String name, String year, Integer minTime, Integer maxTime) {
      this.name = name;
      this.year = year;
      this.minTime = minTime;
      this.maxTime = maxTime;
   }
   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getYear() { return year; }
   public void setYear(String year) { this.year = year; }

   public Integer getMinTime() { return minTime; }
   public void setMinTime(Integer minTime) { this.minTime = minTime; }

   public Integer getMaxTime() { return maxTime; }
   public void setMaxTime(Integer maxTime) { this.maxTime = maxTime; }
   
   public Integer getDuration() {
      int dur = maxTime - minTime;
      if (0 == dur) {
         double v = Math.floor((maxTime + minTime)/2);
         dur = (int)v;
      }
      return dur;
   }
}
