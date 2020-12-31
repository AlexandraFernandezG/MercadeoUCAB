import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { Solicitud } from 'src/app/modelos/solicitud';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';

@Component({
  selector: 'app-analista-poblacion',
  templateUrl: './analista-poblacion.component.html',
  styleUrls: ['./analista-poblacion.component.css']
})
export class AnalistaPoblacionComponent implements OnInit {

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

}
