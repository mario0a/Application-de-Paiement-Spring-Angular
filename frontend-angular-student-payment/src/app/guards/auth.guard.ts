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

// ici on va utiliser les synthaxe classe au lieu de celle par defaut

// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };

// on peut ne pas implementer implements CanActivate ca ne change rien mais ce guards doit etre un service

@Injectable()
export class  AuthGuard {

  constructor(private authService : AuthService,private router : Router) {
  }

  // on redefini la methode canActivate qui retourne soit true ou false
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {

    if(this.authService.isAuthentificated){
      return  true;
    }else{

    }

    return this.router.navigateByUrl("/login");
  }

  // pour que le service puisse fonctionner il faut le declarer dans le app.modules.ts



}
