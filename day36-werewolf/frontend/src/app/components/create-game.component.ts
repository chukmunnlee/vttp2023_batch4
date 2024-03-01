import { Component, OnInit, inject } from '@angular/core';
import {WerewolfService} from '../werewolf.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {Observer, Subject, from, of, startWith} from 'rxjs';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrl: './create-game.component.css'
})
export class CreateGameComponent implements OnInit {

  private router = inject(Router)
  private title = inject(Title)

  private werewolfSvc = inject(WerewolfService)

  protected gameId: string = 'not set'

  protected playerCount$!: Promise<number>
  protected invalid!: boolean
  protected canShare: boolean = false

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
    this.playerCount$ = Promise.resolve(0)
    this.invalid = true
    this.canShare = !!navigator.share
  }

  cancel() {
    this.werewolfSvc.deleteGame(this.gameId)
      .then(() => this.router.navigate(['/']))
  }

  startGame() {
    this.werewolfSvc.startGameAsModerator()
      .then(req => {
        console.info('>>> start as moderator: ', req)
        this.router.navigate([ '/moderator' ])
      })
      .catch(err => {
        alert(`${err.error?.message}`)
      })
  }

  getPlayersCount() {
    this.playerCount$ = this.werewolfSvc.getNumberOfPlayers()
        .then(count => {
          this.invalid = count < 7
          return count
        })
  }

  shareGameId() {
    const url = window.location.origin + this.router.parseUrl(`/#/join?gameId=${this.gameId}`)
    const data = {
      title: 'Werewolf',
      text: 'Click on the URL to join the game',
      url
    }
    navigator.share(data)
      .catch(err => alert(`Cannot share.\nError ${JSON.stringify(err)}`))
  }

}
