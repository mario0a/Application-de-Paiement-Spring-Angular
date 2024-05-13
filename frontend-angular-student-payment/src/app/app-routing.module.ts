import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {EtudiantsComponent} from "./etudiants/etudiants.component";
import {PayementsComponent} from "./payements/payements.component";
import {ListEtudiantsComponent} from "./list-etudiants/list-etudiants.component";
import {ListPaiementsComponent} from "./list-paiements/list-paiements.component";
import {LoginComponent} from "./login/login.component";
import {ProfileComponent} from "./profile/profile.component";
import {AdminTemplateComponent} from "./admin-template/admin-template.component";
import {AuthGuard} from "./guards/auth.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";

const routes: Routes = [
  // ici on met la route qu'on souhaite afficher en haut'
  {path : "", component : LoginComponent},
  {path : "login", component : LoginComponent},
  {path : "admin", component : AdminTemplateComponent ,
    // ensuite on protege la route dans app-routingModule.ts
    canActivate : [AuthGuard],
    children : [
      {path : "home", component : HomeComponent},
      {path : "profile", component : ProfileComponent},
      {path : "dashboard", component : DashboardComponent},
      {path : "etudiants", component : EtudiantsComponent},
      {path : "paiements", component : PayementsComponent},
      {
        path : "listEtudiants", component : ListEtudiantsComponent,
        canActivate : [AuthorizationGuard], data : { roles : ['ADMIN']}
      },
      {path : "listPaiements", component : ListPaiementsComponent},
    ]},


];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
