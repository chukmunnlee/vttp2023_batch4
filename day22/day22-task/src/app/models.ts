export interface TaskSummary {
  taskId: number
  title: string
  completed: boolean
}

export interface Task {
  taskId: number
  title: string
  dueDate: number
  priority: number
  completed: boolean
}

export interface TaskStatus {
  message: string
  status: boolean
}
