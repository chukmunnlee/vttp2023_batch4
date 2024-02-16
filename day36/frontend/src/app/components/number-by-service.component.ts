import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { NumberService } from '../number.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-number-by-service',
  templateUrl: './number-by-service.component.html',
  styleUrl: './number-by-service.component.css'
})
export class NumberByServiceComponent implements OnInit, OnDestroy {

  private numSvc = inject(NumberService)
  private sub!: Subscription

  numDisplay: number = 0
  imgWidth: number = 200

  ngOnInit(): void {
    this.numDisplay = this.numSvc.imageNumber
    this.imgWidth = this.numSvc.imageWidth
    this.sub = this.numSvc.onNumberChanged.subscribe(
      value => {
        console.info('>>>> onNumberChanged: ', value)
        this.numDisplay = value
      }
    )
  }

  ngOnDestroy(): void {
      this.sub.unsubscribe()
  }

}
