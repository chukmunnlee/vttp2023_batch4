import { Component, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';

import { Todo } from '../models'

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrl: './entry.component.css'
})
export class EntryComponent implements OnInit {

  private fb = inject(FormBuilder)

  protected form!: FormGroup

  ngOnInit(): void {
    this.form = this.fb.group({
      date: this.fb.control<string>(''),
      task: this.fb.control<string>(''),
      priority: this.fb.control<number>(3),
    })
  }

  process() {

    const task = this.form.value as Todo

    console.info('>> todo: ', task)

    this.form.reset()

  }

}
