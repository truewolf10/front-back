import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PhaseTemplateModel} from '../models/phase-template.model';
import {BASE_URL, DOWNLOAD_URL, TEMPLATE_URL} from '../../end-points.const';
import {HttpGenericService} from './http-generic.service';

@Injectable()
export class PhaseTemplateService{
  constructor(private http: HttpClient, private httpGenericService : HttpGenericService) {}

  public getAll(queryParams = null): Observable<PhaseTemplateModel[]> {
    return this.http.get<PhaseTemplateModel[]>(BASE_URL + TEMPLATE_URL);
  }

  public getById(id: number): Observable<PhaseTemplateModel> {
    return this.http.get<PhaseTemplateModel>(BASE_URL + TEMPLATE_URL + id);
  }

  public add(template: PhaseTemplateModel) : Observable<any> {
    return this.http.post(BASE_URL + TEMPLATE_URL, template);
  }

  public saveDocByTemplateId(id: number): Observable<any> {
    const options = {
      headers : this.createHeaderOption(),
      responseType: 'blob'
    };
    return this.http.get(BASE_URL + TEMPLATE_URL + DOWNLOAD_URL + id, {
      headers : this.createHeaderOption(),
      responseType: 'blob'
    });
  }

  private createHeaderOption(){
    return this.httpGenericService.setHeaders()['headers'];
  }

}
