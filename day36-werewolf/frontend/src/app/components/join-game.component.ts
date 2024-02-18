import { Component, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {JoinGameRequest} from '../models';

@Component({
  selector: 'app-join-game',
  templateUrl: './join-game.component.html',
  styleUrl: './join-game.component.css'
})
export class JoinGameComponent implements OnInit {

  private router = inject(Router)
  private fb = inject(FormBuilder)
  private activatedRoute = inject(ActivatedRoute)

  gameId = ''
  username = ''

  form!: FormGroup

  ngOnInit(): void {
    this.gameId = !!this.activatedRoute.snapshot.queryParams['gameId']?
        this.activatedRoute.snapshot.queryParams['gameId']: ''

    this.form = this.createForm()
  }

  joinGame() {
    const req: JoinGameRequest = this.form.value
    console.info('>>> req: ', req)
  }

  private createForm(): FormGroup {
    return this.fb.group({
      gameId: this.fb.control<string>(this.gameId, [ Validators.required, Validators.minLength(8) ]),
      username: this.fb.control<string>(this.username, [ Validators.required ])
    })
  }

}
