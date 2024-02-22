import { Injectable } from "@angular/core";
import { ComponentStore } from "@ngrx/component-store";
import { Todo, TodoSlice } from "./models";

const INIT_STORE: TodoSlice = {
  loadedOn: 0,
  todos: []
}

@Injectable()
export class TodoStore extends ComponentStore<TodoSlice> {

  constructor() { super(INIT_STORE) }

  // Selectors
  readonly getTodos = this.select<Todo[]>(
    (slice: TodoSlice) => slice.todos
  )

  readonly getNumberOfTodos = this.select<number>(
    (slice: TodoSlice) => slice.todos.length
  )

  readonly getTodoByPriority = (priority: number) => {
    return this.select<Todo[]>(
      (slice: TodoSlice) => {
        return slice.todos.filter(todo => todo.priority == priority)
      }
    )
  }

  // Mutators
  readonly deleteTaskByDate = this.updater<string>(
    (slice: TodoSlice, date: string) => {
      return {
        loadedOn: slice.loadedOn,
        todos: slice.todos.filter(todo => date != todo.date)
      }
    }
  )

  readonly addTodo = this.updater<Todo>(
    (slice: TodoSlice, todo: Todo) => {
      /*
      const newTodos: Todo[] = []
      for (let t of slice.todos)
        newTodos.push(t)
      newTodos.push(todo)
      return {
        loadedOn: slice.loadedOn,
        todos: newTodos
      }
      */
      return {
        loadedOn: slice.loadedOn,
        todos: [ ...slice.todos, todo ]
      }
    }
  )

}
