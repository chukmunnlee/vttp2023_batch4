import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ListingsComponent } from './components/listings.component';
import { EntryComponent } from './components/entry.component';
import {ReactiveFormsModule} from '@angular/forms';
import { TodoStore } from './todo.store';

@NgModule({
  declarations: [
    AppComponent,
    ListingsComponent, EntryComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule
  ],
  providers: [ TodoStore ],
  bootstrap: [AppComponent]
})
export class AppModule { }
