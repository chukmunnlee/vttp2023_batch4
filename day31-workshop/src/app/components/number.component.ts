import { Component, Input, Output } from '@angular/core';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-number',
  templateUrl: './number.component.html',
  styleUrl: './number.component.css'
})
export class NumberComponent {

  @Input()
  value: number = 0

  @Input()
  min: number = 0

  @Input()
  max: number = 30

  @Output()
  selected = new Subject<number>()

  neg: number = 1

  inc(fac: number) {

    this.value += fac

    if (this.value < this.min)
      this.value = this.max
    else if (this.value > this.max)
      this.value = this.min
  }

  negative() {
    console.info('>>>> negative: ')
    this.neg *= -1

  }

  pressed() {
    this.selected.next(this.value * this.neg)
  }

}
