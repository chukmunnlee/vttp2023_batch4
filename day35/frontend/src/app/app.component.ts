import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription, map, tap } from 'rxjs';
import { BoardgameService } from './boardgame.service';
import { Game } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy {

  private fb = inject(FormBuilder)
  private bgSvc = inject(BoardgameService)

  form!: FormGroup
  search$!: Observable<Game[]>

  ngOnInit(): void {
    this.form = this.createForm()
  }

  ngOnDestroy(): void {
  }

  processForm() {
    const searchValue: string = this.form.value['search']
    console.info('>>> value: ', searchValue)
    this.search$ = this.bgSvc.searchBoardgame(searchValue)
      .pipe(
        tap(value => {
          console.info('>>> value: ', value)
        })
      )
  }

  private createForm(): FormGroup {
    return this.fb.group({
      search: this.fb.control<string>('', [ Validators.required ]),
    })
  }
}
