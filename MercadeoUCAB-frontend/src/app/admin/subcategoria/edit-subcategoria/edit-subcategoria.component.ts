import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { SubcategoriaComponent } from 'src/app/admin/subcategoria/subcategoria.component';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Subcategoria2 } from 'src/app/modelos/subcategoria';
import { Categoria } from 'src/app/modelos/categoria';

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
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<SubcategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Subcategoria2,
    ) {

      this.subcategoriaForm = this.fb.group({
        nombre: new FormControl([ Validators.maxLength(100)]),
        caracteristicas: new FormControl([ Validators.maxLength(150)])
      });
    }
 // subcategoria: Subcategoria = {_id: 1 , _nombre: '' , _descripcion: '', _estatus: 'Activo'};
 // subcategoria2: Subcategoria2 = {id: 1, nombre: '', descripcion: '', estatus: 'Activo'};
 categorias: Categoria[] =[
  {_id: 1, _nombre: 'Steak', _descripcion: 'cwwececw', _estatus: 'Activo'},
  {_id: 2, _nombre: 'Steak2', _descripcion: 'cwwececw', _estatus: 'Activo'},
  {_id: 3, _nombre: 'Steak3', _descripcion: 'cwwececw', _estatus: 'Activo'}
];
  subcategoriaForm: FormGroup;
  ngOnInit(): void {
    this.getId();
      }

  getId(){
   // console.log('primero', this.subcategoria);
    const id = this.data.id;
   // this.service.getSubcategoria(id).subscribe(data => {this.subcategoria = data;
    }
/*
  editSubcategoria( subcategoria: Subcategoria): void{
    console.log('segundo', subcategoria);
    const editSub: Subcategoria2 = {
      id: this.data.id,
      nombre: subcategoria._nombre,
      descripcion: subcategoria._descripcion,
      estatus: subcategoria._estatus
    };
    this.service.updatesubcategoria(editSub).subscribe();
    this.dialogRef.close();
      }*/

}

