import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { CreateGameComponent } from './components/create-game.component';
import { JoinGameComponent } from './components/join-game.component';

import {WerewolfService} from './werewolf.service';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'create', component: CreateGameComponent },
  { path: 'join', component: JoinGameComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    CreateGameComponent,
    JoinGameComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ WerewolfService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
