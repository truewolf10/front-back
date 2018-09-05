import {Component, OnInit} from '@angular/core';
import {HttpGenericService} from '../../services/http-generic.service';
import {REGISTER_URL, UNIT_URL} from '../../../end-points.const';
import {UserModel} from '../../models/user.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export  class RegisterComponent implements OnInit{

  user : UserModel = new UserModel();
  errorMessage: string;
  units : string[] = [];



  constructor(private httpService: HttpGenericService, private router: Router) {}


  ngOnInit(): void {
    this.httpService.getAll(UNIT_URL).subscribe(response => {
      this.units = response;
    });
    // var popica = Object.keys(BusinessUnit);
    // this.units = popica.slice(popica.length/2);
  }


  register() {
    const userAdded = {
      id: 0,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email,
      password: this.user.password,
      businessUnit: this.user.businessUnit
    };

    this.httpService.add(REGISTER_URL, userAdded).subscribe(data=>{
      this.router.navigate(['/login']);
    }, error1 => {
      this.errorMessage = "username already exist"
    });
  }

  selectCheckbox(unit : any){
    this.user.businessUnit = unit;
  }

}
