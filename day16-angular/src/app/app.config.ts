import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter, withHashLocation } from '@angular/router';

import { routes } from './app.routes';
import {ReactiveFormsModule} from '@angular/forms';
import {provideHttpClient} from '@angular/common/http';
import {TodoService} from './todo.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withHashLocation()),
    importProvidersFrom(ReactiveFormsModule),
    provideHttpClient(),
    TodoService
  ]
};
