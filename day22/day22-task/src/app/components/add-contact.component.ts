import { Component, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TaskService} from '../task.service';
import {CreateTask} from '../models';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrl: './add-contact.component.css'
})
export class AddContactComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private taskSvc = inject(TaskService)

  form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  process() {
    const newTask: CreateTask = {
      title: this.form.value['title'],
      dueDate: new Date(this.form.value['dueDate']).getTime(),
      priority: this.form.value['priority'],
    }

    this.taskSvc.createTask(newTask)
      .then(result => {
        this.router.navigate(['/'])
      })
      .catch(error => {
        alert(`Error: ${JSON.stringify(error)}`)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      title: this.fb.control<string>('', [ Validators.required ]),
      dueDate: this.fb.control<number>((new Date()).getTime(), [ Validators.required ]),
      priority: this.fb.control<number>(3, [Validators.min(1), Validators.max(5) ])
    })
  }

}
