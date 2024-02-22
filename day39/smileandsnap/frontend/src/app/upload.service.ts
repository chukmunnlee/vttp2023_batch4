import { Injectable, inject } from "@angular/core";
import { dataURItoBlob } from "./utils";
import { firstValueFrom } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class UploadService {

  private http = inject(HttpClient)

  upload(dataUrl: string) {
    const blob = dataURItoBlob(dataUrl)
    const file = new File([blob], 'captured.jpg', { type: 'image/jpg'})
    console.info('>> file: ', file)

    // multipart/form-data
    const form = new FormData()
    // <input type="file" name="image">
    form.set('image', file)
    // <input type="text" name="title">
    form.set('title', 'this is my image')

    return firstValueFrom(
      this.http.post<any>('http://localhost:8080/picture', form)
    )
  }

}
