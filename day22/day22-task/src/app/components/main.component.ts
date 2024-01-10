import { Component, OnInit, inject } from '@angular/core';
import {TaskService} from '../task.service';
import {Observable, Subject, firstValueFrom, from, tap} from 'rxjs';
import {TaskSummary} from '../models';
import {FormArray} from '@angular/forms';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  private taskSvc = inject(TaskService)

  tasks$!: Observable<TaskSummary[]>

  ngOnInit(): void {
    this.tasks$ = this.taskSvc.getTaskSummaries()
  }

  deleteTask(taskId: number) {
    this.taskSvc.deleteTask(taskId)
      .then(() => {
        this.tasks$ = this.taskSvc.getTaskSummaries()
      })
      .catch(error => {
        console.error('error: ', error)
      })
  }

  onChange(e: Event) {
    // @ts-ignore
    const checked: boolean = !!e.target.checked
    // @ts-ignore
    const taskId = e.srcElement.dataset.taskid
    console.info(`>>>> checked: ${checked}, taskId: ${taskId}`)
    firstValueFrom(this.taskSvc.updateStatus(taskId, checked))
      .then(result => {
        console.info(result)
      })
      .catch(error => {
        console.error('error: ', error)
      })
  }

}
