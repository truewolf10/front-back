import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { SecondPageComponent } from './pages/second-page/second-page.component';
import { DocumentationComponent } from './pages/documentation/documentation.component';
import { appRoutes } from './app.route';
import { RouterModule } from '@angular/router';
import { Page404Component } from './pages/page404/page404.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {ProjectService} from './services/project.service';
import { ProjectComponent } from './pages/project/project.component';
import { PhaseComponent } from './pages/phase/phase.component';
import {ProjectPhaseService} from './services/project-phase.service';

import {PhaseTemplateComponent} from './pages/phase-template/phase-template.component';
import {HttpClientModule} from '@angular/common/http';
import {PhaseTemplateService} from './services/phase-template.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatCheckboxModule, MatDialogModule, MatRadioModule} from '@angular/material';
import { ModalModule } from 'ngx-bootstrap';
import {HttpGenericService} from './services/http-generic.service';
import {RegisterComponent} from './components/register/register.component';
import {UsersComponent} from './pages/users/users.component';
import {StorageServiceModule} from  'angular-webstorage-service';
import {ProjectDetailsComponent} from './pages/project-details/project-details.component';
import {FileUploadModule} from 'ng2-file-upload';
import * as FileSaver from 'file-saver';
import { LoginComponent } from './components/login/login.component';
import {UrlPermission} from './services/url.permission';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import {ChangePassPermission} from './services/changePass.permission';
import {ChangePasswordService} from './services/change-password.service';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    SecondPageComponent,
    DocumentationComponent,
    Page404Component,
    ProjectComponent,
    PhaseComponent,
    PhaseTemplateComponent,
    RegisterComponent,
    UsersComponent,
    LoginComponent,
    UsersComponent,
    ProjectDetailsComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ModalModule.forRoot(),
    NgbModule.forRoot(),
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false }
    ),
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    MatCheckboxModule,
    StorageServiceModule,
    MatRadioModule,
    FileUploadModule,
    ReactiveFormsModule
  ],
  providers: [ProjectService,
    ProjectPhaseService,
    PhaseTemplateService,
    HttpGenericService,
    UrlPermission,
    ChangePassPermission
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
