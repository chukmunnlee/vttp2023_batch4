export interface CreateGameResponse {
  gameId: string
  secret: string
  message: string
}

export interface JoinGameRequest {
  gameId: string
  username: string
}

export interface JoinGameResponse {
  gameId: string
  message: string
}

export interface DeleteGameResponse {
  success: boolean
  message: string
}

export interface PlayerCountResponse {
  gameId: string
  count: number
}

export interface StartGameRequest {
  gameId: string
  name: string
  moderator: boolean
}

export interface StartGameResponse {
  success: boolean
  message: string
  gameId: string
  name: string
  role: GameRole
}

export enum GameStatus { NoGame, PreStart, Started, Ended }
export enum GameRole { None, Moderator, Werewolf, Villager, Seer, Doctor }


export interface Game {
  gameId: string
  secret: string
  moderator: boolean
  name: string
  role: GameRole
  status: GameStatus
}
