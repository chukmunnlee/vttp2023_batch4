import { Component, OnInit, inject } from '@angular/core';
import { BGGService } from '../bgg.service';
import { Observable } from 'rxjs';
import { Game } from '../models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.css'
})
export class GameListComponent implements OnInit {

  private bggSvc = inject(BGGService)
  private router = inject(Router)

  gameList$!: Observable<Game[]>

  ngOnInit(): void {
    this.gameList$ = this.bggSvc.getGameList()
  }

  showComments(gid: number) {
    this.router.navigate([ '/comments', gid ])
  }

}
