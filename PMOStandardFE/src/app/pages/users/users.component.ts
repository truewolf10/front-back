import {Component, Inject, OnInit, TemplateRef} from '@angular/core';
import {HttpGenericService} from '../../services/http-generic.service';
import {LOGIN_URL, PHASE_URL, REGISTER_URL, UNIT_URL, USER_URL, USERROLE_URL} from '../../../end-points.const';
import {UserModel} from '../../models/user.model';
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ProjectModel} from '../../models/project.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users : UserModel[] = new Array<UserModel>();
  userRole= '';

  firstName = '';
  lastName = '';
  password = '';
  email = '';
  businessUnit = this.businessUnit;
  userRoleCheck = this.userRole;

  units : string[] = [];
  roles : string[] = [];

  modalRef: BsModalRef;
  constructor(private modalService: BsModalService,
              private httpService: HttpGenericService) {}

  ngOnInit(): void {
    this.userRole = localStorage.getItem("role").toString(); //Identifier User Role from LocalStorage
    if (this.userRole.toString() == "ADMIN") {
      this.httpService.getAll(USER_URL).subscribe(response => {
        this.users = response;

      })

      this.httpService.getAll(UNIT_URL).subscribe(response => {
        this.units = response;
      })

      this.httpService.getAll(USERROLE_URL).subscribe(response => {
        this.roles = response;
      })
    }
  }

  openModal(template: TemplateRef<any>, user : UserModel) {
    this.businessUnit=user.businessUnit
    this.userRoleCheck=user.userRole;
    this.modalRef = this.modalService.show(template);
  }

  delete(user: UserModel){
    this.httpService.delete(USER_URL, user.id).subscribe(()=> this.deleteFromFront(user));
  }

  update(user: UserModel) {
    const users = {
      id: user.id,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      password: user.password,
      businessUnit: this.businessUnit,
      userRole: this.userRoleCheck
    };
    this.httpService.update(USER_URL, users).subscribe(()=> {
      this.updateFromFront(users);
      this.modalRef.hide();
    });
  }

  selectCheckboxUnits(unit : any){
    this.businessUnit = unit;
  }

  selectCheckboxRole(role : any){
    this.userRoleCheck= role;
  }

  checkRole(roleChecbox : any,roleUser:any){
    if (roleUser == roleChecbox) {
      return true;
    }else{
      return false;
    }
  }

  checkUnit(unitChecbox : any,unitUser:any){
    if (unitUser == unitChecbox) {
      return true;
    }else{
      return false;
    }
  }

  deleteFromFront(user : UserModel){ //Delete project from front
    for (var index in this.users) { //delete from front
      if (this.users[index].id == user.id) {
        this.users.splice(Number(index),1);
      }
    }
  }

  updateFromFront(user : UserModel){ //Update project from front
    for (let index in this.users){
      if (this.users[index].id == user.id){
        this.users[index] = user;
      }
    }
  }
}
