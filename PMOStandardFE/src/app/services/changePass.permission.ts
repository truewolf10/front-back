import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable()
export class ChangePassPermission implements CanActivate{

  constructor(private router : Router){

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (localStorage.getItem('activation') == 'true'){
      return true;
    }
    this.router.navigate(['/login'], {queryParams : {returnUrl : state.url}});
    return false;
  }
}
