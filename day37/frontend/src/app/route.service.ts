import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Observable, firstValueFrom } from "rxjs";

@Injectable()
export class RouteService {

  http = inject(HttpClient)

  proceed = false

  register(data: any): Promise<any> {
    return firstValueFrom(
      this.http.post<any>('http://localhost:8080/api/user', data)
    )
  }
}
