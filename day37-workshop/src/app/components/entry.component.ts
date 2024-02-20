import { Component, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {DiaryEntry} from '../models';
import {DiaryStore} from '../diary.store';

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrl: './entry.component.css'
})
export class EntryComponent implements OnInit {

  private fb = inject(FormBuilder)
  private diaryStore = inject(DiaryStore)

  protected form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  add() {
    const entry: DiaryEntry = {
      date: new Date(this.form.value['date']).getTime(),
      text: this.form.value['text'],
      entryType: this.form.value['entryType'],
    }
    console.info('>>> entry: ', entry)
    this.diaryStore.add(entry)
      .then(result => {
        this.form.reset()
      })
  }

  private createForm(): FormGroup {
    const t = new Date()
    return this.fb.group({
      date: this.fb.control<Date>((new Date())),
      text: this.fb.control<string>(''),
      entryType: this.fb.control<string>('others')
    })
  }

}
