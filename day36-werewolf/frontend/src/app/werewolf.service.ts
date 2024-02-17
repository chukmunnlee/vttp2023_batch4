import {HttpClient} from "@angular/common/http";
import {Injectable, inject} from "@angular/core";
import {CreateGameResponse, DeleteGameResponse} from "./models";
import {firstValueFrom} from "rxjs";

@Injectable()
export class WerewolfService {

  private http = inject(HttpClient)

  createGame(): Promise<CreateGameResponse> {
    return firstValueFrom(
      this.http.post<CreateGameResponse>('/api/game', {})
    )
  }

  deleteGame(gameId: string): Promise<DeleteGameResponse> {
    return firstValueFrom(
      this.http.delete<DeleteGameResponse>(`/api/game/${gameId}`)
    )
  }
}
