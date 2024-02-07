import { Component, OnInit, Output, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Todo } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent implements OnInit {

  private fb: FormBuilder = inject(FormBuilder)
  todoForm!: FormGroup
  taskArray!: FormArray

  @Output()
  newTodo = new Subject<Todo>()

  ngOnInit(): void {
    this.todoForm = this.createTodoForm()
  }

  processForm() {
    const todo: Todo = this.todoForm.value
    console.log("process form", todo)
    this.newTodo.next(todo)
    //this.todoForm.reset()
    this.todoForm = this.createTodoForm()
  }

  addTask() {
    this.taskArray.push(this.createTaskFrom())
  }
  deleteTask(i: number) {
    this.taskArray.removeAt(i)
  }

  private createTaskFrom(): FormGroup {
    return this.fb.group({
      task: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      priority: this.fb.control<number>(1),
      completed: this.fb.control<boolean>(false),
    })
  }

  isTodoInvalid(): boolean {
    return this.todoForm.invalid || this.taskArray.length <= 0
  }

  private createTodoForm(): FormGroup {
    //this.taskArray = this.fb.array([], [ Validators.required, Validators.minLength(1) ])
    this.taskArray = this.fb.array([])
    return this.fb.group({
      title: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control<string>('fred@gmail.com', [ Validators.required, Validators.email ]),
      completedBy: this.fb.control<string>('', [ Validators.required ]),
      comments: this.fb.control<string>(''),
      tasks: this.taskArray
    })
  }

  // @Autowired/dependency injection
  //constructor(private fb: FormBuilder) { }
}
