import { Injectable } from '@angular/core';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // creation des utilisateurs et des role pour les tests
  public users:any = {
    admin : { password : '123', roles : ['ETUDIANT','ADMIN']},
    user1 : { password : '123', roles : ['ETUDIANT']}
  }
  public username : any;
  public isAuthentificated : boolean=false;
  public roles : string[] =[];

  constructor( private router : Router) { }

  // methode pour gerer la connexion avec le formulaire d'authentification
  public login(username : string, password : string) : boolean{
    if(this.users[username] && this.users[username]['password']==password){
      this.username =username;
      this.isAuthentificated = true;
      this.roles = this.users[username]['roles'];
      return true;
    }else{
      return true;
    }

  }
// creation de la methode logout
  logout() {
    this.isAuthentificated = false;
    this.roles =[];
    this.username = undefined;
    this.router.navigateByUrl("/login")

  }
}
