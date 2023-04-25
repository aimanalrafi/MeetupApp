import { Component, OnInit } from '@angular/core';
import {EventService} from "./event.service";
import { Event } from "./model/event"
import {User} from "../user/model/user";
import {FormArray, FormBuilder, NgForm, Validators} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {Category} from "../category/model/category";
import {CategoryService} from "../category/category.service";
import { FormControl, FormGroup } from '@angular/forms';
import {UserService} from "../user/user.service";
import {EventAction} from "./model/eventAction";
import {Router, Routes} from "@angular/router";
import { ActivatedRoute } from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {Observable} from "rxjs";


@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

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

  participantsEditForm: Array<User> = [];
  listOfUsers: Array<User> = [];
  listOfJoinedEvents: Array<Event> = [];
  // @ts-ignore
  checkEvent: Event;

  // @ts-ignore
  public addEventForm: FormGroup;
  // @ts-ignore
  public editEventForm: FormGroup;

  constructor(private _eventService: EventService,
              private _categoryService: CategoryService,
              private _userService: UserService,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private router: Router) { }



  ngOnInit(): void {
    this.getEventsByHttp();
    this.getCategoriesFromEventComp();
    this.getUsersFromEventComp();


    if(this.route.snapshot.paramMap.get('id') != null){
      //convert string to a number
      // @ts-ignore
      const id: number = +this.route.snapshot.paramMap.get('id');
      console.log('id from path: ', id);

      this._userService.getUserById(id)
        .subscribe(user => {
          this.userLoggedIn = user;
          console.log('logged in user: ', this.userLoggedIn);
        }, (error:HttpErrorResponse) => {
          alert(error.message);
        })
    }




    this.addEventForm = this.fb.group({
      //If no validation needed, remove "['', Validators.required]" and declare field as default
      title: [, Validators.required],
      capacity: ['', Validators.required],
      description: ['', Validators.required],
      eventDate: ['', Validators.required],
      location: ['', Validators.required],
      // category: null,
      // creator: null
      categoryId: 0,
      creatorId: 0
    });

    this.editEventForm = this.fb.group({
      //If no validation needed, remove "['', Validators.required]" and declare field as default
      id: [this.editEvent?.id, Validators.required],
      title: [this.editEvent?.title, Validators.required],
      capacity: [this.editEvent?.capacity, Validators.required],
      description: [this.editEvent?.description, Validators.required],
      eventDate: [this.editEvent?.eventDate, Validators.required],
      location: [this.editEvent?.location, Validators.required],
      categoryId: this.editEvent?.category.id,
      creatorId: 0,
      // participants: []
    });
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



  public onAddEvent(): void {
    this.addEventForm.patchValue({
      creatorId: this.userLoggedIn.id
    });
    if (this.addEventForm.valid) {
        console.log(this.addEventForm.value);
        let event: EventAction = {
          ...this.events,
          ...this.addEventForm.value
        };
        this._eventService.addEvent(event)
          .subscribe({
            next:() => {
              this.saveCompleted();
              this.getEventsByHttp();
              // @ts-ignore
              document.getElementById('add-event-form').click();}});
    }
  }

  public onEditEvent(event: Event): void {
    this.eventId = event.id;
    this.editEventForm.patchValue({
      creatorId: this.userLoggedIn.id,
    });
    // this.editEventForm.patchValue({
    //   participants: event,
    // });

    if (this.editEventForm.valid) {
      console.log(this.editEventForm.value);
      let event: EventAction = {
        ...this.events,
        ...this.editEventForm.value
      };
      this._eventService.updateEvent(this.eventId, event)
        .subscribe({
          next:() => {
            this.getEventsByHttp();
            // @ts-ignore
            document.getElementById('edit-event-form').click();}});
    }
  }

  public onJoinEvent(eventToJoin: Event): void {
    this._userService.joinEvent(this.userLoggedIn.id, eventToJoin.id)
      .subscribe(
        res => {
          console.log('HTTP response: ' ,res)
        },
        (err) => {
          alert(err)
        },
        () => {
          this.getEventsByHttp();
          // @ts-ignore
          document.getElementById('join-event-button-no').click();
          console.log('HTTP PUT request to join event completed.');
        }
      )
      // .subscribe({
      //   next:() => {
      //     this.getEventsByHttp();
      //     // @ts-ignore
      //     document.getElementById('join-event-button-no').click();
      //   }})

  }

  public onFilterEventByCategory(categoryId?: number): void {
    this.events = [];
    this._eventService.getEventsByCategory(categoryId)
      .subscribe(data => {
          this.events = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  public onShowCreatedEvents(): void {

    this.events = [];
    this._eventService.getEventsByCreator(this.userLoggedIn.id)
      .subscribe(data => {
          this.events = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  public onShowJoinedEvents(): void {

    this.events = [];
    this._eventService.getJoinedEvents(this.userLoggedIn.id)
      .subscribe(data => {
          this.events = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  public onDeleteEvent(eventToDelete: Event): void {
    this._eventService.deleteEvent(eventToDelete.id)
      .subscribe(
        res => {
          console.log('HTTP response: ' ,res)
        },
        (err) => {
          alert(err)
        },
        () => {
          this.getEventsByHttp();
          // @ts-ignore
          document.getElementById('delete-event-button-no').click();
          console.log('HTTP PUT request to delete event completed.');
        }
      )
    // .subscribe({
    //   next:() => {
    //     this.getEventsByHttp();
    //     // @ts-ignore
    //     document.getElementById('join-event-button-no').click();
    //   }})

  }



  public saveCompleted(): void {
    this.addEventForm.reset();
  }

  /**
   *
   * @param event
   * @param mode - which button is clicked
   */
  public onOpenModal(mode: String, event?: Event): void {
    const container = document.getElementById('main-container'); //div with id "main-container
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    if(mode === 'add') {
      //if it references id of a html element, then value should contain # eg. #addEventModal
      button.setAttribute('data-target', '#addEventModal');
    } else if(mode === 'edit') {
      this.editEvent = event;
      console.log('Event to edit: ', this.editEvent);
      button.setAttribute('data-target', '#editEventModal');
    } else if(mode === 'delete') {
      this.deleteEvent = event;
      console.log('Event to delete: ', this.deleteEvent);
      button.setAttribute('data-target', '#deleteEventModal');
    } else if(mode === 'join') {
      this.joinEvent = event;
      console.log('Event to join: ', this.joinEvent);
      button.setAttribute('data-target', '#joinEventModal');
    } else if(mode === 'logout') {
      button.setAttribute('data-target', '#logoutModal');
    }

    // @ts-ignore
    container.appendChild(button);
    button.click();
  }

  // public checkJoined(event: Event): void {
  //   this._eventService.getJoinedEvents(event.creator.id)
  //     .subscribe(data => {
  //         this.listOfJoinedEvents = data;
  //         console.log(data);},
  //       (error: HttpErrorResponse) => {alert(error.message)});
  //   // @ts-ignore
  //   this.checkEvent = this.listOfJoinedEvents.find(eventS => eventS.id === event.id);
  //   console.log("event joined:", this.checkEvent)
  //   if(this.checkEvent != null) {
  //
  //   } else {
  //
  //   }
  // }




}
