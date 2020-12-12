import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { CategoriaComponent } from 'src/app/admin/categoria/categoria.component';
import { Categoria } from 'src/app/modelos/categoria';
import { Categoria2 } from 'src/app/modelos/categoria';

@Component({
  selector: 'app-edit-categoria',
  templateUrl: './edit-categoria.component.html',
  styleUrls: ['./edit-categoria.component.css']
})
export class EditCategoriaComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: CategoriasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<CategoriaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Categoria2,
    ) {

      this.categoriaForm = this.fb.group({
        nombre: new FormControl([ Validators.maxLength(100)]),
        descripcion: new FormControl([ Validators.maxLength(150)])
      });
    }
  categoria: Categoria = {_id: 1 , _nombre: '' , _descripcion: '', _estatus: 'Activo'};
  categoria2: Categoria2 = {id: 1, nombre: '', descripcion: '', estatus: 'Activo'};
  categoriaForm: FormGroup;
  ngOnInit(): void {
    this.getId();
      }

  getId(){
    console.log('primero', this.categoria);
    const id = this.data.id;
    this.service.getCategoria(id).subscribe(data => {this.categoria = data;
    });
  }

  editCategoria( categoria: Categoria): void{
    console.log('segundo', categoria);
    const editCa: Categoria2 = {
      id: this.data.id,
      nombre: categoria._nombre,
      descripcion: categoria._descripcion,
      estatus: categoria._estatus
    };
    this.service.updateCategoria(editCa).subscribe();
    this.dialogRef.close();
      }

}

