import { Component, OnInit, inject } from '@angular/core';
import { TodoStore } from '../todo.store';
import { Observable } from 'rxjs';
import { Todo, TodoSlice } from '../models';

@Component({
  selector: 'app-listings',
  templateUrl: './listings.component.html',
  styleUrl: './listings.component.css'
})
export class ListingsComponent implements OnInit {

  private todoStore = inject(TodoStore)

  protected todos$!: Observable<Todo[]>

  ngOnInit(): void {
    //this.todos$ = this.todoStore.getTodos
    this.todos$ = this.todoStore.getTodoByPriority(3)
    // this.todos$ = this.todoStore.select(
    //   (slice: TodoSlice) => slice.todos
    // )
  }

  deleteTask(date: string) {
    this.todoStore.deleteTaskByDate(date)
  }

}
