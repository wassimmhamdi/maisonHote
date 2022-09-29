import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {ModalModule} from 'ngx-bootstrap/modal';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './shared/layout/footer/footer.component';
import { HeaderComponent } from './shared/layout/header/header.component';
import { BannerComponent } from './shared/layout/banner/banner.component';
import { HomeComponent } from './shared/home/home.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { ReservationComponent } from './components/user/reservation/reservation.component';
import {GestionUsersComponent} from './components/admin/gestion-users/gestion-users.component'
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { SearchResultsComponent } from './shared/search-results/search-results.component';
import { HomeDetailComponent } from './shared/home-detail/home-detail.component';
import { ContactUsComponent } from './shared/contact-us/contact-us.component';
import { AboutUsComponent } from './shared/about-us/about-us.component';
import { GestionMaisonhotesComponent } from './components/admin/gestion-maisonhotes/gestion-maisonhotes.component';
import { GestionActivityComponent } from './components/admin/gestion-activity/gestion-activity.component';
import { DetailActivityComponent } from './components/user/detail-activity/detail-activity.component';
import { ResetPasswordComponent } from './components/user/reset-password/reset-password.component';
import { PasswordForgottenComponent } from './components/user/password-forgotten/password-forgotten.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { DetailUserComponent } from './components/admin/detail-user/detail-user.component';
import { CreateActivityComponent } from './components/user/create-activity/create-activity.component';
import { CreateHouseComponent } from './components/user/create-house/create-house.component';
import { DemandeActivityComponent } from './components/admin/demande-activity/demande-activity.component';
import { DemandeMaisonComponent } from './components/admin/demande-maison/demande-maison.component';
import { TinderInterfaceComponent } from './components/user/tinder-interface/tinder-interface.component';
import { PlanningComponent } from './components/user/planning/planning.component';
import { ListMaisonsComponent } from './components/admin/list-maisons/list-maisons.component';
import { SearchActivitiesComponent } from './shared/search-activities/search-activities.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    BannerComponent,
    HomeComponent,
    DashboardComponent,
    ReservationComponent,
    GestionUsersComponent,
    SearchResultsComponent,
    HomeDetailComponent,
    ContactUsComponent,
    AboutUsComponent,
    GestionMaisonhotesComponent,
    GestionActivityComponent,
    DetailActivityComponent,
    ResetPasswordComponent,
    PasswordForgottenComponent,
    ProfileComponent,
    DetailUserComponent,
    CreateActivityComponent,
    CreateHouseComponent,
    DemandeActivityComponent,
    DemandeMaisonComponent,
    TinderInterfaceComponent,
    PlanningComponent,
    ListMaisonsComponent,
    SearchActivitiesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ModalModule.forRoot(),
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
