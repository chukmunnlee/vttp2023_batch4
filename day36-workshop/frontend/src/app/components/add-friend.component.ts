import { Component, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FriendsService} from '../friends.service';
import {Friend} from '../models';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-friend',
  templateUrl: './add-friend.component.html',
  styleUrl: './add-friend.component.css'
})
export class AddFriendComponent implements OnInit{

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private friendsSvc = inject(FriendsService)

  form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  process() {
    const f: Friend = this.form.value
    console.info('>>> f: ', f)

    this.friendsSvc.addFriend(f)
      .then(resp => {
        console.info('resp: ', resp)
        this.router.navigate([ '/' ])
      })
      .catch(resp => {
        alert(`ADD ERROR: ${resp.error.message}`)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      phone: this.fb.control<string>('', [ Validators.required ]),
    })
  }

}
