import { Component, OnInit, inject } from '@angular/core';
import {FriendsService} from '../friends.service';
import {Observable} from 'rxjs';
import {Friend} from '../models';

@Component({
  selector: 'app-friends-list',
  templateUrl: './friends-list.component.html',
  styleUrl: './friends-list.component.css'
})
export class FriendsListComponent implements OnInit {

  private friendsSvc = inject(FriendsService)

  friends$!: Promise<Friend[]>

  ngOnInit(): void {
    this.friends$ = this.friendsSvc.getFriends()
  }

}
