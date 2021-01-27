import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio2 } from 'src/app/modelos/estudio';
import { Solicitud2 } from 'src/app/modelos/solicitud';
import { Usuario3 } from 'src/app/modelos/usuario';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { SolicitudesComponent } from '../../solicitudes/solicitudes.component';

@Component({
  selector: 'app-add-estudio',
  templateUrl: './add-estudio.component.html',
  styleUrls: ['./add-estudio.component.css']
})
export class AddEstudioComponent implements OnInit {

  estudioForm: FormGroup;
  estudio: Estudio2;
  analistas: Usuario3[];


  constructor(
    public dialogRef: MatDialogRef<SolicitudesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Solicitud2,
    private fb: FormBuilder,
    private estudiosService: EstudiosService,
    private analistasService: UsuariosService,
  ) { 
    this.estudioForm = this.fb.group({

      nombre: new FormControl( '',[ Validators.maxLength(150)]),
      instrumento: new FormControl('',[ Validators.maxLength(50)]),
      fechaInicio: new FormControl('',[  Validators.maxLength(50)]),
      fechaFin: new FormControl('',[  Validators.maxLength(50)]),
      analista: new FormControl('',[  Validators.maxLength(50)]),
    })
  }

  ngOnInit(): void {
    console.log(this.data.id);
    this.analistasService.getAnalistas().subscribe( analistasData =>
      {this.analistas = analistasData} );
    console.log(this.analistas)
  }
  addEstudio(){
    this.estudio = {
      id: 1,
      nombre: this.estudioForm.value.nombre,
      tipoInstrumento: this.estudioForm.value.instrumento,
      fechaInicio: this.estudioForm.value.fechaInicio,
      fechaFin: this.estudioForm.value.fechaFin,
      estatus: 'activo',
      estado: 'en espera',
      usuarioDto: this.estudioForm.value.analista,
      solicitudEstudioDto: this.data.id,
    }
    console.log(this.estudio)
    this.estudiosService.createEstudio(
      this.estudio as Estudio2
    ).subscribe();
    this.dialogRef.close();
  }
  close(){
    this.dialogRef.close();
  }
}
