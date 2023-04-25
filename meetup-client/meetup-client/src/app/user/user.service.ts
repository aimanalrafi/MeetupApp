import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./model/user";
import {LoginUser} from "./model/loginUser";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private _url: string = "http://localhost:8082/user";

  constructor(private httpClient: HttpClient) { }

  public getUsersByHttp(): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this._url}/all`);
  }

  public getUserById(userId: number): Observable<User> {
    return this.httpClient.get<User>(`${this._url}/${userId}`)
  }

  public validateLogin(loginUser: LoginUser): Observable<User> {
    return this.httpClient.post<User>(`http://localhost:8082/login`, loginUser)
  }

  public joinEvent(userId: number, eventId: number): Observable<User> {
    return this.httpClient.put<User>(`${this._url}/${userId}/join-event-${eventId}`, {userId, eventId});
  }


}
