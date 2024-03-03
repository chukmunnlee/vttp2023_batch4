import { Component, OnInit, inject } from '@angular/core';
import {WerewolfService} from '../werewolf.service';

@Component({
  selector: 'app-moderator',
  templateUrl: './moderator.component.html',
  styleUrl: './moderator.component.css'
})
export class ModeratorComponent implements OnInit {

  private werewolfSvc = inject(WerewolfService)

  ngOnInit(): void {
    this.werewolfSvc.getPlayers()
      .then(players => {
        console.info('>>> players: ', players)
      })

  }

}
