import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserModel} from '../models/user.model';
import {BASE_URL, LOGIN_URL, LOGOUT_URL, USER_URL} from '../../end-points.const';
import {map} from 'rxjs/operators';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLogged : Subject<any> = new Subject();
  constructor(public http : HttpClient) { }


  logIn(user : UserModel){
    // let headers = new HttpHeaders();
    // headers.append('Accept','application/json');
    //
    let base64Credential : string = btoa(user.email + ':' + user.password);
    // headers.append("Authorization","Basic" + base64Credential);


    const httpOptions = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Authorization', "Basic " + base64Credential);

    let options = {
      // headers:  headers la fel ca si
      headers: httpOptions
    };


    return this.http.get(BASE_URL + USER_URL + LOGIN_URL, options).pipe(
      map( response  => {
        let userResponse = response['principal']; // TODO : remember this fucking stuff!!!!
        if (userResponse){
          localStorage.setItem('role',userResponse['authorities'][0]['authority']);
          userResponse['password'] = user.password;
          localStorage.setItem('currentUser', JSON.stringify(userResponse));
          localStorage.setItem('isLoggedFlagRefresh', 'true');
          this.isLogged.next(true);
        }
      })
    );
  }

  logOut(){
    const httpOptions = new HttpHeaders()
      .set('Accept', 'application/json');
    let options = {
      // headers:  headers la fel ca si
      headers: httpOptions
    };
    return this.http.post(BASE_URL + LOGOUT_URL,{}, options).pipe(
      map(response =>{
        localStorage.removeItem('currentUser');
        localStorage.setItem('isLoggedFlagRefresh', 'false');
        this.isLogged.next(false);
        location.reload();
      })
    );
  }

}
