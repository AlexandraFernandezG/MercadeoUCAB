import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Estudio } from '../../modelos/estudio';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { Solicitud } from 'src/app/modelos/solicitud';
import { EditarSolicitudComponent } from '../editar-solicitud/editar-solicitud.component';
import { MatSort } from '@angular/material/sort';
import { EstudiosService } from 'src/app/servicios/estudios.service';

@Component({
  selector: 'app-estudios-analista',
  templateUrl: './estudios-analista.component.html',
  styleUrls: ['./estudios-analista.component.css']
})
export class EstudiosAnalistaComponent implements OnInit {

  estudios:any[];

  constructor(
    private service: EstudiosService,
    ) { }

  ngOnInit(): void {
    let id = 20;
    this.service.getEstudiosCliente(id).subscribe(
      estudiosData => { this.estudios = estudiosData ,
        console.log(this.estudios)},
      );
    
  }

  edit(id){
   
  }

  result(id){
    
  }

  deleted(id){
  
  }
}
