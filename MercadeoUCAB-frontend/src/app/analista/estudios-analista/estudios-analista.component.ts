import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Estudio, Estudio2 } from '../../modelos/estudio';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { Solicitud } from 'src/app/modelos/solicitud';
import { EditarSolicitudComponent } from '../editar-solicitud/editar-solicitud.component';
import { MatSort } from '@angular/material/sort';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { MuestraEstudioComponent } from '../muestra-estudio/muestra-estudio.component';

@Component({
  selector: 'app-estudios-analista',
  templateUrl: './estudios-analista.component.html',
  styleUrls: ['./estudios-analista.component.css']
})
export class EstudiosAnalistaComponent implements OnInit {

  estudios:any[];
  editEst: Estudio2;

  constructor(
    private service: EstudiosService,
    public dialog: MatDialog,
    ) { }

  ngOnInit(): void {
    let id = JSON.parse(localStorage.getItem('usuarioID'));
    this.service.getEstudiosAnalista(id).subscribe(
      estudiosData => { this.estudios = estudiosData ,
        console.log(this.estudios)},
      );
    
  }

  openModal( id: number, nombre: string): void{
    this.dialog.open(MuestraEstudioComponent,
      {
        data: {
          id,
          nombre
        }
      }
    );
  }

  iniciar(estudio){
    console.log(estudio);
    this.editEst ={
      id: estudio.idEstudio,
      nombre: estudio.nombreEstudio,
      tipoInstrumento: 'encuesta',
      fechaInicio: estudio.fechaInicioEstudio,
      fechaFin: estudio.fechaFinEstudio,
      estatus: estudio.estatusEstudio,
      estado: "en proceso",
      usuarioDto: JSON.parse(localStorage.getItem('usuarioID')),
      solicitudEstudioDto: 4,
    }
    console.log(this.editEst)
    this.service.updateEstudio(this.editEst).subscribe();
  }

  cerrar(estudio){
    console.log(estudio);
    this.editEst ={
      id: estudio.idEstudio,
      nombre: estudio.nombreEstudio,
      tipoInstrumento: 'encuesta',
      fechaInicio: estudio.fechaInicioEstudio,
      fechaFin: estudio.fechaFinEstudio,
      estatus: estudio.estatusEstudio,
      estado: "en proceso",
      usuarioDto: JSON.parse(localStorage.getItem('usuarioID')),
      solicitudEstudioDto: 4,
    }
    console.log(this.editEst)
    //this.service.updateEstudio(estudio).subscribe();
  }
}
