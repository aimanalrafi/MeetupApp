import { Component } from '@angular/core';
import {Event} from "../event/model/event";
import {User} from "../user/model/user";
import {Category} from "../category/model/category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EventService} from "../event/event.service";
import {CategoryService} from "../category/category.service";
import {UserService} from "../user/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {EventAction} from "../event/model/eventAction";
import {EventNRService} from "./event-nr.service";

@Component({
  selector: 'app-event-for-non-registered',
  templateUrl: './event-for-non-registered.component.html',
  styleUrls: ['./event-for-non-registered.component.css']
})
export class EventForNonRegisteredComponent {

  // @ts-ignore
  public eventId: number;
  //used for data binding in form
  public editEvent: Event | undefined;
  public joinEvent: Event | undefined;
  public deleteEvent: Event | undefined;
  //logged in user
  // @ts-ignore
  public userLoggedIn: User;

  public events: Event[] = [];
  // @ts-ignore
  public categories: Category[] = [];
  public users: User[] =[];

  listOfUsers: Array<User> = [];

  // @ts-ignore
  public addEventForm: FormGroup;
  // @ts-ignore
  public editEventForm: FormGroup;

  constructor(private _eventService: EventNRService,
              private _categoryService: CategoryService,
              private _userService: UserService,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private router: Router) { }



  ngOnInit(): void {
    this.getEventsByHttp();
    this.getCategoriesFromEventComp();
    this.getUsersFromEventComp();
  }

  public getEventsByHttp(): void {
    this._eventService.getEventsByHttp()
      .subscribe(data => {
          this.events = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  public getCategoriesFromEventComp(): void {
    this._categoryService.getCategoriesByHttp()
      .subscribe(data => {
          this.categories = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  public getUsersFromEventComp(): void {
    this._userService.getUsersByHttp()
      .subscribe(data => {
          this.users = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  public onFilterEventByCategory(categoryId?: number): void {
    this.events = [];
    this._eventService.getEventsByCategory(categoryId)
      .subscribe(data => {
          this.events = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }



}
