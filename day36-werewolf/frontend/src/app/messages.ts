import {GameRole} from "./models"

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
  secret: string
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

