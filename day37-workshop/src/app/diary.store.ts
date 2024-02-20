import {Injectable} from "@angular/core";
import Dexie from "dexie";
import {DiaryEntry} from "./models";
import {Subject} from "rxjs";

const ENTRY = 'entry'

@Injectable()
export class DiaryStore extends Dexie {

  private entry!: Dexie.Table<DiaryEntry, number>

  onEntries = new Subject<DiaryEntry[]>

  constructor() {
    super('dear-diary')
    this.version(1).stores({
      [ENTRY]: '++entryId, date, entryType'
    })
    this.entry = this.table(ENTRY)
    this.getDiaryEntries().then(
      (result) => this.onEntries.next(result)
    )
  }

  add(entry: DiaryEntry): Promise<any> {
    return this.entry.add(entry)
        .then(pk => {
          console.info('>> pk ', pk)
          return this.getDiaryEntries()
        })
        .then(result => this.onEntries.next(result))
  }

  getDiaryEntries(): Promise<DiaryEntry[]> {
    return this.entry
        .orderBy('date').reverse().toArray()
  }

}
