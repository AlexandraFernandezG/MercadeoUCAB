import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { PresentacionesService } from 'src/app/servicios/presentaciones.service';
import { Presentacion } from 'src/app/modelos/presentacion';
import { Presentacion2 } from 'src/app/modelos/presentacion';
import { PresentacionComponent } from 'src/app/admin/presentacion/presentacion.component';



@Component({
  selector: 'app-add-presentacion',
  templateUrl: './add-presentacion.component.html',
  styleUrls: ['./add-presentacion.component.css']
})
export class AddPresentacionComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: PresentacionesService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<PresentacionComponent>
    ) {

    this.presentacionForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)])
    });
   }

  presentacion: Presentacion = {_id: 1 , _nombre: '', _descripcion: '', _estatus: 'Activo'};
  presentacion2: Presentacion2 = {id: 1 , nombre: '', descripcion: '', estatus: 'Activo'};
  presentacionForm: FormGroup;
  ngOnInit(): void {
  }

  addPresentacion(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createPresentacion({
     id,
    nombre,
    descripcion,
    estatus
    } as Presentacion2).subscribe();
    console.log(id, nombre, descripcion, estatus);
    this.dialogRef.close();
  }
}
