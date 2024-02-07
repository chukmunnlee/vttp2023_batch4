import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { TodoComponent } from './components/todo.component';

@NgModule({
  declarations: [ AppComponent, TodoComponent ],
  imports: [ BrowserModule, ReactiveFormsModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
