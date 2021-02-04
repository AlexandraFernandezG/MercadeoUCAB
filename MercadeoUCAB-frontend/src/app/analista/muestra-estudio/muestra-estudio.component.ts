import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Estudio, Estudio2 } from 'src/app/modelos/estudio';
import { Usuario3 } from 'src/app/modelos/usuario';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { EstudiosAnalistaComponent } from '../estudios-analista/estudios-analista.component';

@Component({
  selector: 'app-muestra-estudio',
  templateUrl: './muestra-estudio.component.html',
  styleUrls: ['./muestra-estudio.component.css']
})
export class MuestraEstudioComponent implements OnInit {

  encuestados: Usuario3[];
  displayedColumns: string[] = ['nombre', 'correo', 'acciones'];
  dataSource: MatTableDataSource<Usuario3>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private encuestadosService: UsuariosService,
    @Inject(MAT_DIALOG_DATA) public data: Estudio2,
    public dialogRef: MatDialogRef<EstudiosAnalistaComponent>,
  ) { }

  ngOnInit(): void {
    this.dialogRef.updateSize('650px', '500px');
    this.encuestadosService.getEncuestadosEstudio(this.data.id).subscribe( encuestadosData =>
      {
        this.dataSource = new MatTableDataSource<Usuario3>(encuestadosData);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      } );
    console.log(this.encuestados);
  }

  // tslint:disable-next-line: typedef
  closeModal(){
    this.dialogRef.close();
  }

  // tslint:disable-next-line: typedef
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}
