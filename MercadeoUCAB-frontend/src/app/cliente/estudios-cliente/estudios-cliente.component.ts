import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EstudiosService } from '../../servicios/estudios.service';
import { Estudio } from '../../modelos/estudio';
import { SolicitudEstudioComponent } from '../solicitud-estudio/solicitud-estudio.component';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Router } from '@angular/router';
import { MatSort } from '@angular/material/sort';

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
  @ViewChild(MatSort) sort: MatSort;
  
  ngOnInit(): void {
    let id = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudiosService.getEstudiosCliente(id).subscribe(
      estudios => { 
        console.log(estudios)
        this.dataSource = new MatTableDataSource<Estudio>(estudios);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
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