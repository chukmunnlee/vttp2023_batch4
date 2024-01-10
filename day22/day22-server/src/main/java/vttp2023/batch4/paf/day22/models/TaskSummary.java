package vttp2023.batch4.paf.day22.models;

public class TaskSummary {

	private int taskId;
	private String title;
	private boolean completed;

	public void setTaskId(int taskId) { this.taskId = taskId; }
	public int getTaskId() { return this.taskId; }

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setCompleted(boolean completed) { this.completed = completed; }
	public boolean getCompleted() { return this.completed; }
	public boolean isCompleted() { return this.completed; }
	@Override
	public String toString() {
		return "TaskSummary [taskId=" + taskId + ", title=" + title + ", completed=" + completed + "]";
	}

}
