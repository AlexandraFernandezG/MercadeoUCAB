import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { TiposService } from 'src/app/servicios/tipos.service';
import { TipoComponent } from 'src/app/admin/tipo/tipo.component';
import { Tipo } from 'src/app/modelos/tipo';
import { Tipo2 } from 'src/app/modelos/tipo';

@Component({
  selector: 'app-edit-tipo',
  templateUrl: './edit-tipo.component.html',
  styleUrls: ['./edit-tipo.component.css']
})
export class EditTipoComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: TiposService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<TipoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Tipo2,
    ) {

      this.tipoForm = this.fb.group({
        nombre: new FormControl([ Validators.maxLength(100)]),
        descripcion: new FormControl([ Validators.maxLength(150)])
      });
    }
  tipo: Tipo = {_id: 1 , _nombre: '' , _descripcion: '', _estatus: 'Activo'};
  tipo2: Tipo2 = {id: 1, nombre: '', descripcion: '', estatus: 'Activo'};
  tipoForm: FormGroup;
  ngOnInit(): void {
    this.getId();
      }

  getId(){
    console.log('primero', this.tipo);
    const id = this.data.id;
    this.service.getTipo(id).subscribe(data => {this.tipo = data;
    });
  }

  editTipo( tipo: Tipo): void{
    console.log('segundo', tipo);
    const editCa: Tipo2 = {
      id: this.data.id,
      nombre: tipo._nombre,
      descripcion: tipo._descripcion,
      estatus: tipo._estatus
    };
    this.service.updateTipo(editCa).subscribe();
    this.dialogRef.close();
      }

}

