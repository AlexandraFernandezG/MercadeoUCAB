import { Component, OnInit, Input, Inject } from '@angular/core';
import { TiposService } from 'src/app/servicios/tipos.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddTipoComponent } from './add-tipo/add-tipo.component';
import { EditTipoComponent } from './edit-tipo/edit-tipo.component';
import { Tipo, Tipo2 } from 'src/app/modelos/tipo';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';



@Component({
  selector: 'app-tipo',
  templateUrl: './tipo.component.html',
  styleUrls: ['./tipo.component.css']
})
export class TipoComponent implements OnInit {

  tipos: Tipo[];


  @Input() tipoData: any = {};
  constructor(
    private service: TiposService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  tipoForm: FormGroup;
  ngOnInit() {
    this.service.getTipos()
    .subscribe(data => {this.tipos = data;
    } );

  }
  openModal(){
    this.dialog.open(AddTipoComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditTipoComponent,
      {
        data: {id: id}
      }
    );
  }

  deleteTipo( tipo: Tipo): void{
    console.log('segundo', tipo);
    const deleteCa: Tipo2 = {
      id: tipo._id,
      nombre: tipo._nombre,
      descripcion: tipo._descripcion,
      estatus: 'Inactivo'
    };
    this.service.updateTipo(deleteCa).subscribe();
      }
}
