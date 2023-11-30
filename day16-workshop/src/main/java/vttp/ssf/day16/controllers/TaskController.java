package vttp.ssf.day16.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.ssf.day16.models.Task;
import vttp.ssf.day16.services.TaskService;

@RestController
@RequestMapping(path="/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

   @Autowired
   private TaskService taskSvc;

   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> postTask(@RequestBody String payload) {

      // Note should check if JSON payload is correct
      Optional<Task> opt = Task.toTask(payload);

      JsonObject resp;

      if (opt.isEmpty()) {
         resp = Json.createObjectBuilder()
               .add("error", "Incorrect task payload")
               .build();
         return ResponseEntity.badRequest().body(resp.toString());
      }

      Task task = opt.get();
      task = taskSvc.save(task);

      resp = Json.createObjectBuilder()
            .add("id", task.getId())
            .add("createTime", task.getCreateTime())
            .build();

      return ResponseEntity.ok(resp.toString());
   }

   
}