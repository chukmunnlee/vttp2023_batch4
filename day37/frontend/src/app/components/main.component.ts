import { Component, OnInit, inject } from '@angular/core';
import { RouteService } from '../route.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  routeSvc = inject(RouteService)

  agree = false

  ngOnInit(): void {
      this.routeSvc.proceed = this.agree
  }

  checked(checkbox: any) {
    this.agree = checkbox.target.checked
    this.routeSvc.proceed = this.agree
    console.info('value == ', this.agree, checkbox.target.checked)
  }

}
