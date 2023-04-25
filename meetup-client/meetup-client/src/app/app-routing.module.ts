import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {EventComponent} from "./event/event.component";
import {EventForNonRegisteredComponent} from "./event-for-non-registered/event-for-non-registered.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent}, //login page
  {path: 'events', component: EventForNonRegisteredComponent}, //list all events
  {path: 'user/:id/events', component: EventComponent}, // as a logged in user
  {path: '', component: EventForNonRegisteredComponent},
]


/**
 * forRoot() because you configure the router at the application's root level.
 * The forRoot() method supplies the service providers and directives needed for routing,
 * and performs the initial navigation based on the current browser URL.
 */
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
