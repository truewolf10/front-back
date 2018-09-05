import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BASE_URL} from '../../end-points.const';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  constructor(private http : HttpClient) { }

  public getActivation(url: string, queryParams : HttpParams): Observable<any> {
    return this.http.get(BASE_URL + url , {params : queryParams}).pipe(
      map( (response)=>{
          localStorage.setItem('activation', JSON.stringify(response));
        }
    ))
  }

  public addNewPassword(url:string, body : any) : Observable<any>{
    return this.http.post(BASE_URL + url, body);
  }
}
