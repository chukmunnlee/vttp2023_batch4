import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { FormComponent } from './components/form.component';
import { NoticeComponent } from './components/notice.component';
import { RouteService } from './route.service';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, FormComponent, NoticeComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ RouteService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
