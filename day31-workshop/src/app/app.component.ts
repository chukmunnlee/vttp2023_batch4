import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  selection = 0

  numList: number[] = [ 1, 2, 3, 4, 5, 6, 7 ]

  selectedNumber(n: number) {
    this.selection = n
  }
}
