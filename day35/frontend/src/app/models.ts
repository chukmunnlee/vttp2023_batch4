export interface Game {
  gameId: number
  name: string
  url: string
  image: string
}

export interface Comment {
  user: string
  gameId: number
  text: string
  rating: number
}
