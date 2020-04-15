import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080/';

  verifyUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}/user/login`,user);
  }


}
