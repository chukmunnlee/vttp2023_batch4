export interface Friend {
  name: string
  email: string
  phone: string
  //dateOfBirth: string
  friend: boolean
}


export const NO_FRIEND: Friend = {
  name: '',
  email: '',
  phone: '',
  friend: true
}
