import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Estudio } from 'src/app/modelos/estudio';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { EstudiosComponent } from '../estudios/estudios.component';

@Component({
  selector: 'app-estudios-sugeridos',
  templateUrl: './estudios-sugeridos.component.html',
  styleUrls: ['./estudios-sugeridos.component.css']
})
export class EstudiosSugeridosComponent implements OnInit {

  estudios: Estudio[];
  constructor(
    private service: EstudiosService,
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

  //  this.service.getEstudiosSugeridos()
  //  .subscribe(data => {this.estudios = data;
  //  } );
    
  }


}
