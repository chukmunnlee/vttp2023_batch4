export interface Task {
  task: string
  priority: number
  completed: boolean
}

export interface Todo {
  title: string
  email: string
  completedBy: string
  comments: string
  tasks: Task[]
}
