import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-dog',
  templateUrl: './dog.component.html',
  styleUrl: './dog.component.css'
})
export class DogComponent implements OnInit, OnDestroy {

  ngOnInit(): void {
    console.info("dog OnInit")
  }

  ngOnDestroy(): void {
    console.info("dog OnDestroy")
  }

}
