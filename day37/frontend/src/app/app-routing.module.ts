import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './components/main.component';
import { FormComponent } from './components/form.component';
import { canLeave, canProcced } from './guards';
import { NoticeComponent } from './components/notice.component';

const routes: Routes = [
  { path: '', component: MainComponent },
  { path: 'notice', component: NoticeComponent },
  {
    path: 'form', component: FormComponent,
    canActivate: [ canProcced ],
    canDeactivate: [ canLeave ]
  },
  { path: '**', redirectTo: '/', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
