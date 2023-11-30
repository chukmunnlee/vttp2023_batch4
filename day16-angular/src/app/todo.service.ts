import {Injectable, inject} from "@angular/core";
import {Task, TaskResponse} from "./models";
import {Observable, map} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class TodoService {

  private http = inject(HttpClient)

  addTask(task: Task): Observable<TaskResponse> {
    return this.http.post<TaskResponse>('/api/task', task)

  }
}
