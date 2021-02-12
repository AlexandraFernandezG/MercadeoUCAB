import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { EstudiosService } from '../../servicios/estudios.service';
import { Estudio } from '../../modelos/estudio';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { RegistroEncuestado } from 'src/app/modelos/registro-encuestado';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { identifierModuleUrl } from '@angular/compiler';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-estudios-encuestado',
  templateUrl: './estudios-encuestado.component.html',
  styleUrls: ['./estudios-encuestado.component.css']
})
export class EstudiosEncuestadoComponent implements OnInit {
  
  constructor( 
    private estudiosService: EstudiosService,
    private usuariosService: UsuariosService,
    private dialog: MatDialog,
    private _router: Router,
    
   ) { }
  
  id: number;
  otro: number;
  estudios:any[];
  infoEncuestado: RegistroEncuestado;
  displayedColumns: string[] = ['nombre', 'fechaInicio', 'fechaFin','estatus', 'acciones'];
  dataSource: MatTableDataSource<Estudio>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    let id = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudiosService.getEstudiosEncuestado(id)
      .subscribe(data => { this.estudios = data.Estudios.reverse()
        console.log(data.Estudios)
        
      console.log(this.estudios);
    });
      // console.log(this.infoEncuestado);
      // this.estudiosService.getEstudios()
      // .subscribe(data => {this.estudios = data;
      // } );
      // console.log(this.estudios);
      this.id = JSON.parse(localStorage.getItem('usuarioID')),
      console.log(this.id);
  };

  logId(){
    this.id = JSON.parse(localStorage.getItem('usuarioID'));
    return this.id
  }
  
  //  this.estudiosService.getEstudiosEncuestado(this.id).subscribe(estudios => console.log(estudios));

}
