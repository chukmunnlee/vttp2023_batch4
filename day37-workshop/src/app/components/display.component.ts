import { Component, OnInit, inject } from '@angular/core';
import {DiaryStore} from '../diary.store';
import {Observable, map} from 'rxjs';
import {DiaryEntry} from '../models';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrl: './display.component.css'
})
export class DisplayComponent implements OnInit {

  diaryStore = inject(DiaryStore)
  entries$!: Observable<DiaryEntry[]>

  ngOnInit(): void {
    this.entries$ = this.diaryStore.onEntries.asObservable()
        //.pipe(
        //  map(e => e.map(v => ({ text: v.text, date: v.date}))
        //)
  }

}
