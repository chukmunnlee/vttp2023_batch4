import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RouteService } from '../route.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private routeSvc = inject(RouteService)

  form!: FormGroup
  hasProcessed = false

  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ])
    })
  }

  process() {
    const value: any = this.form.value
    console.info('>>> value: ', value)
    this.hasProcessed = true
    this.router.navigate(['/'])
  }

}
