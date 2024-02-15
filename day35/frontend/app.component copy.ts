import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription, map, tap } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy {

  private fb = inject(FormBuilder)

  form!: FormGroup
  valueSub!: Subscription
  statusSub!: Subscription

  formStatus: boolean = false
  formStatus$!: Observable<boolean>

  ngOnInit(): void {
    this.form = this.createForm()
    this.valueSub = this.form.valueChanges.subscribe({
      next: (values) => {
        console.info('form values: ', values)
      }
    })
    this.statusSub = this.form.statusChanges
      .pipe(
        map(status => "VALID" === status),
      )
      .subscribe({
        next: (status) => {
          console.info('form status: ', status)
          this.formStatus = status
        }
      })

      this.formStatus$ = this.form.statusChanges
        .pipe(
          map(status => 'VALID' === status)
        )
  }

  ngOnDestroy(): void {
      // Otherwise you will have memory leak
      this.valueSub.unsubscribe()
      this.statusSub.unsubscribe()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
    })
  }
}
