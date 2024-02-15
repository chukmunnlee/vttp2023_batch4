import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription, map, switchMap, tap } from 'rxjs';
import { BoardgameService } from './boardgame.service';
import { Game, Comment } from './models';

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
  comments$!: Observable<Comment[] | undefined>
  searchP$!: Promise<Game | undefined>
  commentsP$!: Promise<Comment[] | undefined>

  ngOnInit(): void {
    this.form = this.createForm()
  }

  ngOnDestroy(): void { }

  processForm() {
    const searchValue: string = this.form.value['search']
    console.info('>>> value: ', searchValue)
    // this.commentsP$ = this.bgSvc.searchBoardgamePromise(searchValue)
    //     .then(result => {
    //       if (result.length > 0)
    //         return result[0]
    //       return undefined
    //     })
    //     .then(result => {
    //       if (!!result)
    //         return this.bgSvc.searchCommentsPromise(result.gameId)
    //       return result
    //     })
    //     .then(result => {
    //       console.info('comments: ', result)
    //       return result
    //     })
    this.comments$ = this.bgSvc.searchBoardgame(searchValue)
      .pipe(
        // @ts-ignore
        switchMap((value: Game[]) => {
          if (value.length < 0)
            return undefined
          const g = value[0]
          // Observable<Comment[]>
          return this.bgSvc.searchComments(g.gameId)
        }),
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
