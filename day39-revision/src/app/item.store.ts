import { Injectable } from "@angular/core";
import { ComponentStore } from "@ngrx/component-store";
import { Item, ItemSlice } from "./models";
import { v4 as uuidv4 } from 'uuid'
import { SlicePipe } from "@angular/common";

const INIT: ItemSlice = {
  loadedOn: 0,
  items: []
}

@Injectable()
export class ItemStore extends ComponentStore<ItemSlice> {

  constructor() { super(INIT) }

  // Mutators
  readonly addToStore = this.updater<Item>(
    (slice: ItemSlice, value: Item) => {
      value.id = uuidv4().substring(0, 8)
      const newSlice: ItemSlice = {
        loadedOn: slice.loadedOn,
        items: []
      }
      // Copy all the existing items from old slice to new slice
      for (let i of slice.items)
        newSlice.items.push(i)
      newSlice.items.push(value)
      return newSlice
      // return {
      //   loadedOn: slice.loadedOn,
      //   items: [ ...slice.items, value ]
      // } as ItemSlice
    }
  )

  readonly loadToStore = this.updater<Item[]>(
    (_slice: ItemSlice, values: Item[]) => {
      return {
        loadedOn: (new Date()).getTime(),
        items: values
      } as ItemSlice
    }
  )

  // Selectors
  readonly getAllItems = this.select<Item[]>(
    (slice: ItemSlice) => slice.items
  )

  readonly getLoadedTime = this.select<Date>(
    (slice: ItemSlice) => new Date(slice.loadedOn)
  )

  readonly getItemsWhereQuantity = (quantity: number) =>
    this.select<Item[]>(
      (slice: ItemSlice) => slice.items.filter(item => item.quantity < quantity)
    )

}
