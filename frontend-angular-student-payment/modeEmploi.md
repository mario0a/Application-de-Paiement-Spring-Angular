# installation d'angular
ng new frontend-angular-student-pyment-app --directory=./ --no-standalone
### --directory=./   pour installer dans le dossier courant
### --no-standalone pour installer les modules

# installation de angular/material
ng add  @angular/material

# installation des guard pour controler l'acces des utilisateurs
ng g g guards/auth
// pour que le service puisse fonctionner il faut le declarer dans le app.modules.ts ensuite on protege la route dans app-routingModule.ts
