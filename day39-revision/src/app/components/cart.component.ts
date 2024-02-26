import { Component, Input, OnInit, inject } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Item} from '../models';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  private fb = inject(FormBuilder)

  @Input()
  name = 'anon'

  form!: FormGroup

  ngOnInit(): void {
    this.form = this.fb.group({
      item: this.fb.control<string>(''),
      quantity: this.fb.control<number>(1)
    })
  }

  process() {
    const item = this.form.value as Item
    console.info('item = ', item)
    this.form.reset()
  }

}
