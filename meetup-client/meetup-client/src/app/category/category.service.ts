import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, map, Observable} from "rxjs";
import {Category} from "./model/category";
import {Event} from "../event/model/event";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  private _url: string = "http://localhost:8082/category";

  constructor(private httpClient: HttpClient) { }

  getCategoriesByHttp(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(`${this._url}/all`);
  }

  public getCategoryById(categoryId: number): Observable<Category> {
    return this.httpClient.get<Category>(`${this._url}/${categoryId}`)
  }
}
