import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpGenericService} from '../../services/http-generic.service';
import {BASE_URL, FORGETPASS_URL, USER_URL} from '../../../end-points.const';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  errorMessage : string;
  email : string;
  constructor(private httpGenericService : HttpGenericService, private router : Router) {
  }

  ngOnInit() {
  }

  forgetPass() {
    this.httpGenericService.add(USER_URL + FORGETPASS_URL, this.email)
      .subscribe(data=>{
        this.router.navigate(['/login'])
      }, error1 => {
        console.log(error1);
        if (error1["status"] === 200) {
          this.router.navigate(['/login']);
        }
        else{
          this.errorMessage = "error : This email has never been registered";
        }
      });
    return false;
    //TODO : curious if this return is needed;
  }
}
