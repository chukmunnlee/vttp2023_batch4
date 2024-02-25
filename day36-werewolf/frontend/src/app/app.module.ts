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
import {WerewolfStore} from './werewolf.store';
import { PreStartComponent } from './components/pre-start.component';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'create', component: CreateGameComponent },
  { path: 'join', component: JoinGameComponent },
  { path: 'prestart', component: PreStartComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    CreateGameComponent,
    JoinGameComponent,
    PreStartComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ WerewolfService, WerewolfStore ],
  bootstrap: [AppComponent]
})
export class AppModule { }
