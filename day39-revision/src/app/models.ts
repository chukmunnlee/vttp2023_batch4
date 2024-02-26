export interface Item {
  id?: string
  item: string
  quantity: number
}

export interface ItemSlice {
  loadedOn: number
  items: Item[]
}
