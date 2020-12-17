import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { MarcasService } from 'src/app/servicios/marcas.service';
import { Marca } from 'src/app/modelos/marca';
import { Marca2} from 'src/app/modelos/marca';
import { MarcaComponent } from 'src/app/admin/marca/marca.component';



@Component({
  selector: 'app-add-marca',
  templateUrl: './add-marca.component.html',
  styleUrls: ['./add-marca.component.css']
})
export class AddMarcaComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: MarcasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<MarcaComponent>
    ) {

    this.marcaForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)])
    });
   }

  marca: Marca = {_id: 1 , _nombre: '', _descripcion: '', _estatus: 'Activo'};
  marca2: Marca2 = {id: 1 , nombre: '', descripcion: '', estatus: 'Activo'};
  marcaForm: FormGroup;
  ngOnInit(): void {
  }

  addMarca(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createMarca({
     id,
    nombre,
    descripcion,
    estatus
    } as Marca2).subscribe();
    console.log(id, nombre, descripcion, estatus);
    this.dialogRef.close();
  }
}
