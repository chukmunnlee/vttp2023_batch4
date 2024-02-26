import { AfterViewInit, Component, OnInit, ViewChild, inject } from '@angular/core';

import { environment as env } from '../environments/environment'

import { Item } from './models'
import { CartComponent } from './components/cart.component';
import { ItemStore } from './item.store';
import { Observable, firstValueFrom } from 'rxjs';
import { ItemService } from './item.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, AfterViewInit {

  private itemStore = inject(ItemStore)
  private itemSvc = inject(ItemService)

  @ViewChild(CartComponent)
  cart!: CartComponent

  names = [ 'fred', 'barney', 'wilma' ]

  myItem: Item = { item: 'apple', quantity: 10 }

  items$!: Observable<Item[]>
  filteredItems$!: Observable<Item[]>

  ngOnInit(): void {
    console.info('onInit: ', this.cart)
    this.items$ = this.itemStore.getAllItems
    this.itemSvc.load()
      .then((items: Item[]) => this.itemStore.loadToStore(items))
  }

  newItem(item: Item) {
    console.info('>>>> new item: ', item)
  }

  ngAfterViewInit(): void {
    console.info('onAfterViewInit: ', this.cart)
    this.cart.name = env.url

    this.cart.onUpdate.subscribe(
      // Java method reference
      // map(c -> Utils.toJson(c)) -> map(Utils::toJson)
      this.newItem
      //value => this.newItem(value)
    )

    /*
    this.cart.onUpdate.subscribe({
      next: this.newItem,
      error: err => console.info('error', err),
      complete: () => console.info('completed')
    })
    */
  }

  pressed(elem: any) {
    console.info('>>> elem: ', elem.target.value)
    this.filteredItems$ = this.itemStore.getItemsWhereQuantity(elem.target.value)
  }

  save() {
    console.info('>>> saving to Dexie')
    /*
    this.itemStore.getAllItems
      .subscribe(
        (items: Item[]) => {
          this.itemSvc.save(items)
            .then(() =>  console.info('successful'))
            .catch(err => console.error('>>> error: ', err))
        }
      )
    */
    firstValueFrom(this.itemStore.getAllItems)
        .then((items: Item[]) => this.itemSvc.save(items))
        .then(result => console.info('>>> promise success: ', result))
        .catch(err => console.error('>>> error: ', err))
  }

  add() {
    const i = this.cart.value()
    console.info('>> add: ', i)
    this.cart.clearForm()
    this.itemStore.addToStore(i)
  }
  update() {
    const i = this.cart.value()
    console.info('>> update: ', i)
    this.cart.clearForm()
  }
  delete() {
    console.info('>> delete')
  }
  addOne() {

  }
}
