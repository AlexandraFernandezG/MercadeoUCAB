import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { SubcategoriaComponent } from 'src/app/admin/subcategoria/subcategoria.component';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Subcategoria2 } from 'src/app/modelos/subcategoria';
import { Categoria } from 'src/app/modelos/categoria';
import { CategoriasService } from 'src/app/servicios/categorias.service';

@Component({
  selector: 'app-edit-subcategoria',
  templateUrl: './edit-subcategoria.component.html',
  styleUrls: ['./edit-subcategoria.component.css']
})
export class EditSubcategoriaComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: SubcategoriasService,
    private serviceCategoria: CategoriasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<SubcategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Subcategoria2,
    ) {

      this.subcategoriaForm = this.fb.group({
        nombre: new FormControl([ Validators.maxLength(100)]),
        descripcion: new FormControl([ Validators.maxLength(150)]),
        categoriaDto: new FormControl('',[Validators.required])
      });
    }
    categorias: Categoria[] = [
    ];
    subcategorias: any;
    subcategoria: Subcategoria = {
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
    this.getId();
    this.service.getSubcategorias()
    .subscribe(data => {this.subcategorias = data;
    } );
    this.serviceCategoria.getCategorias()
    .subscribe(catego => {this.categorias = catego;
    } );
      }

  getId(){
    console.log('primero', this.subcategoria);
    const id = this.data.id;
    this.service.getSubcategoria(id).subscribe(data => {this.subcategoria = data;
    });
  }


  editSubcategoria(): void{
   console.log('segundo');
   const editSu: Subcategoria2 = {
      id: this.data.id,
      nombre: this.subcategoriaForm.get("nombre").value,
      descripcion: this.subcategoriaForm.get("descripcion").value,
      estatus: 'Activo',
      categoriaDto: this.subcategoriaForm.get("categoriaDto").value
    };
   this.service.updateSubcategoria(editSu).subscribe();

   this.dialogRef.close();
      }

}
