import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CartComponent } from './components/cart.component';
import {ReactiveFormsModule} from '@angular/forms';
import { OrderComponent } from './components/order.component';

@NgModule({
  declarations: [
    AppComponent,
    CartComponent, OrderComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
