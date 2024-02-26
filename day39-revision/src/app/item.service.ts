import { Injectable } from "@angular/core";
import Dexie from "dexie";

import { Item } from './models'

@Injectable()
export class ItemService extends Dexie {

  items: Dexie.Table<Item, string>

  constructor() {
    super('items')
    this.version(1).stores({
      items: "id"
    })
    this.items = this.table('items')
  }

  save(items: Item[]): Promise<string> {
    return this.items.bulkAdd(items)
  }

  load(): Promise<Item[]> {
    return this.items.toArray()
  }
}

