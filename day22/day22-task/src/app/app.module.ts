import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import {TaskService} from './task.service';
import { AddContactComponent } from './components/add-contact.component';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'add', component: AddContactComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, AddContactComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ TaskService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
