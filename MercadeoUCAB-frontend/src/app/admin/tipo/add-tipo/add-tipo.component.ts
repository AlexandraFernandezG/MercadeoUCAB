import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { TiposService } from 'src/app/servicios/tipos.service';
import { Tipo } from 'src/app/modelos/tipo';
import { Tipo2 } from 'src/app/modelos/tipo';
import { TipoComponent } from 'src/app/admin/tipo/tipo.component';



@Component({
  selector: 'app-add-tipo',
  templateUrl: './add-tipo.component.html',
  styleUrls: ['./add-tipo.component.css']
})
export class AddTipoComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: TiposService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<TipoComponent>
    ) {

    this.tipoForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)])
    });
   }

  tipo: Tipo = {_id: 1 , _nombre: '', _descripcion: '', _estatus: 'Activo'};
  tipo2: Tipo2 = {id: 1 , nombre: '', descripcion: '', estatus: 'Activo'};
  tipoForm: FormGroup;
  ngOnInit(): void {
  }

  addTipo(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createTipo({
     id,
    nombre,
    descripcion,
    estatus
    } as Tipo2).subscribe();
    console.log(id, nombre, descripcion, estatus);
    this.dialogRef.close();
  }
}
