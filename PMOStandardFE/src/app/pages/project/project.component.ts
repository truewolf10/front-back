import {Component, Inject, OnInit, TemplateRef} from '@angular/core';
import {ProjectModel} from '../../models/project.model';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {PHASE_URL, PROJECT_URL, STATUS_URL, TEMPLATES_URL} from '../../../end-points.const';
import {HttpGenericService} from '../../services/http-generic.service';
import {ProjectPhaseModel} from '../../models/projectPhase.model';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import {ProjectPhaseService} from '../../services/project-phase.service';
import {StatusInformation} from '../../models/statusInformation';
import {Observable} from 'rxjs';


@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})

export class ProjectComponent implements OnInit {

  //Arrays
  projects : ProjectModel[] = new Array<ProjectModel>();
  phases : ProjectPhaseModel[] = new Array<ProjectPhaseModel>();
  phasesChecked : Map<number,boolean> = new Map();


  //modelFields
  projectName = "";
  projectSize;
  projectDescription = "";
  projectIdHidden;
  isAdded=true;
  userRole: String;
  statusInfo : StatusInformation;

  //modal popup
  modalRef: BsModalRef;

  constructor(private projectService : ProjectPhaseService,
              private httpService : HttpGenericService,
              private modalService: BsModalService) { }

  ngOnInit(): void {
    this.userRole = localStorage.getItem("role").toString(); //Identifier
    this.httpService.getAll(PROJECT_URL).subscribe(response=>{
      this.projects = response;
    });
    this.httpService.getAll(PHASE_URL).subscribe(response=>{
      this.phases = response;
      for (let phase of this.phases){
        this.phasesChecked.set(phase.id, false);
      }
    });
    this.httpService.getAll(STATUS_URL).subscribe( statusInformation=>{
      this.statusInfo = statusInformation;
    })

  }


  add(){
    const project = {
      id : 0,
      name : this.projectName,
      size : this.projectSize,
      description : this.projectDescription,
      phasesDtos : []
    };

    if (!this.isAdded){
      project.id = this.projectIdHidden;
    }

    this.phasesChecked.forEach((value,phaseId)=> {
      if (value)
        project.phasesDtos.push({"id": phaseId})
    })


    this.isAdded ? this.httpService.add(PROJECT_URL,project).subscribe(newIndex=>{
      project.id = newIndex;
      this.projects.push( project);
      this.addUpdateStatus(project);
      this.modalRef.hide();
    }) : this.httpService.update(PROJECT_URL,project).subscribe(index =>{
      this.updateFromFront(project);
      this.addUpdateStatus(project)
      this.modalRef.hide();
    });

  }

  openModal(template: TemplateRef<any>, isAdded = false) {
    this.isAdded = isAdded;
    if (this.isAdded) {
      for (let phase of this.phases) {
        this.phasesChecked.set(phase.id, false);
      }
      this.projectName = '';
      this.projectSize = null;
      this.projectDescription = ""
    }
    this.modalRef = this.modalService.show(template);
  }

  changeCheckbox(phase : any){
    this.phasesChecked.set(phase.id, !this.phasesChecked.get(phase.id));
  }

  delete(project : ProjectModel){
    this.deleteStatus(project);
    this.httpService.delete(PROJECT_URL, project.id).subscribe();
  }

  update(project:ProjectModel, template: TemplateRef<any>, projectId : number){
    this.checkboxFalse();
    for (let phase of project.phasesDtos){
      this.phasesChecked.set(phase.id, true);
    }
    this.projectIdHidden = projectId;
    this.projectName = project.name;
    this.projectSize = project.size;
    this.projectDescription = project.description;
    this.openModal(template);
  }

  checkboxFalse(){ //Make all checkboxes unchecked
    var i;
    for (i = 0; i < this.phases.length; i++) {
      this.phasesChecked.set(this.phases[i].id, false);
    }
  }

  addUpdateStatus(project : ProjectModel){
    this.phasesChecked.forEach((value,phaseId)=>{
      if (value)
        this.httpService.getById(TEMPLATES_URL,phaseId).subscribe(templateId=>{
          for (let k in templateId) {
            this.httpService.getAll(STATUS_URL).subscribe( statusInformation=>{
              this.statusInfo = statusInformation;
            })
            var ok:boolean = true;
            for (let i in this.statusInfo) { // When edit a project with name/phase information , Status change in "To Revise"
              if ((this.statusInfo[i].idProject == project.id) && (this.statusInfo[i].idPhase == phaseId) && (this.statusInfo[i].idTemplate == templateId[k].id)) {
                ok = false;
                const statusInformations = {
                  id: this.statusInfo[i].id,
                  idProject: this.statusInfo[i].idProject,
                  idPhase: this.statusInfo[i].idPhase,
                  idTemplate: this.statusInfo[i].idTemplate,
                  status: "TO_REVISE"
                }

                this.httpService.update(STATUS_URL, statusInformations).subscribe(()=>{



                });
              }
            }
            if ((ok) && project.id>0){ // if add a new phase to project , add a new status
              const statusInformations = {
                id: 0,
                idProject: project.id,
                idPhase: phaseId,
                idTemplate: templateId[k].id,
                status: "TO_DO"
              }
              this.httpService.add(STATUS_URL,statusInformations).subscribe();
            }

          }
        })
    });
  }

  deleteStatus(project : ProjectModel){
    for (let i in this.statusInfo){
      if (this.statusInfo[i].idProject == project.id){
        this.httpService.delete(STATUS_URL,this.statusInfo[i].id).subscribe(()=>{
           this.deleteFromFront(project); //delete from front
          }
        );
      }
    }
  }

  deleteFromFront(project : ProjectModel){ //Delete project from front
    for (var index in this.projects) { //delete from front
      if (this.projects[index].id == project.id) {
        this.projects.splice(Number(index),1);
      }
    }
  }

  updateFromFront(project : ProjectModel){ //Update project from front
    for (let index in this.projects){
      if (this.projects[index].id == project.id){
        this.projects[index] = project;
      }
    }
  }

}
