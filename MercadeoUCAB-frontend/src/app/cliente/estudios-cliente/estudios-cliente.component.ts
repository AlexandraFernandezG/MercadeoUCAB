import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EstudiosService } from '../../servicios/estudios.service';
import { Estudio } from '../../modelos/estudio';
import { SolicitudEstudioComponent } from '../solicitud-estudio/solicitud-estudio.component';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-estudios-cliente',
  templateUrl: './estudios-cliente.component.html',
  styleUrls: ['./estudios-cliente.component.css']
})
export class EstudiosClienteComponent implements OnInit {

  constructor( 
    private estudiosService: EstudiosService, 
    private dialog: MatDialog,
    private _router: Router
    ) { }

  estudios: Estudio[];  
  displayedColumns: string[] = ['_id', 'nombre', '_fechaInicio','_fechaFin', '_estatus', 'acciones'];
  dataSource: MatTableDataSource<Estudio>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  ngOnInit(): void {

    this.estudiosService.getEstudios().subscribe(
      estudios => { 
        this.dataSource = new MatTableDataSource<Estudio>(estudios);
        this.dataSource.paginator = this.paginator;
      });
    

    this.estudiosService.getEstudios().subscribe(estudios => console.log(estudios));
  }
  solicitarEstudio(): void {
    this._router.navigate(['/cliente/solicitar_estudio']);
  }
  onCreate(){
    this.dialog.open(SolicitudEstudioComponent);
  }
  crearProducto(){};
}