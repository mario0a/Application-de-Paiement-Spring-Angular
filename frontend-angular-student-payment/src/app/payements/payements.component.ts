import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-payements',
  templateUrl: './payements.component.html',
  styleUrl: './payements.component.css'

})

// implementation du back end
export class PayementsComponent  implements OnInit{
// implementation du back end

  //on recupere les donnee qu'on va stoker quans une litse de paiement
  public payments : any;
  //on declarer un data sources pour le html
  public dataSource : any;
  // la liste des colonnes que lon souhaite afficher
  public displayedColumns = ['id','date','montant','type','status','firstName'];

  //configuration de la pgination

  @ViewChild(MatPaginator) paginator! :MatPaginator;
  //pour faire les tri avec sort
  @ViewChild(MatSort) sort! :MatSort;
  constructor(private http: HttpClient) {
  }


  ngOnInit() {
    this.http.get("http://localhost:8021/payments")
      .subscribe({
        next : data =>{// si tout se passe bien data ou value
          this.payments = data;
          // on initialiser le data source ici
          this.dataSource =  new MatTableDataSource(this.payments)
          //pagination
          this.dataSource.paginator = this.paginator;
          // faire le tri
          this.dataSource.sort = this.sort;

        },
          error : err =>{
            console.log(err)

          }

      }

      )

  }

}
