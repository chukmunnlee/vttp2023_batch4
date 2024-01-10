package vttp2023.batch4.paf.day22.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day22.Utils;
import vttp2023.batch4.paf.day22.models.TaskSummary;

@Repository
public class TaskRepository {

	@Autowired
	private JdbcTemplate template;

	public List<TaskSummary> getTasksAsSummaries() {

		SqlRowSet rs = template.queryForRowSet(Queries.SQL_SELECT_TASK_AS_SUMMARY);
		List<TaskSummary> results = new LinkedList<>();
		while (rs.next())
			results.add(Utils.toTaskSummary(rs));
		return results;
	}
}
