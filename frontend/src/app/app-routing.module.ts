import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {AdminComponent} from './components/admin/admin.component';
import {SharedComponent} from './components/shared/shared.component';
import {UserComponent} from './components/user/user.component';
import {AppGuard} from './guards/app.guard';
import {DashboardComponent} from './components/dashboard/dashboard.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AppGuard] },
  { path: 'shared', component: SharedComponent, canActivate: [AppGuard] },
  { path: 'user', component: UserComponent, canActivate: [AppGuard] },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AppGuard] },
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
