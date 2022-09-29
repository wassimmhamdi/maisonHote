import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { GestionUsersComponent } from './components/admin/gestion-users/gestion-users.component';
import { AboutUsComponent } from './shared/about-us/about-us.component';
import { ContactUsComponent } from './shared/contact-us/contact-us.component';
import { HomeDetailComponent } from './shared/home-detail/home-detail.component';
import { HomeComponent } from './shared/home/home.component';
import { SearchResultsComponent } from './shared/search-results/search-results.component';
import {GestionMaisonhotesComponent} from './components/admin/gestion-maisonhotes/gestion-maisonhotes.component'
import { GestionActivityComponent } from './components/admin/gestion-activity/gestion-activity.component';
import { DetailActivityComponent } from './components/user/detail-activity/detail-activity.component';
import { ResetPasswordComponent } from './components/user/reset-password/reset-password.component';
import { PasswordForgottenComponent } from './components/user/password-forgotten/password-forgotten.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { DetailUserComponent } from './components/admin/detail-user/detail-user.component';
import { CreateActivityComponent } from './components/user/create-activity/create-activity.component';
import { CreateHouseComponent } from './components/user/create-house/create-house.component';
import { DemandeMaisonComponent } from './components/admin/demande-maison/demande-maison.component';
import { DemandeActivityComponent } from './components/admin/demande-activity/demande-activity.component';
import { TinderInterfaceComponent } from './components/user/tinder-interface/tinder-interface.component';
import { PlanningComponent } from './components/user/planning/planning.component';
import { ListMaisonsComponent } from './components/admin/list-maisons/list-maisons.component';
import { SearchActivitiesComponent } from './shared/search-activities/search-activities.component';
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'users',
    component: GestionUsersComponent,
  },
  {
    path: 'detailUser/:idUser',
    component: DetailUserComponent,
  },
  {
    path: 'profile',
    component: ProfileComponent,
  },
  {
    path: 'gestionMaison',
    component: GestionMaisonhotesComponent,
  },
  {
    path: 'demandeHouse',
    component: DemandeMaisonComponent,
  },
  {
    path: 'demandeActivity',
    component: DemandeActivityComponent,
  },
  {
    path: 'activities',
    component: GestionActivityComponent,
  },
  {
    path: 'createActivity',
    component: CreateActivityComponent,
  },
  {
    path: 'createHouse',
    component: CreateHouseComponent,
  },
  {
    path: 'listMaisons',
    component: SearchResultsComponent,
  },
  {
    path: 'listActivities',
    component: SearchActivitiesComponent,
  },
  {
    path: 'mesMaisons',
    component: ListMaisonsComponent,
  },
  {
    path: 'home_detail/:idHouse',
    component: HomeDetailComponent,
  },
  {
    path: 'detailActivity/:idAct',
    component: DetailActivityComponent,
  },
  {
    path: 'contact',
    component: ContactUsComponent,
  },
  {
    path: 'about',
    component: AboutUsComponent,
  },
   {
    path: 'passwordForotten',
    component: PasswordForgottenComponent,
  },
  {
    path: 'passwordReset',
    component: ResetPasswordComponent,
  },
  {
    path: 'tinder',
    component: TinderInterfaceComponent,
  },
  {
    path: 'planning',
    component: PlanningComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
