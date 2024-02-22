export interface Todo {
  date: string
  task: string
  priority: number
}

// DB/Store
export interface TodoSlice {
  loadedOn: number
  todos: Todo[]
}
