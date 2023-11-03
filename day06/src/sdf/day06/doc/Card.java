package sdf.day06.doc;

// Value object - record
public record Card(String suit, String name, Integer value) { 

   public String display() {
      return "Suit: %s, Name: %s, Value: %d".formatted(suit(), name(), value());
   }
}