import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Pregunta3 } from 'src/app/modelos/pregunta';
import { Solicitud } from 'src/app/modelos/solicitud';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { AddEstudioComponent } from '../estudios/add-estudio/add-estudio.component';

@Component({
  selector: 'app-solicitudes',
  templateUrl: './solicitudes.component.html',
  styleUrls: ['./solicitudes.component.css']
})
export class SolicitudesComponent implements OnInit {

  solicitudes: Solicitud[];
  preguntas: Pregunta3[] = [];
  id: number;
  constructor(
    private service: SolicitudEstudiosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog
  ) { }

  displayedColumns: string[] = ['descripcion', 'genero', 'edadMinima', 'edadMaxima',
     'estadoCivil', 'usuario',  'producto', 'acciones'];
  dataSource: MatTableDataSource<Solicitud>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.id= +this.actRoute.snapshot.paramMap.get("id");
    this.service.getSolicitudes()
    .subscribe(data => {
      console.log('ashdjad')
      this.dataSource = new MatTableDataSource<Solicitud>(data.reverse());
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );
  }

  createEstudio(solicitud: Solicitud):void{
    localStorage.setItem('solicitudId',  JSON.stringify (solicitud._id));
    localStorage.setItem('solicitudDes',  JSON.stringify (solicitud._descripcion));
    localStorage.setItem('solicitudMin',  JSON.stringify (solicitud._edadMinima));
    localStorage.setItem('solicitudMax',  JSON.stringify (solicitud._edadMaxima));
    localStorage.setItem('solicitudGen',  JSON.stringify (solicitud._genero));
    localStorage.setItem('solicitudProducto',  JSON.stringify (solicitud._producto._nombre));
    localStorage.setItem('solicitudEnLinea',  JSON.stringify (solicitud._disponibilidadEnLinea));
    localStorage.setItem('preguntasEst',  JSON.stringify (this.preguntas))
  }

}
