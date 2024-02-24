import { Component, OnInit, inject } from '@angular/core';
import {Router} from '@angular/router';

import {Title} from '@angular/platform-browser';
import {Observable, tap} from 'rxjs';

import {WerewolfStore} from '../werewolf.store';
import {Game, StartGameRequest} from '../models';
import {WerewolfService} from '../werewolf.service';

@Component({
  selector: 'app-pre-start',
  templateUrl: './pre-start.component.html',
  styleUrl: './pre-start.component.css'
})
export class PreStartComponent implements OnInit {

  private router = inject(Router)
  private title = inject(Title)
  private store = inject(WerewolfStore)
  private werewolfSvc = inject(WerewolfService)

  private game!: Game
  protected game$!: Observable<Game>

  ngOnInit(): void {
    this.game$ = this.store.game$
      .pipe(
        tap(game => {
          this.title.setTitle(`Game Id: ${game.gameId}`)
          this.game = game
        })
      )
  }

  startGame() {
    const { gameId, name } = this.game
    const req: StartGameRequest = {
      gameId, name,
      moderator: false
    }
    this.werewolfSvc.startGameAsPlayer(req)
      .then(req => {
        console.info('>>> start as player: ', req)
      })
      .catch(err => {
        alert(`${err.error?.message}`)
      })
  }

  leaveGame() {
  }

}
