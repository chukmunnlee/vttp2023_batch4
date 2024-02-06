import { Component } from '@angular/core';
import {PictureData} from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  images: PictureData[] = [
    {
      url: "https://www.wwf.org.uk/sites/default/files/styles/social_share_image/public/2016-12/Original_WW22791.jpg?itok=kuWctE9S",
    text: "Polar bears!"
    },
    {
      url: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWQfWTONzxEWgpuPVbDa_xFshqgCrdzQh6iw&usqp=CAU",
      text: "Dogs are friends not food!"
    },
    {
      url: 'https://www.telegraph.co.uk/content/dam/news/2020/07/12/TELEMMGLPICT000195952264_trans_NvBQzQNjv4BqpVlberWd9EgFPZtcLiMQfyf2A9a6I9YchsjMeADBa08.jpeg',
      text: 'Great Dane'
    }
  ]

  pastCaptions: string[] = []

  imagePressed(text: string): void {
    console.info('app.component: image pressed: ', text)
    this.pastCaptions.push(text)
  }

}
