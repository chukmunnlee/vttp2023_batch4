package vttp.ssf.day16.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import vttp.ssf.day16.models.Task;

@Service
public class TaskService {

   //@Autowired
   //private TaskRepository taskRepo;

   public Task save(Task task) {

      task.setId(UUID.randomUUID().toString());
      task.setCreateTime((new Date()).getTime());

      // Save to Redis
      // taskRepo.save(task);
      return task;
   }
   
}
