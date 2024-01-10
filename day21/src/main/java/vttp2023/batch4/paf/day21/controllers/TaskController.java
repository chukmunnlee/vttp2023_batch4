package vttp2023.batch4.paf.day21.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/api")
public class TaskController {
	@GetMapping("/tasks")
	public ResponseEntity<String> getTasks() {
		String data = """
			[
				{ "taskId": 123, "title": "Task 1", "completed": false },
				{ "taskId": 890, "title": "Task 2", "completed": true }
			]
		""";
		return ResponseEntity.ok(data);
	}
}
