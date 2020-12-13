import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Estudio } from '../../modelos/estudio';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';

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

  estudios: Estudio[];  
  displayedColumns: string[] = ['id', 'title', 'acciones'];
  dataSource: MatTableDataSource<Estudio>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  ngOnInit(): void {

    this.solicitudService.getEstudios().subscribe(
      estudios => { 
        this.dataSource = new MatTableDataSource<Estudio>(estudios);
        this.dataSource.paginator = this.paginator;
      });
    

    this.solicitudService.getEstudios().subscribe(estudios => console.log(estudios));
  }
  crearEstudio(): void {
    this._router.navigate(['analista/crearEncuesta']);
  }

}
