import {HttpClient} from "@angular/common/http";
import {Injectable, inject} from "@angular/core";
import {Observable} from "rxjs";
import {Task, TaskStatus, TaskSummary} from "./models";

@Injectable()
export class TaskService {

  private http = inject(HttpClient)

  getTaskSummaries(): Observable<TaskSummary[]> {
    return this.http.get<TaskSummary[]>('/api/tasks')
  }

  createTask(task: Task): Observable<TaskStatus> {
    return this.http.post<TaskStatus>('/api/task', task)
  }

  deleteTask(taskId: number): Observable<TaskStatus> {
    return this.http.delete<TaskStatus>(`/api/task/${taskId}`)
  }

  updateStatus(taskId: number, completed: boolean): Observable<TaskStatus> {
    return this.http.patch<TaskStatus>(`/api/task/${taskId}`, { completed })
  }
}
