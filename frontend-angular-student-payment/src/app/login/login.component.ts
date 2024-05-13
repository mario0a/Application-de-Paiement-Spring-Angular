import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent  implements OnInit{
  // declaration du formulaire
  public loginForm! : FormGroup;
  constructor(private fb : FormBuilder ,private authService : AuthService,
              private router : Router) { // on injecte le service dans le controleur

  }
  ngOnInit():void {
    this.loginForm = this
      .fb.group({
      username : this.fb.control(''),
      password : this.fb.control('')
    });


  }// fonction login pour gerer le clck du bouton
  login() {
    // on recupere les valeur de formulaire
    let username = this.loginForm.value.username;
    let password = this.loginForm.value.password;
    // on appele la methode du service
    let auth : boolean = this.authService.login(username,password);
    // on teste
    if(auth==true){
      this.router.navigateByUrl("/admin")

    }


  }

  // pour une authentification on creer un service auth qui est dans services
}
