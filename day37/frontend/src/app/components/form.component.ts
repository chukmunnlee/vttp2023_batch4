import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RouteService } from '../route.service';
import { User } from '../models';
import { StorageService } from '../storage.service';
import { UserStorage } from '../user.storage.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private storageSvc = inject(StorageService)
  private userStorage = inject(UserStorage)
  private routeSvc = inject(RouteService)

  form!: FormGroup
  hasProcessed = false

  ngOnInit(): void {
    //const u = this.storageSvc.getFromLocalStroage()
    //this.form = this.fb.group({
    //  name: this.fb.control<string>(!!u? u.name: '', [ Validators.required ]),
    //  email: this.fb.control<string>(!!u? u.email: '', [ Validators.required, Validators.email ])
    //})

    this.form = this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ])
    })
  }

  process() {
    const value: User = this.form.value
    console.info('>>> value: ', value)
    this.routeSvc.register(value)
      .then(() => this.userStorage.addUser(value))
      .then(resp => {
        console.info('>>> resp: ', resp)
        this.hasProcessed = true
        this.router.navigate(['/'])

        //this.storageSvc.saveToLocalStorage(value)
      })
      .catch(err => alert(JSON.stringify(err)))
  }

}
