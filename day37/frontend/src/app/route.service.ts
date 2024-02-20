import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { User } from "./models";

@Injectable()
export class RouteService {

  http = inject(HttpClient)

  proceed = false

  register(data: User): Promise<any> {
    return firstValueFrom(
      this.http.post<any>('/api/user', data)
    )
  }
}
