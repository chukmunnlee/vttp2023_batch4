import { Component, Input, OnInit, Output, inject } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Item} from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  private fb = inject(FormBuilder)

  @Input()
  name = 'anon'

  @Input()
  item: Item = { item: '', quantity: 0 }

  @Output()
  onUpdate = new Subject<Item>()

  form!: FormGroup

  ngOnInit(): void {
    var { item, quantity } = this.item
    this.form = this.fb.group({
      item: this.fb.control<string>(item),
      quantity: this.fb.control<number>(quantity)
    })
  }

  process() {
    const item = this.form.value as Item
    console.info('item = ', item)
    this.onUpdate.next(item)
    this.form.reset()
  }

  clearForm() {
    this.form.reset()
  }

  incrementQuantity(n: number) {
    this.form.get('quantity')?.setValue(n)
  }

  value(): Item {
    return this.form.value as Item
  }

}
