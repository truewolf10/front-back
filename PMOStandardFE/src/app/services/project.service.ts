import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ProjectModel} from '../models/project.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ProjectPhaseModel} from '../models/projectPhase.model';
import {PhaseTemplateModel} from '../models/phase-template.model';

@Injectable()
export class ProjectService {

  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(private http: HttpClient) {}

  getAllProjects() : Observable<ProjectModel[]>{
    return this.http.get<ProjectModel[]>("http://localhost:8080/project");
  }

  public getPhasesTemplates(id :number): Observable<PhaseTemplateModel[]> {
    return this.http.get<PhaseTemplateModel[]>('http://localhost:8080/phase/temp'+id);
  }

  addProject(project : ProjectModel) : Observable<any>{
    return this.http.post("http://localhost:8080/project", project);
  }

  deleteProject(id: number ): Observable<any> {
    return this.http.delete('http://localhost:8080/project/' + id);
  }

  updateProject(project : ProjectModel): Observable<any> {
    return this.http.put('http://localhost:8080/project',project);
  }
}
