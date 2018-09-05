import {Component, OnInit} from '@angular/core';
import {HttpGenericService} from '../../services/http-generic.service';
import {PHASE_URL, PROJECT_URL, STATUS_URL, TEMPLATE_URL, TEMPLATES_URL} from '../../../end-points.const';
import {ProjectModel} from '../../models/project.model';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {PhaseTemplateModel} from '../../models/phase-template.model';
import {ProjectPhaseModel} from '../../models/projectPhase.model';
import {StatusInformation} from '../../models/statusInformation';
import {fakeAsync} from '@angular/core/testing';
import * as FileSaver from 'file-saver';
import {PhaseTemplateService} from '../../services/phase-template.service';


@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.html']
})
export class ProjectDetailsComponent implements OnInit {

  project : ProjectModel;
  id : number;
  name = "";
  size : number;
  statusInfo : StatusInformation[] = new Array<StatusInformation>();

  statusProject : string = "TO_DO";

  templatesMap: Map<number, PhaseTemplateModel[]> = new Map();
  showTemplates : Map<ProjectPhaseModel, boolean> = new Map();
  statusPhase : Map<number,string> = new Map<number, string>();
  templates = new Array<PhaseTemplateModel>();

  //for status color
  isToDO=false;
  isProgress=false;
  isDone=false;
  isRevise=false;

  constructor(private httpService : HttpGenericService,
              private route: ActivatedRoute,
              private templateService: PhaseTemplateService) {}

  ngOnInit(): void {
    this.route.params.subscribe( param =>
      this.id = param['id']
    );

    this.httpService.getById(PROJECT_URL, this.id).subscribe( response => {
      this.project = response;
      this.project.phasesDtos.forEach(elem => {
        this.statusPhase.set(elem.id,"TO_DO");
        this.showTemplates.set(elem, false);
      });
    });

    this.httpService.getAll(STATUS_URL).subscribe( statusInformation=>{
      this.statusInfo = statusInformation;
    })

  }

  getTemplates(id: number) {
    this.httpService.getAll(TEMPLATES_URL + id).subscribe( result => {

      this.templatesMap.set(id, result);
    });
  }

  clicked(phase: any): void {
    if (this.showTemplates.get(phase)) {
      this.showTemplates.set(phase, false);
    } else {
      this.showTemplates.set(phase, true);
      this.getTemplates(phase.id);
    }
  }

  getShowState(key: ProjectPhaseModel): boolean {

    return this.showTemplates.get(key);
  }

  getTemplateByPhaseId(id: number): any {
    return this.templatesMap.get(id);
  }


  changeStatusTemplate(phaseId : number, templateId : number, statusInfo : string){
    var ok : boolean = true;
    for (let i in this.statusInfo){
      if ((this.statusInfo[i].idProject == this.project.id) && (this.statusInfo[i].idPhase == phaseId) && (this.statusInfo[i].idTemplate == templateId)){
        ok = false;
        const statusInformations = {
          id : this.statusInfo[i].id,
          idProject : this.statusInfo[i].idProject,
          idPhase : this.statusInfo[i].idPhase,
          idTemplate : this.statusInfo[i].idTemplate,
          status : statusInfo
        }
        this.httpService.update(STATUS_URL,statusInformations).subscribe(()=>{
          this.updateFromFront(statusInformations);
        });
      }
    }
    if (ok) {
      const statusInformations = {
        id : 0,
        idProject : this.project.id,
        idPhase : phaseId,
        idTemplate : templateId,
        status : statusInfo
      }
      this.httpService.add(STATUS_URL,statusInformations).subscribe(newIndex=> {
        statusInformations.id = newIndex;
        this.statusInfo.push(statusInformations);

      })
    }

  }

  getStatusTemplate(phaseId : number, templateId : number){
    for (let i in this.statusInfo){
      if ((this.statusInfo[i].idProject == this.project.id) && (this.statusInfo[i].idPhase == phaseId) && (this.statusInfo[i].idTemplate == templateId)){
                                                      return this.statusInfo[i].status;
      }
    }
  }

  checkStatusPhase(phase : ProjectPhaseModel){
     var okDone,okProgress,okDo,okRevise : boolean;
     okDone = false;
    okProgress = false;
    okDo = false;
    okRevise = false;

    this.isToDO=false;
    this.isProgress=false;
    this.isDone=false;
    this.isRevise=false;

     for (let i in this.statusInfo){
       if ((this.statusInfo[i].idProject == this.project.id) && (this.statusInfo[i].idPhase == phase.id))
       {
         if (this.statusInfo[i].status == "TO_DO"){
          okDo = true;
         }

         if (this.statusInfo[i].status == "IN_PROGRESS"){
           okProgress  = true;
         }

         if (this.statusInfo[i].status == "DONE"){
           okDone  = true;
         }

         if (this.statusInfo[i].status == "TO_REVISE"){
           okRevise  = true;
         }
       }

       if (okDo) {this.statusPhase.set(phase.id,"TO_DO")}
          else if (okProgress) {this.statusPhase.set(phase.id,"IN_PROGRESS")}
              else if (okRevise) {this.statusPhase.set(phase.id,"TO_REVISE")}
                  else if (okDone) {this.statusPhase.set(phase.id,"DONE")}
     }
  }

  checkStatusProject(){
    var okDone,okProgress,okDo,okRevise : boolean;
    okDone = false;
    okProgress = false;
    okDo = false;
    okRevise = false;

    for (let status of this.statusPhase){
      if (status[1] == "TO_DO"){ okDo = true;}
      if (status[1] == "IN_PROGRESS"){ okProgress = true;}
      if (status[1] == "DONE"){ okDone = true;}
      if (status[1] == "TO_REVISE"){ okRevise = true;}
    }
    if (okDo) {this.statusProject = "TO_DO"; this.isToDO = true;}
    else if (okProgress) {this.statusProject = "IN_PROGRESS"; this.isProgress = true;}
    else if (okRevise) {this.statusProject = "TO_REVISE"; this.isRevise = true;}
    else if (okDone) {this.statusProject = "DONE";this.isDone = true;}

  }

  downloadDocument(template: PhaseTemplateModel) {
    this.templateService.saveDocByTemplateId(template.id).subscribe((response: any) => {
      const data = response;
      const blob = new Blob([data], {type: 'application/pdf'});
      const filename = template.documentName;
      FileSaver.saveAs(blob, filename);
    });
  }

  updateFromFront(status : StatusInformation){ //Update project from front
    for (let index in this.statusInfo){
      if (this.statusInfo[index].id == status.id){
        this.statusInfo[index] = status;
      }
    }
  }
}
