import { Component, OnInit, inject } from '@angular/core';
import {WerewolfService} from '../werewolf.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrl: './create-game.component.css'
})
export class CreateGameComponent implements OnInit {

  private router = inject(Router)
  private werewolfSvc = inject(WerewolfService)

  gameId: string = 'not set'

  ngOnInit(): void {
    this.werewolfSvc.createGame()
        .then(resp => {
          this.gameId = resp.gameId
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

}
