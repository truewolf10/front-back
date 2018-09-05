import {Component, Inject, OnInit} from '@angular/core';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import {UserModel} from './models/user.model';
import {AuthService} from './services/auth.service';
import {HttpGenericService} from './services/http-generic.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  public currentUser : UserModel;
  public isLogged : boolean = false;
  public isLoggedFlagRefresh : boolean = false;


  constructor(private authService : AuthService, private httpGeneric: HttpGenericService){

  }

  ngOnInit(): void {
    this.httpGeneric.authSubscription();

    this.authService.isLogged.subscribe(
      (isLogged: boolean) => {
        this.isLogged = isLogged;
      });
    this.isLoggedFlagRefresh = JSON.parse(localStorage.getItem('isLoggedFlagRefresh'));
  }
}
