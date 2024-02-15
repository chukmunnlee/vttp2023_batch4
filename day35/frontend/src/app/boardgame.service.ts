import { Injectable, inject } from "@angular/core";
import { Observable } from "rxjs";
import { Game } from "./models";
import { HttpClient, HttpParams } from "@angular/common/http";

@Injectable()
export class BoardgameService {

  //private http = inject(HttpClient)
  constructor(private http: HttpClient) { }

  // GET http://localhost:8080/api/games/search?name=abc
  searchBoardgame(name: string): Observable<Game[]> {

    console.info('in search board game')

    const params = new HttpParams()
        .set("name", name)

    return this.http.get<Game[]>('http://localhost:8080/api/games/search', { params })
  }

}
