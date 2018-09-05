import { Component, Inject, OnInit, TemplateRef } from '@angular/core';
import { PhaseTemplateModel } from '../../models/phase-template.model';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {HttpGenericService} from '../../services/http-generic.service';
import {BASE_URL, TEMPLATE_URL, UPLOAD_URL} from '../../../end-points.const';
import {FileItem, FileUploader, FileUploaderOptions} from 'ng2-file-upload';
import * as FileSaver from 'file-saver';
import { saveAs } from 'file-saver/FileSaver';
import {PhaseTemplateService} from '../../services/phase-template.service';

@Component({
  selector: 'app-phase-template',
  templateUrl: './phase-template.component.html',
  styleUrls: ['./phase-template.component.scss']
})
export class PhaseTemplateComponent implements OnInit {
  name = '';
  url = '';
  documentName = '';
  descriptionTemplate = '';
  userRole: String;
  templates = new Array<PhaseTemplateModel>();
  uploader: FileUploader;
  modalRef: BsModalRef;
  fileMap: Map<PhaseTemplateModel, boolean> = new Map();


  constructor(private httpService: HttpGenericService,
              private modalService: BsModalService,
              private templateService: PhaseTemplateService) {}

  ngOnInit(): void {
    this.userRole = localStorage.getItem("role").toString(); //Identifier User Role from LocalStorage
    this.httpService.getAll(TEMPLATE_URL).subscribe(response => {
      this.templates = response;
      this.templates.forEach( elem => {
        if(elem.url == '' && elem.documentName != '') {
          this.fileMap.set(elem, true);
        }

        if(elem.url != '' && elem.documentName == '') {
          this.fileMap.set(elem, false);
        }
      })
    });

    const options = this.httpService.setHeaders()['headers']['lazyUpdate'];

    let fileUploadOption: FileUploaderOptions = {};
    fileUploadOption.headers = [
      {name: options[0].name, value : options[0].value },
      {name: options[1].name, value : options[1].value}
      ];
    fileUploadOption.url = BASE_URL + TEMPLATE_URL + UPLOAD_URL;
    this.uploader = new FileUploader(fileUploadOption);

    this.uploader.onBeforeUploadItem = (item) => {
      item.withCredentials = false;
    };
  }

  openModal(template: TemplateRef<any>) {
    this.name = '';
    this.url = '';
    this.documentName = '';
    this.descriptionTemplate = '';
    this.modalRef = this.modalService.show(template);
  }

  add() {
    const template = {
      id: 0,
      name: this.name,
      url: this.url,
      documentName: this.documentName,
      description : this.descriptionTemplate,
    };

    if(this.uploader.queue.length > 0) {
      this.upload(template);
    } else {
      this.httpService.add(TEMPLATE_URL, template).subscribe(newIndex =>{
        template.id = newIndex;
        this.templates.push(template);
        this.modalRef.hide();
      });
    }
  }

  update(template: PhaseTemplateModel){
    if(this.uploader.queue.length > 0) {
      this.upload(template)
    } else {
      this.httpService.update(TEMPLATE_URL, template).subscribe(()=> {
        this.updateFromFront(template);
        this.modalRef.hide();
      });
    }
  }

  upload(template: PhaseTemplateModel) {
    this.uploader.setOptions({additionalParameter: template});
    const file: FileItem = this.uploader.queue[0];
    file.upload();
    file.onComplete = () => {
      // console.log(response);
      // console.log(status);
      // console.log(headers);
      location.reload();
    }
  }

  downloadDocument(template: PhaseTemplateModel) {
    this.templateService.saveDocByTemplateId(template.id).subscribe((response: any) => {
      const data = response;
      const blob = new Blob([data], {type: 'application/pdf'});
      const filename = template.documentName;
      FileSaver.saveAs(blob, filename);
    });
  }

  getIsFile(template: PhaseTemplateModel): boolean {
    return this.fileMap.get(template);
  }

  delete(template : PhaseTemplateModel){
    this.httpService.delete(TEMPLATE_URL,template.id).subscribe(()=>this.deleteFromFront(template));
  }

  deleteFromFront(template : PhaseTemplateModel){
    for (var index in this.templates) { //delete from front
      if (this.templates[index].id == template.id) {
        this.templates.splice(Number(index),1);
      }
    }
  }

  updateFromFront(template : PhaseTemplateModel){ //Update project from front
    for (let index in this.templates){
      if (this.templates[index].id == template.id){
        this.templates[index] = template;
      }
    }
  }
}
