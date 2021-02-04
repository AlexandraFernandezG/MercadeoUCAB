import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Estudio, Estudio2 } from 'src/app/modelos/estudio';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { EstudiosComponent } from '../estudios/estudios.component';
import { PreguntasEstudioComponent } from '../estudios/preguntas-estudio/preguntas-estudio.component';

@Component({
  selector: 'app-estudios-sugeridos',
  templateUrl: './estudios-sugeridos.component.html',
  styleUrls: ['./estudios-sugeridos.component.css']
})
export class EstudiosSugeridosComponent implements OnInit {

  estudios: Estudio[];
  constructor(
    private service: EstudiosService,
    public dialogRef: MatDialogRef<PreguntasEstudioComponent>,
    @Inject(MAT_DIALOG_DATA) public est: Estudio2,
  ) { }

  displayedColumns: string[] = ['nombre', 'fechaInicio', 'fechaFin', 'usuario',
    'estatus', 'acciones'];
  dataSource: MatTableDataSource<Estudio>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.service.getEstudios()
    .subscribe(data => {
      this.dataSource = new MatTableDataSource<Estudio>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );

  //  this.service.getEstudiosSugeridos(this.est.id)
  //  .subscribe(data => {
  //   this.dataSource = new MatTableDataSource<Estudio>(data);
  //   this.dataSource.paginator = this.paginator;
  //   this.dataSource.sort = this.sort;
  //  } );
  }

  // tslint:disable-next-line: typedef
  cloneEstudio(idSugerido: number){
    this.service.addEstudioSugerido(
      idSugerido,
      this.est.id
    ).subscribe();
    this.dialogRef.close();
  }

  // tslint:disable-next-line: typedef
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}
