package vttp2023.batch4.paf.day22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2023.batch4.paf.day22.Utils;
import vttp2023.batch4.paf.day22.models.TaskSummary;
import vttp2023.batch4.paf.day22.services.TaskService;



@Controller
@RequestMapping("/api")
public class TaskController {

   @Autowired
   private TaskService taskSvc;

   @GetMapping("/tasks")
   @ResponseBody
   public ResponseEntity<String> getMethodName() {

      List<TaskSummary> results = taskSvc.getTasksAsSummaries();

      JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
      results.stream()
         .forEach(t -> arrBuilder.add(Utils.toJson(t)));

      return ResponseEntity.ok(arrBuilder.build().toString());
   }
   
}
