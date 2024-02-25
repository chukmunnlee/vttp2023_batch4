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
