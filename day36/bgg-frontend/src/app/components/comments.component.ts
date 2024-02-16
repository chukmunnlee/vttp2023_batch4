import { Component, OnInit, inject } from '@angular/core';
import { BGGService } from '../bgg.service';
import { Observable } from 'rxjs';
import { Comment } from '../models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.css'
})
export class CommentsComponent implements OnInit {

  private bggSvc = inject(BGGService)
  private activatedRoute = inject(ActivatedRoute)

  comments$!: Observable<Comment[]>

  ngOnInit(): void {
    const gameId = this.activatedRoute.snapshot.params['gameId']
    this.comments$ = this.bggSvc.getCommentsByGameId(gameId)
  }

}
