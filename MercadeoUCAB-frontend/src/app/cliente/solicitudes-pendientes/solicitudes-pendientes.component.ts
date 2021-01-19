import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AddEstudioComponent } from 'src/app/admin/estudios/add-estudio/add-estudio.component';
import { Solicitud } from 'src/app/modelos/solicitud';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';

@Component({
  selector: 'app-solicitudes-pendientes',
  templateUrl: './solicitudes-pendientes.component.html',
  styleUrls: ['./solicitudes-pendientes.component.css']
})
export class SolicitudesPendientesComponent implements OnInit {

  solicitudes: Solicitud[];

  constructor(
    private service: SolicitudEstudiosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private _router: Router
  ) { }

  displayedColumns: string[] = ['descripcion', 'genero', 'edadMinima', 'edadMaxima',
     'estadoCivil', 'producto', 'acciones'];
  dataSource: MatTableDataSource<Solicitud>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.service.getSolicitudes()
    .subscribe(data => {
      let id = JSON.parse(localStorage.getItem('usuarioID'));
      this.dataSource = new MatTableDataSource<Solicitud>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );
  }
  solicitarEstudio(): void {
    this._router.navigate(['/cliente/solicitar_estudio']);
  }
  openModal(id: number):void{
    this.dialog.open(AddEstudioComponent,
      {
        data: {id: id}
      }
      );
  }

}
