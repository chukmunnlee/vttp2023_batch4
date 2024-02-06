import { Component, Input, Output } from '@angular/core';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-picture',
  templateUrl: './picture.component.html',
  styleUrl: './picture.component.css'
})
export class PictureComponent {

  @Input()
  caption: string = "a picture"

  @Input({ required: true })
  image: string = "https://png.pngtree.com/png-vector/20210604/ourmid/pngtree-gray-network-placeholder-png-image_3416659.jpg"

  @Output()
  imageEvent = new Subject<string>()

  imageStyle: string = "thumbnail"

  imageClicked() {
    //console.info('>>> image clicked: ', this.caption)
    this.imageEvent.next(this.caption)
  }

}
