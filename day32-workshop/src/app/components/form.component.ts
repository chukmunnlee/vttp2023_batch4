import { Component, Input, OnChanges, OnInit, Output, SimpleChanges, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Friend, NO_FRIEND } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit, OnChanges {

  @Input()
  friend: Friend = NO_FRIEND

  @Output()
  newFriend = new Subject<Friend>()

  @Output()
  updateFriend = new Subject<Friend>()

  private fb = inject(FormBuilder)

  friendsForm!: FormGroup
  updateMode = false

  ngOnInit(): void {
    this.friendsForm = this.createFriendsForm(this.friend)
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.info('>>> changes: ', changes)
    this.friendsForm = this.createFriendsForm(changes['friend'].currentValue)
    this.updateMode = true
  }

  cannotCreate() {
    return this.friendsForm.invalid || this.updateMode
  }
  cannotUpdate() {
    return this.friendsForm.invalid || !this.updateMode
  }

  create() {
    const f: Friend = this.friendsForm.value
    this.newFriend.next(f)
    this.friendsForm = this.createFriendsForm(this.friend)
  }
  update() {
    const f: Friend = this.friendsForm.value
    this.updateFriend.next(f)
    this.friendsForm = this.createFriendsForm(this.friend)
    this.updateMode = false
  }

  private createFriendsForm(f: Friend): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>(f.name, [ Validators.required ]),
      email: this.fb.control<string>(f.email, [ Validators.required, Validators.email ]),
      phone: this.fb.control<string>(f.phone, [ Validators.required, Validators.minLength(8) ]),
      friend: this.fb.control<boolean>(f.friend)
    })
  }
}
