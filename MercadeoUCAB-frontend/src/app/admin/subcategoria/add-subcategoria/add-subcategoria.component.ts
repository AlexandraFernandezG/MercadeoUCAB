import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Subcategoria2 } from 'src/app/modelos/subcategoria';
import { SubcategoriaComponent } from 'src/app/admin/subcategoria/subcategoria.component';
import { Categoria } from 'src/app/modelos/categoria';

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
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<SubcategoriaComponent>
    ) {

    this.subcategoriaForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)])
    });
   }
    categorias: Categoria[] =[
      {_id: 1, _nombre: 'Steak', _descripcion: 'cwwececw', _estatus: 'Activo'},
      {_id: 2, _nombre: 'Steak2', _descripcion: 'cwwececw', _estatus: 'Activo'},
      {_id: 3, _nombre: 'Steak3', _descripcion: 'cwwececw', _estatus: 'Activo'}
    ];
 // subcategoria: Subcategoria = {_id: 1 , _nombre: '', _descripcion: '', _estatus: 'Activo', _fk_categoria:};
 // subcategoria2: Subcategoria2 = {id: 1 , nombre: '', descripcion: '', estatus: 'Activo'  _fk_categoria:};
  subcategoriaForm: FormGroup;
  ngOnInit(): void {
  }
   

  addSubcategoria(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createSubcategoria({
     id,
    nombre,
    descripcion,
    estatus
    } as Subcategoria2).subscribe();
    console.log(id, nombre, descripcion, estatus);
    this.dialogRef.close();
  }
}

