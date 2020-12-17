import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { MarcasService } from 'src/app/servicios/marcas.service';
import { MarcaComponent } from 'src/app/admin/marca/marca.component';
import { Marca } from 'src/app/modelos/marca';
import { Marca2 } from 'src/app/modelos/marca';

@Component({
  selector: 'app-edit-marca',
  templateUrl: './edit-marca.component.html',
  styleUrls: ['./edit-marca.component.css']
})
export class EditMarcaComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: MarcasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<MarcaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Marca2,
    ) {

      this.marcaForm = this.fb.group({
        nombre: new FormControl([ Validators.maxLength(100)]),
        descripcion: new FormControl([ Validators.maxLength(150)])
      });
    }
  marca: Marca = {_id: 1 , _nombre: '' , _descripcion: '', _estatus: 'Activo'};
  marca2: Marca2 = {id: 1, nombre: '', descripcion: '', estatus: 'Activo'};
  marcaForm: FormGroup;
  ngOnInit(): void {
    this.getId();
      }

  getId(){
    console.log('primero', this.marca);
    const id = this.data.id;
    this.service.getMarca(id).subscribe(data => {this.marca = data;
    });
  }

  editMarca( marca: Marca): void{
    console.log('segundo', marca);
    const editMa: Marca2 = {
      id: this.data.id,
      nombre: marca._nombre,
      descripcion: marca._descripcion,
      estatus: marca._estatus
    };
    this.service.updateMarca(editMa).subscribe();
    this.dialogRef.close();
      }

}

