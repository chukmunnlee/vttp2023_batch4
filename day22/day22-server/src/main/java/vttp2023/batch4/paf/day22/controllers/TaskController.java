package vttp2023.batch4.paf.day22.controllers;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2023.batch4.paf.day22.Utils;
import vttp2023.batch4.paf.day22.models.TaskSummary;
import vttp2023.batch4.paf.day22.services.TaskService;



@Controller
@RequestMapping("/api")
public class TaskController {

   @Autowired
   private TaskService taskSvc;

   @PatchMapping("/task/{taskId}")
   @ResponseBody
   public ResponseEntity<String> patchTask(@PathVariable int taskId, @RequestBody String payload) {

      System.out.printf(">>> patch: %s\n", payload);

      JsonReader r = Json.createReader(new StringReader(payload));
      JsonObject j = r.readObject();

      boolean result = taskSvc.updateTaskStatus(taskId, j.getBoolean("completed"));
      System.out.printf(">>> after patch: %b\n", result);

      return ResponseEntity.ok("{}");
   }

   @GetMapping("/tasks")
   @ResponseBody
   public ResponseEntity<String> getTasks() {

      List<TaskSummary> results = taskSvc.getTasksAsSummaries();

      JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      results.stream()
         .forEach(t -> arrBuilder.add(Utils.toJson(t)));

      return ResponseEntity.ok(arrBuilder.build().toString());
   }
   
}
