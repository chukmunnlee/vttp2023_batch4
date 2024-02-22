import { Component, OnInit, inject } from '@angular/core';
import { WebcamImage } from 'ngx-webcam';
import { Observable, Subject } from 'rxjs';
import { dataURItoBlob } from './utils';
import { UploadService } from './upload.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  private uploadSvc = inject(UploadService)

  capturedImage = '/assets/placeholder.jpg'

  protected width: number = 300
  protected height: number = 300

  protected trigger$!: Observable<void>
  protected triggerSub = new Subject<void>()

  private canUpload = false

  ngOnInit(): void {
    this.trigger$ = this.triggerSub.asObservable()
  }

  upload() {
    if (!this.canUpload)
      return
    this.uploadSvc.upload(this.capturedImage)
      .then(result => {
        console.info('response: ', result)
      })
  }

  snap() {
    this.triggerSub.next()
  }

  captured(image: WebcamImage) {
    this.capturedImage = image.imageAsDataUrl
    // Convert the data url to binary/blob
    console.info('>>> ', this.capturedImage)
    this.canUpload = true
  }

  clear() {
    this.canUpload = false
    this.capturedImage = '/assets/placeholder.jpg'
  }
}
