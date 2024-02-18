import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable, inject} from "@angular/core";
import {firstValueFrom} from "rxjs";

import {CreateGameResponse,  DeleteGameResponse, JoinGameRequest, JoinGameResponse} from "./models";

@Injectable()
export class WerewolfService {

  private http = inject(HttpClient)
  gameId = ''
  username = ''
  secret = 'abcd1234'

  hasJoined(): boolean {
    return !!this.gameId
  }

  createGame(): Promise<CreateGameResponse> {
    return firstValueFrom(
      this.http.post<CreateGameResponse>('/api/game', {})
    ).then(resp => {
      this.gameId = resp.gameId
      this.secret = resp.secret
      this.username = 'moderator'
      console.info(`>>> gameId: ${this.gameId}, secret: ${this.secret}`)
      return resp
    })
  }

  joinGame(req: JoinGameRequest): Promise<JoinGameResponse> {
    return firstValueFrom(
      this.http.post<JoinGameResponse>(`/api/game/${req.gameId}`, req)
    ).then(resp => {
      this.gameId = req.gameId
      this.username = req.username
      return resp
    })
  }

  deleteGame(gameId: string): Promise<DeleteGameResponse> {
    const headers = new HttpHeaders()
        .set('X-SECRET', this.secret)
    return firstValueFrom(
      this.http.delete<DeleteGameResponse>(`/api/game/${gameId}`, { headers })
    ).then(resp => {
      this.gameId = ''
      this.username = ''
      return resp
    })
  }
}
