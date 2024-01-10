package vttp2023.batch4.paf.day22;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch4.paf.day22.models.TaskSummary;

public class Utils {

	public static TaskSummary toTaskSummary(SqlRowSet rs) {
		TaskSummary t = new TaskSummary();
		t.setTaskId(rs.getInt("task_id"));
		t.setTitle(rs.getString("title"));
		t.setCompleted(rs.getBoolean("completed"));
		return t;
	}

	public static JsonObject toJson(TaskSummary t) {
		return Json.createObjectBuilder()
			.add("taskId", t.getTaskId())
			.add("title", t.getTitle())
			.add("completed", t.isCompleted())
			.build();
	}
}
