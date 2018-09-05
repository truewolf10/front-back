import { Injectable } from '@angular/core';
import {ProjectPhaseModel} from '../models/projectPhase.model';
import { Observable } from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {PhaseTemplateModel} from '../models/phase-template.model';
import {HttpGenericService} from './http-generic.service';

@Injectable()
export class ProjectPhaseService {

  constructor(private http: HttpClient, private httpGenericService : HttpGenericService) { }

  public getProjectPhases(): Observable<ProjectPhaseModel[]> {
    return this.http.get<ProjectPhaseModel[]>('http://localhost:8080/phase');
  }

  public getPhasesTemplates(id :number): Observable<PhaseTemplateModel[]> {
    return this.http.get<PhaseTemplateModel[]>('http://localhost:8080/phase/temp/'+id);
  }

  public addProjectPhaseWithTemplate (phase: ProjectPhaseModel, params: HttpParams): Observable<any> {
    const options = {
      headers : this.createHeaderOption(),
      params : params};
    return this.http.post('http://localhost:8080/phase/params',phase,options);
  }

  public update(phase: ProjectPhaseModel, params: HttpParams): Observable<any> {
    const options = {
      headers : this.createHeaderOption(),
      params : params};
    console.log(options);
    return this.http.put('http://localhost:8080/phase/params',phase,options);
  }

  private createHeaderOption(){
    return this.httpGenericService.setHeaders()['headers'];
  }
}
