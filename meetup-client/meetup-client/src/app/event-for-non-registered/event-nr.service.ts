import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Event} from "../event/model/event";
import {EventAction} from "../event/model/eventAction";

@Injectable({
  providedIn: 'root'
})
export class EventNRService {


  private _url: string = "http://localhost:8082/event";

  constructor(private httpClient: HttpClient) { }

  public getEventsByHttp(): Observable<Event[]> {
    return this.httpClient.get<Event[]>(`${this._url}/all`);
  }

  public getEventById(eventId: number): Observable<Event> {
    return this.httpClient.get<Event>(`${this._url}/${eventId}`)
  }

  public addEvent(eventAction: EventAction): Observable<EventAction> {
    return this.httpClient.post<EventAction>(`${this._url}/add`, eventAction);
  }

  public updateEvent(eventId: number, event: EventAction): Observable<EventAction> {
    return this.httpClient.put<EventAction>(`${this._url}/${eventId}/update`, event);
  }

  public deleteEvent(eventId: number): Observable<Event> {
    return this.httpClient.delete<Event>(`${this._url}/${eventId}/delete`);
  }

  public getEventsByCategory(categoryId: number | undefined): Observable<Event[]> {
    return this.httpClient.get<Event[]>(`${this._url}/all-events-by-category/${categoryId}`);
  }
}
