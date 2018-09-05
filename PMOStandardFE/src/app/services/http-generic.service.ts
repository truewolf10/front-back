import {Injectable, OnDestroy, OnInit,Inject} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {BASE_URL} from '../../end-points.const';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import {AuthService} from './auth.service';

@Injectable()
export  class HttpGenericService implements OnDestroy {

  userLogged: any;
  httpOptions: any;

  authSubcriber: Subscription;

  constructor(@Inject(LOCAL_STORAGE) private storage: WebStorageService,private http: HttpClient, private authService : AuthService) {}

  public authSubscription() {
    this.authSubcriber = this.authService.isLogged.subscribe((isLogged: boolean)=>
      {
        this.userLogged = JSON.parse(localStorage.getItem('currentUser'));
        if (isLogged) {
          let base64Credential: string = btoa(this.userLogged['username'] + ':' + this.userLogged['password']);

          // this.httpOptions = {
          //       headers: new HttpHeaders()
          //         .set('Accept', 'application/json')
          //         .set('Authorization', 'Basic ' + base64Credential)
          //     };
          // vechea varianta in care setam un field global si nu in local stoarage, astfel
          // la un refresh se pierdeau credentialele la un refresh, de aici solutia cu localStorage.

          const httpOptions = new HttpHeaders()
            .set('Accept', 'application/json')
            .set('Authorization', "Basic " + base64Credential);

          // let options = {
          //   // headers:  headers la fel ca si
          //   headers: httpOptions
          // };

          localStorage.setItem('httpOptions', JSON.stringify(httpOptions));
        }
      }
    );
  }

  public setHeaders(){
    let httpOptions = JSON.parse(localStorage.getItem('httpOptions'))['lazyUpdate'];
    const httpOptionsRezerved = {
      headers: new HttpHeaders()
        .set(httpOptions[0].name, httpOptions[0].value)
        .set(httpOptions[1].name, httpOptions[1].value)
    };
    return httpOptionsRezerved;
  }

  public getAll(url: string, queryParams = null): Observable<any> {
    return (localStorage.getItem('httpOptions'))
      ? this.http.get<any>(BASE_URL + url, this.setHeaders()) : this.http.get<any>(BASE_URL + url);
  }

  public getById(url: string, id: number, queryParams = null): Observable<any> {
    return this.http.get<any>(BASE_URL + url + id, this.setHeaders());
  }

  public add(url: string, body: any): Observable<any> {
    return (localStorage.getItem('httpOptions'))
      ? this.http.post(BASE_URL + url, body, this.setHeaders()) : this.http.post(BASE_URL + url, body);
  }

  public update(url: string, body: any): Observable<any> {
    return this.http.put(BASE_URL + url, body, this.setHeaders());
  }

  public delete(url: string, id: number): Observable<any> {
    return this.http.delete(BASE_URL + url + id, this.setHeaders());
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.authSubcriber.unsubscribe();
  }
}
