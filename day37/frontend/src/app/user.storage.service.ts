import { Injectable } from "@angular/core";
import Dexie from "dexie";
import { User } from "./models";

@Injectable()
export class UserStorage extends Dexie {

  private user!: Dexie.Table<User, string>

  constructor() {
    // Create db if it does not exists
    super('userdb')

    const COL_USER = 'user'

    // Define schema
    this.version(1).stores({
      [COL_USER]: 'email',
    })
    this.user = this.table(COL_USER)
  }

  addUser(user: User): Promise<string> {
    return this.user.add(user)
  }

  getAllUsers(): Promise<User[]> {
    return this.user.toArray()
  }

}
