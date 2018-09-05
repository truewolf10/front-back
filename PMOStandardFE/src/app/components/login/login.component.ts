import { Component, OnInit } from '@angular/core';
import {UserModel} from '../../models/user.model';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {AppComponent} from '../../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user : UserModel = new UserModel();
  errorMessage : string;

  constructor(private authService : AuthService, private router : Router) { }

  ngOnInit() {
  }

  logIn(){
    this.authService.logIn(this.user).subscribe(
      data=> {
        this.router.navigate(['/home']);
      },err=>{
        this.errorMessage = "error : Username or password is incorrect";
      }
    )
  }

}
