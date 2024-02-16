import {HttpClient} from "@angular/common/http";
import {Injectable, inject} from "@angular/core";
import {Friend} from "./models";
import {lastValueFrom} from "rxjs";

@Injectable()
export class FriendsService {

  private http = inject(HttpClient)

  addFriend(friend: Friend): Promise<any> {
    return lastValueFrom(
      this.http.post<any>('http://localhost:8080/friend', friend)
    )
  }

  getFriends(): Promise<Friend[]> {
    return lastValueFrom(
      this.http.get<Friend[]>('http://localhost:8080/friends')
    )
  }

}
