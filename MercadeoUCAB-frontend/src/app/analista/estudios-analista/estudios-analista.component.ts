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

@Component({
  selector: 'app-estudios-analista',
  templateUrl: './estudios-analista.component.html',
  styleUrls: ['./estudios-analista.component.css']
})
export class EstudiosAnalistaComponent implements OnInit {


  constructor( 
    private solicitudService: SolicitudEstudiosService, 
    private _router: Router,
    private matDialog: MatDialog,
    ) { }

  solicitudes: Solicitud[];  
  displayedColumns: string[] = ['id', 'descripcion', 'acciones'];
  dataSource: MatTableDataSource<Solicitud>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ngOnInit(): void {

    this.solicitudService.getSolicitudes().subscribe(
      solicitudes => { 
        this.dataSource = new MatTableDataSource<Solicitud>(solicitudes);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });
    

    this.solicitudService.getSolicitudes().subscribe(solicitudes => console.log(solicitudes));
  }
  crearEstudio(): void {
    this._router.navigate(['analista/crearEncuesta']);
  }

  openModal( id: number): void{
    this.matDialog.open(EditarSolicitudComponent,
      {
        data: {id: id}
      }
    );
  }
}
