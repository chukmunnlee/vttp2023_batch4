import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
// Import RouterModule
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { DogComponent } from './components/dog.component';
import { NumberComponent } from './components/number.component';
import { NumberByServiceComponent } from './components/number-by-service.component';
import { NumberService } from './number.service';

// List of Routes
const appRoutes: Routes = [
  // Define the 'landing' page
  { path: '', component: MainComponent },
  { path: 'dog', component: DogComponent },
  // :num == {num} in SpringBoot
  { path: 'number/:num', component: NumberComponent },
  { path: 'number-by-service', component: NumberByServiceComponent },
  // Wildcard route - last
  { path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, DogComponent, NumberComponent, NumberByServiceComponent
  ],
  imports: [
    BrowserModule,
    // Register the list of Routes
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ NumberService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
