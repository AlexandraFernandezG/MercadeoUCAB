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
  estudios: Estudio[];
  infoEncuestado: RegistroEncuestado;

  ngOnInit(): void {
    let id = JSON.parse(localStorage.getItem('usuarioID'));
      this.usuariosService.getInfoUsuario(id)
        .subscribe(data => {this.infoEncuestado = data;
          console.log(this.infoEncuestado);
          this.estudiosService.getEstudiosEncuestado(this.infoEncuestado._id)
          .subscribe(data => {this.estudios = data;
            console.log(this.estudios);
        }); 
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
