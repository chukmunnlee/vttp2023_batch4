package vttp2023.batch4.paf.day22.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch4.paf.day22.models.TaskSummary;
import vttp2023.batch4.paf.day22.repositories.TaskRepository;

@Service
public class TaskService {

   @Autowired
   private TaskRepository taskRepo;

	public boolean updateTaskStatus(int taskId, boolean completed) {
      return this.taskRepo.updateTaskStatus(taskId, completed);
   }

   public List<TaskSummary> getTasksAsSummaries() {
      return this.taskRepo.getTasksAsSummaries();
   }

}