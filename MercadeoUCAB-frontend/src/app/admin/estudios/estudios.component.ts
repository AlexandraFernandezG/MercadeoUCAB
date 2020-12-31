import { Component, OnInit, Input, Inject } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Categoria, Categoria2 } from 'src/app/modelos/categoria';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Estudio, Estudio2 } from 'src/app/modelos/estudio';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { AddEstudioComponent } from './add-estudio/add-estudio.component';
import { EditCategoriaComponent } from '../categoria/edit-categoria/edit-categoria.component';
import { EditEstudioComponent } from './edit-estudio/edit-estudio.component';


@Component({
  selector: 'app-estudio',
  templateUrl: './estudios.component.html',
  styleUrls: ['./estudios.component.css']
})
export class EstudiosComponent implements OnInit {

  estudios: Estudio[];

  constructor(
    private service: EstudiosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  estudioForm: FormGroup;

  ngOnInit(): void {
    this.service.getEstudios()
    .subscribe(data => {this.estudios = data;
    } );
  }

  openModal(){
    this.dialog.open(AddEstudioComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditEstudioComponent,
      {
        data: {id}
      }
    );
  }
/*
  deleteEstudio( estudio: Estudio): void{
    console.log('segundo', estudio);
    const editSu: Estudio2 = {
      id: estudio._id,
      nombre: estudio._nombre,
      descripcion: estudio._descripcion,
      estatus: 'Inactivo',
      categoriaDto: estudio._categoria._id
    };
    this.service.updateestudio(editSu).subscribe();
      }*/


}

