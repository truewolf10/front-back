import {Component, Inject, OnInit} from '@angular/core';
import {NavBarItemModel} from '../../models/nav-bar-item.model';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  userRole : String;
  public currentUserName : any;
  public isCollapsed = false;
  authSubcriber: Subscription;
  public navBarItems: NavBarItemModel[] = [{
    name: 'Home',
    routerLink: '/home',
  }, {
    name: 'Project',
    routerLink: '/project',
  }, {
    name: 'Phase',
    routerLink: '/phase',
  }, {
    name: 'Template',
    routerLink: '/phase-template',
  }, {
    name: 'Users',
    routerLink: '/users'
  }];
  constructor(private authService : AuthService, private router : Router) {

  }

  ngOnInit() {
    this.userRole = localStorage.getItem("role").toString(); //Identifier User Role from LocalStorage
    this.authSubcriber = this.authService.isLogged.subscribe((isLogged : boolean)=>{
      this.currentUserName = JSON.parse(localStorage.getItem('currentUser'))['username'];
    });
    if (performance.navigation.type == 1){
      if (localStorage.getItem('currentUser')) {
        this.currentUserName = JSON.parse(localStorage.getItem('currentUser'))['username'];
      }
    }
  }


  logOut() {
    this.authService.logOut()
      .subscribe(
        data => {
          this.router.navigate(['/login']);
        },
        error => {

        });
  }
}
