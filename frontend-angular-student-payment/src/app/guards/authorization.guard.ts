import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
// ici on gere l'autorisation  et droit sur les autre utilisateurs , on changes des choses dans les routes

// ici on va utiliser les synthaxe classe au lieu de celle par defaut

// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };

// on peut ne pas implementer implements CanActivate ca ne change rien mais ce guards doit etre un service

@Injectable()
export class  AuthorizationGuard {

  constructor(private authService : AuthService,private router : Router) {
  }

  // on redefini la methode canActivate qui retourne soit true ou false
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {

    if(this.authService.isAuthentificated){
      // ici on recupere les roles apartir de des routes
      let requiredRoles = route.data['roles'];
      // on recupere les roles de l'utilisateur authentifier
      let userRoles = this.authService.roles;
      // on parcourt les roles pour voir qui a le droit
      for (let role of userRoles){
        if(requiredRoles.includes(role)){
          return  true;

        }
      }
      return  false;
    }else{
       this.router.navigateByUrl("/login");
       return false;

    }


  }

  // pour que le service puisse fonctionner il faut le declarer dans le app.modules.ts



}
