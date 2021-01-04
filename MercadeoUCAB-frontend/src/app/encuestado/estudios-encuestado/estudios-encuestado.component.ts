import { Component, OnInit, ViewChild } from '@angular/core';
import { EstudiosService } from '../../servicios/estudios.service';
import { Estudio } from '../../modelos/estudio';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-estudios-encuestado',
  templateUrl: './estudios-encuestado.component.html',
  styleUrls: ['./estudios-encuestado.component.css']
})
export class EstudiosEncuestadoComponent implements OnInit {
  
  constructor( 
    private estudiosService: EstudiosService,
    private dialog: MatDialog,
    private _router: Router
   ) { }
  
  id: number = 4;
  estudios: Estudio[];

  ngOnInit(): void {

      this.estudiosService.getEstudiosEncuestado(this.id)
      .subscribe(data => {this.estudios = data;
      } );
    };
  
  //  this.estudiosService.getEstudiosEncuestado(this.id).subscribe(estudios => console.log(estudios));

}
