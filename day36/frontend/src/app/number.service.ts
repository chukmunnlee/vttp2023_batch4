import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable()
export class NumberService {
  imageNumber:number = 0
  imageWidth:number = 200

  onNumberChanged = new Subject<number>()

  setNumber(num: number) {
    this.imageNumber = num
    this.onNumberChanged.next(num)
  }
}
