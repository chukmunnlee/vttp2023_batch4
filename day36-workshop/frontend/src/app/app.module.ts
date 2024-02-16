import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { FriendsListComponent } from './components/friends-list.component';
import { AddFriendComponent } from './components/add-friend.component';
import {FriendsService} from './friends.service';

const appRoutes: Routes = [
  { path: '', component: FriendsListComponent },
  { path: 'add', component: AddFriendComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    FriendsListComponent,
    AddFriendComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ FriendsService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
