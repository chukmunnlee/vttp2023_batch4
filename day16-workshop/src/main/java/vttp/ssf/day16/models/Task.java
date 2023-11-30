package vttp.ssf.day16.models;

import java.io.StringReader;
import java.util.Optional;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Task {
   private String id;
   private Long createTime;
   private String name;
   private String description;
   private Integer prority;

   public String getId() { return id; }
   public void setId(String id) { this.id = id; }

   public Long getCreateTime() { return createTime; }
   public void setCreateTime(Long createTime) { this.createTime = createTime; }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getDescription() { return description; }
   public void setDescription(String description) { this.description = description; }

   public Integer getPrority() { return prority; }
   public void setPrority(Integer prority) { this.prority = prority; }

   public static Optional<Task> toTask(String payload) {

      JsonReader reader = Json.createReader(new StringReader(payload));
      JsonObject j = reader.readObject();

      Task task = new Task();
      task.setName(j.getString("name", ""));
      task.setDescription(j.getString("description", ""));
      task.setPrority(
         Integer.parseInt(j.getString("priority", "-1")));

      return Optional.of(task);
   }
   
}
