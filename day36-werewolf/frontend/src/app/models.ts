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
