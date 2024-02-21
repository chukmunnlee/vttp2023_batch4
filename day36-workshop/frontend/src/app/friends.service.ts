import {HttpClient} from "@angular/common/http";
import {Injectable, inject} from "@angular/core";
import {Friend} from "./models";
import {lastValueFrom} from "rxjs";

import { environment } from '../environments/environment'

const URL = environment.url

@Injectable()
export class FriendsService {

  private http = inject(HttpClient)

  addFriend(friend: Friend): Promise<any> {
    return lastValueFrom(
      this.http.post<any>(`${URL}/friend`, friend)
    )
  }

  getFriends(): Promise<Friend[]> {
    return lastValueFrom(
      this.http.get<Friend[]>(`${URL}/friends`)
    )
  }

}
