import { Component, OnInit, inject } from '@angular/core';
import {WerewolfService} from '../werewolf.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {StartGameRequest} from '../models';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrl: './create-game.component.css'
})
export class CreateGameComponent implements OnInit {

  private router = inject(Router)
  private title = inject(Title)

  private werewolfSvc = inject(WerewolfService)

  gameId: string = 'not set'

  ngOnInit(): void {
    this.werewolfSvc.createGame()
        .then(resp => {
          this.gameId = resp.gameId
          this.title.setTitle(`Game Id: ${this.gameId}`)
        })
        .catch(resp => {
          alert(`${resp.error?.message}`)
          this.router.navigate(['/'])
        })
  }

  cancel() {
    this.werewolfSvc.deleteGame(this.gameId)
      .then(() => this.router.navigate(['/']))
  }

  startGame() {
    const req: StartGameRequest = {
      gameId: this.gameId,
      name: 'moderator',
      moderator: true
    }
    this.werewolfSvc.startGameAsModerator(this.gameId)
      .then(req => {
        console.info('>>> start as moderator: ', req)
      })
      .catch(err => {
        alert(`${err.error?.message}`)
      })
  }

}
