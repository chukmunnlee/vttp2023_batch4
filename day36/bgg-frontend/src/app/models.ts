export interface Game {
  gameId: number
  name: string
  url: string
  image: string
}

export interface Comment {
  gameId: number
  user: string
  rating: number
  text: string
}
