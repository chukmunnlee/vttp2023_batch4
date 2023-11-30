import { Routes } from '@angular/router';
import {MainComponent} from './components/main.component';

export const routes: Routes = [
  { path: '', component: MainComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
];
