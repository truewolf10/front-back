import {Component, Inject, OnInit, TemplateRef} from '@angular/core';
import {ProjectPhaseService} from '../../services/project-phase.service';
import {ProjectPhaseModel} from '../../models/projectPhase.model';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {PhaseTemplateModel} from '../../models/phase-template.model';
import {HttpClient, HttpParams} from '@angular/common/http';
import {PHASE_URL, PROJECT_URL, TEMPLATE_URL, TEMPLATES_URL} from '../../../end-points.const';
import {HttpGenericService} from '../../services/http-generic.service';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import {ProjectModel} from '../../models/project.model';

@Component({
  selector: 'app-phase',
  templateUrl: './phase.component.html',
  styleUrls: ['./phase.component.scss']
})
export class PhaseComponent implements OnInit {
  //fields
  phasesName = '';
  phaseDescription = '';
  statusPhase = new Map<String,String>()

  modalRef: BsModalRef;

  //array
  public phases: ProjectPhaseModel[] = new Array<ProjectPhaseModel>();
  public templates: PhaseTemplateModel[] = new Array<PhaseTemplateModel>();
  public templateChecked : Map<number,boolean> =new Map();
  userRole : String;

  constructor(private httpService: HttpGenericService,
              private httpClient: HttpClient,
              private modalService: BsModalService,
              private projectPhaseService: ProjectPhaseService) {}

  ngOnInit(): void {
    this.userRole = localStorage.getItem("role").toString(); //Identifier User Role from LocalStorage
    this.httpService.getAll(PHASE_URL)
      .subscribe(phaseView => {
        this.phases = phaseView;
      });

    this.httpService.getAll(TEMPLATE_URL)
      .subscribe(templateView => {
        this.templates = templateView;
        this.checkboxFalse();
      })
  }

  add() {
    const phase = {
      id: 0,
      name: this.phasesName,
      description : this.phaseDescription,
      statusPhase: this.statusPhase
    }

    let params = new HttpParams();
    var i;
    for (i = 1; i <= this.templateChecked.size; i++) {
      if (this.templateChecked.get(i)) {
        params = params.append("id", i);
      }
    }
    this.projectPhaseService.addProjectPhaseWithTemplate(phase, params).subscribe(newIndex =>{
      phase.id = newIndex;
      //location.reload();
      this.phases.push(phase);
      this.modalRef.hide();});
    }

  delete(phase: ProjectPhaseModel) {
    this.httpService.delete(PHASE_URL, phase.id).subscribe(()=>this.deleteFromFront(phase));
  }

  update(phase: ProjectPhaseModel) {
    const phases = {
      id : phase.id,
      name : phase.name,
      description : phase.description,
      statusPhase: phase.statusPhase
    }
    let params = new HttpParams();
    this.templateChecked.forEach((value, templateId) =>{
      if(value) {
        params = params.append("id", templateId.toString());
      }
    });

    this.projectPhaseService.update(phases,params).subscribe(()=>{
      this.updateFromFront(phases);
      this.modalRef.hide();});
  }

  openModal(template: TemplateRef<any>) {
    this.phasesName = '';
    this.phaseDescription = '';
    this.modalRef = this.modalService.show(template);
  }

  checkCheckbox(id :number){ //if template exist in phase, check the checkbox
    this.httpService.getById(TEMPLATES_URL,id).subscribe(result2 =>{
      this.checkboxFalse();
      for (let i of result2){
        this.templateChecked.set(i.id,true);
      }
    })
  }

  changeCheckbox(template : any){
    this.templateChecked.set(template.id, !this.templateChecked.get(template.id));
  }

  checkboxFalse(){ //Make all checkboxes unchecked
    for (let template of this.templates){
      this.templateChecked.set(template.id,false);
    }
  }

  deleteFromFront(phase : ProjectPhaseModel){
    for (var index in this.phases) { //delete from front
      if (this.phases[index].id == phase.id) {
        this.phases.splice(Number(index),1);
      }
    }
  }

  updateFromFront(phase : ProjectPhaseModel){ //Update project from front
    for (let index in this.phases){
      if (this.phases[index].id == phase.id){
        this.phases[index] = phase;
      }
    }
  }
}
