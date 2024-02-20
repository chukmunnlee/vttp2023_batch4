import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { EntryComponent } from './components/entry.component';
import { DisplayComponent } from './components/display.component';
import {ReactiveFormsModule} from '@angular/forms';
import {DiaryStore} from './diary.store';

@NgModule({
  declarations: [
    AppComponent,
    EntryComponent, DisplayComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule
  ],
  providers: [ DiaryStore ],
  bootstrap: [AppComponent]
})
export class AppModule { }
