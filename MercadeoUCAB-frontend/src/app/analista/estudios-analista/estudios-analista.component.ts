import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { Solicitud } from 'src/app/modelos/solicitud';

@Component({
  selector: 'app-estudios-analista',
  templateUrl: './estudios-analista.component.html',
  styleUrls: ['./estudios-analista.component.css']
})
export class EstudiosAnalistaComponent implements OnInit {


  constructor( 
    private solicitudService: SolicitudEstudiosService, 
    private _router: Router
    ) { }

  solicitudes: Solicitud[];  
  displayedColumns: string[] = ['id', 'descripcion', 'acciones'];
  dataSource: MatTableDataSource<Solicitud>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  ngOnInit(): void {

    this.solicitudService.getSolicitudes().subscribe(
      solicitudes => { 
        this.dataSource = new MatTableDataSource<Solicitud>(solicitudes);
        this.dataSource.paginator = this.paginator;
      });
    

    this.solicitudService.getSolicitudes().subscribe(solicitudes => console.log(solicitudes));
  }
  crearEstudio(): void {
    this._router.navigate(['analista/crearEncuesta']);
  }

}
