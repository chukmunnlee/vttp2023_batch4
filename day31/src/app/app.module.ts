import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PictureComponent } from './components/picture.component';
import { HistoryComponent } from './components/history.component';

@NgModule({
  declarations: [
    AppComponent, PictureComponent, HistoryComponent
  ],
  imports: [ BrowserModule, ],
  exports: [ PictureComponent ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
