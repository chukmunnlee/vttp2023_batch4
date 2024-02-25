import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable, inject} from "@angular/core";
import {firstValueFrom} from "rxjs";

import {
  CreateGameResponse,  DeleteGameResponse, JoinGameRequest, JoinGameResponse,
  PlayerCountResponse, StartGameRequest, StartGameResponse
} from "./messages";

import { Game, GameStatus, GameRole } from "./models"
import {WerewolfStore} from "./werewolf.store";

@Injectable()
export class WerewolfService {

  private http = inject(HttpClient)
  private store = inject(WerewolfStore)

  gameId = ''
  username = ''
  secret = 'abcd1234'

  hasJoined(): boolean {
    return !!this.gameId
  }

  getNumberOfPlayers(): Promise<number> {
    return firstValueFrom(
      this.http.get<PlayerCountResponse>(`/api/game/${this.gameId}/players/count`)
    ).then(resp => resp.count)
  }

  createGame(): Promise<CreateGameResponse> {
    return firstValueFrom(
      this.http.post<CreateGameResponse>('/api/game', {})
    ).then(resp => {
      this.gameId = resp.gameId
      this.secret = resp.secret
      this.username = 'moderator'
      console.info(`>>> gameId: ${this.gameId}, secret: ${this.secret}`)
      this.store.newGame({
        gameId: this.gameId,
        secret: this.secret,
        moderator: true,
        name: this.username,
        role: GameRole.Moderator,
        status: GameStatus.PreStart
      } as Game)
      return resp
    })
  }

  joinGame(req: JoinGameRequest): Promise<JoinGameResponse> {
    return firstValueFrom(
      this.http.post<JoinGameResponse>(`/api/game/${req.gameId}`, req)
    ).then(resp => {
      this.gameId = req.gameId
      this.username = req.username
      this.store.newGame({
        gameId: this.gameId,
        secret: "",
        moderator: false,
        name: this.username,
        role: GameRole.None,
        status: GameStatus.PreStart
      } as Game)
      return resp
    })
  }

  startGameAsModerator(): Promise<StartGameResponse> {
    const headers = new HttpHeaders()
        .set('X-SECRET', this.secret)
      const req: StartGameRequest = {
        gameId: this.gameId,
        name: 'moderator',
        moderator: true
      }
    return firstValueFrom(
      this.http.post<StartGameResponse>(`/api/game/${req.gameId}/moderator`, req, { headers })
    )
  }

  startGameAsPlayer(): Promise<StartGameResponse> {
    const req: StartGameRequest = {
      gameId: this.gameId,
      name: this.username,
      moderator: false
    }
    return firstValueFrom(
      this.http.post<StartGameResponse>(`/api/game/${req.gameId}/${req.name}`, req)
    )
  }

  leaveGame(): Promise<any> {
    return firstValueFrom(
      this.http.delete<any>(`/api/game/${this.gameId}/${this.username}`)
    )
  }

  deleteGame(gameId: string): Promise<DeleteGameResponse> {
    const headers = new HttpHeaders()
        .set('X-SECRET', this.secret)
    return firstValueFrom(
      this.http.delete<DeleteGameResponse>(`/api/game/${gameId}`, { headers })
    ).then(resp => {
      this.gameId = ''
      this.username = ''
      this.secret = 'abcd1234'
      this.store.deleteGame()
      return resp
    })
  }
}
