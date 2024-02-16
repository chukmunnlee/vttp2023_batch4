import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit, OnDestroy {

  ngOnInit(): void {
    console.info('main OnInit')
  }

  ngOnDestroy(): void {
    console.info('main OnDestroy')
  }

}
