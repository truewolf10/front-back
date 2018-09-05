
import {HomeComponent} from './pages/home/home.component';
import {Routes} from '@angular/router';
import {DocumentationComponent} from './pages/documentation/documentation.component';
import {SecondPageComponent} from './pages/second-page/second-page.component';
import {Page404Component} from './pages/page404/page404.component';
import {ProjectComponent} from './pages/project/project.component';
import {PhaseComponent} from './pages/phase/phase.component';
import {PhaseTemplateComponent} from './pages/phase-template/phase-template.component';
import {RegisterComponent} from './components/register/register.component';
import {UsersComponent} from './pages/users/users.component';
import {LoginComponent} from './components/login/login.component';
import {UrlPermission} from './services/url.permission';
import {ProjectDetailsComponent} from './pages/project-details/project-details.component';
import {ForgotPasswordComponent} from './components/forgot-password/forgot-password.component';
import {ChangePasswordComponent} from './components/change-password/change-password.component';
import {ChangePassPermission} from './services/changePass.permission';

export const appRoutes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'second-page', component: SecondPageComponent, canActivate : [UrlPermission] },
  { path: 'phase', component: PhaseComponent, canActivate : [UrlPermission] },
  { path: 'documentation', component: DocumentationComponent, canActivate : [UrlPermission] },
  { path: 'project', component: ProjectComponent, canActivate : [UrlPermission]},
  { path: 'phase-template', component: PhaseTemplateComponent, canActivate : [UrlPermission] },
  { path: 'users', component: UsersComponent, canActivate : [UrlPermission] },
  { path: 'register', component: RegisterComponent},
  { path: 'project/:id', component: ProjectDetailsComponent, canActivate : [UrlPermission] },
  { path: 'login', component:LoginComponent},
  { path : 'forgot-password', component: ForgotPasswordComponent},
  { path : 'change-password', component: ChangePasswordComponent},
  { path: '**', redirectTo : '/login' }
];
