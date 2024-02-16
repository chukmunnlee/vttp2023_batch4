import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { GameListComponent } from './components/game-list.component';
import { BGGService } from './bgg.service';
import { CommentsComponent } from './components/comments.component';

const appRoutes: Routes = [
  { path: '', component: GameListComponent },
  { path: 'comments/:gameId', component: CommentsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    GameListComponent, CommentsComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ BGGService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
