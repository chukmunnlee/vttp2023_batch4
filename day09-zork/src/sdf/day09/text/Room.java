package sdf.day09.text;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Room {

   private final String roomId;
   private String name;
   private String description;
   private Map<String, String> directions = new HashMap<>();
   private List<String> items = new LinkedList<>();

   public Room(String roomId) { this.roomId = roomId; }
   public Room(String roomId) { this.roomId = roomId; }
   public Room(String roomId, String name) { 
		this.roomId = roomId; 
		this.name = name;
	}


   public String getRoomId() { return roomId; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getDescription() { return description; }
   public void setDescription(String description) { this.description = description; }

   public void setDirection(String dir, String roomId) {
      directions.put(dir, roomId);
   }
   public boolean isAccessible(String dir) {
      return directions.containsKey(dir);
   }
   // This method is a bit dangerous
   public Optional<String> getRoom(String dir) {
      if (isAccessible(dir))
         return Optional.of(directions.get(dir));
      return Optional.empty();
   }

   public void putItem(String item) {
      this.items.add(item);
   }
   // what is the danger of this method
   public List<String> getItems() {
      return Collections.unmodifiableList(this.items);
   }
   public Optional<String> takeItem(String item) {
      List<String> newList = this.items.stream()
         .filter(i -> !item.equals(i))
         .toList();
      if (newList.size() != items.size()) {
         items = newList;
         return Optional.of(item);
      }
      return Optional.empty();
   }
}
