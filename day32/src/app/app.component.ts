import { Component } from '@angular/core';
import { Todo } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  todos: Todo[] = []

  newTodo(todo: Todo) {
    this.todos.push(todo)
  }

}
