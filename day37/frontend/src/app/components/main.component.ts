import { Component, OnInit, inject } from '@angular/core';
import { RouteService } from '../route.service';
import { UserStorage } from '../user.storage.service';
import { User } from '../models';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  routeSvc = inject(RouteService)
  userStorage = inject(UserStorage)

  agree = false
  users$!: Promise<User[]>

  ngOnInit(): void {
    this.routeSvc.proceed = this.agree
    this.users$ = this.userStorage.getAllUsers()
  }

  checked(checkbox: any) {
    this.agree = checkbox.target.checked
    this.routeSvc.proceed = this.agree
    console.info('value == ', this.agree, checkbox.target.checked)
  }

}
