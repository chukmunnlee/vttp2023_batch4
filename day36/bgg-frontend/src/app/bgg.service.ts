import { Injectable, inject } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Game, Comment } from "./models";
import { Observable } from "rxjs";

@Injectable()
export class BGGService {

  private http = inject(HttpClient)

  getGameList(limit: number = 20, offset: number = 0): Observable<Game[]> {
    const params = new HttpParams()
        .set('limit', limit)
        .set('offset', offset)
    return this.http.get<Game[]>('http://localhost:8080/api/games', { params })
  }

  getCommentsByGameId(gameId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`http://localhost:8080/api/game/${gameId}/comments`)

  }

}
