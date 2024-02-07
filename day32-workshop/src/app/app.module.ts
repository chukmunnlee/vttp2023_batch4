import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {ReactiveFormsModule} from '@angular/forms';
import { FormComponent } from './components/form.component';
import { FriendsListComponent } from './components/friends-list.component';

@NgModule({
  declarations: [
    AppComponent,
    FormComponent, FriendsListComponent
  ],
  imports: [ BrowserModule, ReactiveFormsModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
