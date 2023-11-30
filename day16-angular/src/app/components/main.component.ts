import { Component, OnInit, inject  } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {TodoService} from '../todo.service';
import { ulid } from 'ulid'
import {Task, TaskResponse} from '../models';
import {Observable, Subject, tap} from 'rxjs';
import {AsyncPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [ ReactiveFormsModule, AsyncPipe, DatePipe ],
  providers: [ TodoService ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  private fb = inject(FormBuilder)
  private todoSvc = inject(TodoService)

  form!: FormGroup
  result$!: Observable<TaskResponse>

  ngOnInit(): void {
    this.form = this.createForm()
  }

  processForm() {
    const task: Task = {
      name: this.form.value['name'],
      description: this.form.value['description'],
      priority: this.form.value['priority']
    }
    this.result$ = this.todoSvc.addTask(task)
        .pipe(
          tap(() => this.form = this.createForm())
        )
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      description: this.fb.control<string>(''),
      priority: this.fb.control<number>(-1, [Validators.required, Validators.min(0), Validators.max(2)])
    })
  }

}
