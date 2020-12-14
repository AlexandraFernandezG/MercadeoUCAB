import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { PresentacionesService } from 'src/app/servicios/presentaciones.service';
import { PresentacionComponent } from 'src/app/admin/presentacion/presentacion.component';
import { Presentacion } from 'src/app/modelos/presentacion';
import { Presentacion2 } from 'src/app/modelos/presentacion';

@Component({
  selector: 'app-edit-presentacion',
  templateUrl: './edit-presentacion.component.html',
  styleUrls: ['./edit-presentacion.component.css']
})
export class EditPresentacionComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: PresentacionesService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<PresentacionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Presentacion2,
    ) {

      this.presentacionForm = this.fb.group({
        nombre: new FormControl([ Validators.maxLength(100)]),
        caracteristicas: new FormControl([ Validators.maxLength(150)])
      });
    }
  presentacion: Presentacion = {_id: 1 , _nombre: '' , _caracteristicas: '', _estatus: 'Activo'};
  presentacion2: Presentacion2 = {id: 1, nombre: '', caracteristicas: '', estatus: 'Activo'};
  presentacionForm: FormGroup;
  ngOnInit(): void {
    this.getId();
      }

  getId(){
    console.log('primero', this.presentacion);
    const id = this.data.id;
    this.service.getPresentacion(id).subscribe(data => {this.presentacion = data;
    });
  }

  editPresentacion( presentacion: Presentacion): void{
    console.log('segundo', presentacion);
    const editPre: Presentacion2 = {
      id: this.data.id,
      nombre: presentacion._nombre,
      caracteristicas: presentacion._caracteristicas,
      estatus: presentacion._estatus
    };
    this.service.updatePresentacion(editPre).subscribe();
    this.dialogRef.close();
      }

}

