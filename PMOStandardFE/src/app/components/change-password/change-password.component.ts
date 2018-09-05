import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PasswordValidation} from './password-validation';
import {ActivatedRoute, ParamMap, Params, Router} from '@angular/router';
import {ACTIVATE_URL, CHANGEPASS_URL, USER_URL} from '../../../end-points.const';
import {HttpParams} from '@angular/common/http';
import {ChangePasswordService} from '../../services/change-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  showForm = false;
  password : string;
  form : FormGroup;

  constructor(fb : FormBuilder, private route : ActivatedRoute, private httpChangePassService : ChangePasswordService, private router : Router) {
    this.form = fb.group({
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    }, {
      validator: PasswordValidation.MatchPassword // your validation method
    })
  }

  ngOnInit() {

    this.route.queryParams.subscribe((queryParams: Params) => {
          let id = queryParams["id"];
          let uuid = queryParams["uuid"];

          let params = new HttpParams();
          params = params.append("id", id);
          params = params.append("uuid", uuid);

          this.httpChangePassService.getActivation(USER_URL + ACTIVATE_URL, params).subscribe((response)=>{
            if (JSON.parse(localStorage.getItem('activation'))['activated'] !== true){
              localStorage.removeItem('activation');
              this.router.navigate(['/login']);
            }else{
              this.showForm = true //daca e bun uid-ul;

            }
          },error1 => {
            this.router.navigate(['/login']);
            console.log(error1);
          })
    });
  }

  onSubmit(password : any) {
    let userId = JSON.parse(localStorage.getItem('activation'))['user']['id'];
    // console.log(userId + " " + password);
    const changePassDto = {
      userId : userId,
      password : password
    };
    this.httpChangePassService.addNewPassword(USER_URL + CHANGEPASS_URL, changePassDto).subscribe(data=> {
      this.router.navigate(['/login'])
    }, error1 => {
    //
    });
    localStorage.removeItem('activation');
    this.router.navigate(['/login']);
  }

}
