import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrl: './admin-template.component.css'
})
export class AdminTemplateComponent {
  constructor(public  authServices : AuthService) {
  }

  // creation de la fonction logout
  logout() {
    this.authServices.logout();

  }
}
