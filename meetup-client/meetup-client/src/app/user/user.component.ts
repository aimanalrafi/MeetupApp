import { Component, OnInit } from '@angular/core';
import {User} from "./model/user";
import {UserService} from "./user.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public users = new Array<User>();

  public getCreator: User | undefined;

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this.getUsersByHttp();
  }
  public getUsersByHttp(): void {
    this._userService.getUsersByHttp()
      .subscribe(data =>
        {this.users = data;
          console.log(data);}
      );
  }

  public getUserById(userId: number): void {
    this._userService.getUserById(userId)
      .subscribe(data => {
          this.getCreator = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

}
