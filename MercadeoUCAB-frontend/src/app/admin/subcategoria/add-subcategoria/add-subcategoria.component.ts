import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Subcategoria2 } from 'src/app/modelos/subcategoria';
import { SubcategoriaComponent } from 'src/app/admin/subcategoria/subcategoria.component';
import { Categoria, Categoria2 } from 'src/app/modelos/categoria';
import { CategoriasService } from 'src/app/servicios/categorias.service';

@Component({
  selector: 'app-add-subcategoria',
  templateUrl: './add-subcategoria.component.html',
  styleUrls: ['./add-subcategoria.component.css']
})
export class AddSubcategoriaComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: SubcategoriasService,
    private serviceCategoria: CategoriasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<SubcategoriaComponent>
    ) {

    this.subcategoriaForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]), 
      categoriaDto: new FormControl('',[Validators.required])
    });
   }
    categorias: Categoria[] =[
    ]
    subcategorias: any;
    subcategoria: Subcategoria ={
      _id: 0,
      _nombre: '',
      _estatus: '',
      _descripcion: '',
      _categoria: {
        _id: 0,
        _nombre: '',
        _estatus: '',
        _descripcion: ''
      }
    };

  subcategoriaForm: FormGroup;
  ngOnInit(): void {

    this.service.getSubcategorias()
    .subscribe(data => {this.subcategorias = data;
    } );
    this.serviceCategoria.getCategorias()
    .subscribe(catego => {this.categorias = catego;
    } );
  }

  addSubcategoria(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createSubcategoria({
     id,
    nombre,
    descripcion,
    estatus,
    categoriaDto: this.subcategoriaForm.get("categoriaDto").value
    } as Subcategoria2).subscribe();
    console.log(Subcategoria2);
    this.dialogRef.close();
  }
}

