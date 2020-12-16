import { Component, OnInit, Input, Inject } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Categoria, Categoria2 } from 'src/app/modelos/categoria';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Subcategoria, Subcategoria2 } from 'src/app/modelos/subcategoria';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { AddSubcategoriaComponent } from './add-subcategoria/add-subcategoria.component';
import { EditCategoriaComponent } from '../categoria/edit-categoria/edit-categoria.component';
import { EditSubcategoriaComponent } from './edit-subcategoria/edit-subcategoria.component';


@Component({
  selector: 'app-subcategoria',
  templateUrl: './subcategoria.component.html',
  styleUrls: ['./subcategoria.component.css']
})
export class SubcategoriaComponent implements OnInit {

  subcategorias: Subcategoria[];
  categorias: Categoria[];

  constructor(
    private service: SubcategoriasService,
    private serviceCategoria: CategoriasService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  subcategoriaForm: FormGroup;

  ngOnInit(): void {
    this.service.getSubcategorias()
    .subscribe(data => {this.subcategorias = data;
    } );
    this.serviceCategoria.getCategorias()
    .subscribe(catego => {this.categorias = catego;
    } );
  }

  openModal(){
    this.dialog.open(AddSubcategoriaComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditSubcategoriaComponent,
      {
        data: {id: id}
      }
    );
  }

  deleteSubcategoria( subcategoria: Categoria): void{
    console.log('segundo', subcategoria);
   /* const deleteSubca: Subcategoria2 = {
      id: subcategoria._id,
      nombre: subcategoria._nombre,
      descripcion: subcategoria._descripcion,
      estatus: 'Inactivo',
    };
    this.service.updateSubcategoria(deleteSubca).subscribe();
     */
    }

}
