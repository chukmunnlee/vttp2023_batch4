export interface CreateGameResponse {
  gameId: string
  message: string
}

export interface JoinGameResponse {
  gameId: string
  message: string
}

export interface DeleteGameResponse {
  success: boolean
  message: string
}
