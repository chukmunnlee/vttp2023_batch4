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

export const stringToGameRole = (role: string): GameRole => {
  switch (role.trim().toLowerCase()) {
    case 'werewolf':
      return GameRole.Werewolf
    case 'villager':
      return GameRole.Villager
    case 'seer':
      return GameRole.Seer
    case 'doctor':
      return GameRole.Doctor
    default:
      return GameRole.None
  }
}
