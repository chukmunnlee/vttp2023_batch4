import { Component, Input, Output } from '@angular/core';
import {Friend} from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-friends-list',
  templateUrl: './friends-list.component.html',
  styleUrl: './friends-list.component.css'
})
export class FriendsListComponent {

  @Input()
  friends: Friend[] = []

  @Output()
  selectedFriend = new Subject<number>()

  friendSelected(i: number) {
    this.selectedFriend.next(i)
  }

}
