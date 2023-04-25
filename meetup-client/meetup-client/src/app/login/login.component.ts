import {Component, OnInit} from '@angular/core';
import {UserService} from "../user/user.service";
import {Router} from "@angular/router";
import {User} from "../user/model/user";
import {LoginUser} from "../user/model/loginUser";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {EventAction} from "../event/model/eventAction";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  user: User | undefined;
  // @ts-ignore
  userLoginDetails: LoginUser;

  // @ts-ignore
  loginForm: FormGroup;

  constructor(private _userService: UserService, private router : Router, private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      //If no validation needed, remove "['', Validators.required]" and declare field as default
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  public login(): void {
    if(this.loginForm.valid && this.loginForm.dirty) {
      console.log(this.loginForm.value);
      let user: LoginUser = {
        ...this.user,
        ...this.loginForm.value
      };
      this._userService.validateLogin(user)
        .subscribe(data => {
          this.user = data;
          this.router.navigate([`/user/${this.user.id}/events`]);
        }, (error: HttpErrorResponse) => {
          alert(error.message);
        });
    }

  }

}
