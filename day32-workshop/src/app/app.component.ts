import { Component } from '@angular/core';
import {Friend, NO_FRIEND} from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  friends: Friend[] = []
  friend: Friend = NO_FRIEND

  newFriend(friend: Friend) {
    this.friends.push(friend)
  }

  selectedFriend(i: number) {
    console.info('>>> selected: ', this.friends[i])
    this.friend = { ...this.friends[i] }
  }

}
