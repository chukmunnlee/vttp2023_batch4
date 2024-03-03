import {Injectable} from "@angular/core";
import {ComponentStore} from "@ngrx/component-store";

import {Game, GameRole, GameStatus} from "./models";

const INIT_VALUE: Game = {
  gameId: "",
  secret: "",
  moderator: false,
  name: "",
  role: GameRole.None,
  status: GameStatus.NoGame
}

@Injectable()
export class WerewolfStore extends ComponentStore<Game> {

  constructor() { super(INIT_VALUE) }

  // Selectors
  readonly game$ = this.select<Game>(
    (game) => ({ ...game } as Game)
  )

  // Mutators
  readonly newGame = this.updater<Game>(
    (_game: Game, value: Game) => value
  )

  readonly updateRole = this.updater<GameRole>(
    (game: Game, role: GameRole) => ({ ...game, role } as Game)
  )

  readonly deleteGame = this.updater<void>(
    (_game: Game) => INIT_VALUE
  )

  readonly startGame = this.updater<void>(
    (game: Game) => ({ ...game, status: GameStatus.Started } as Game)
  )

}
