import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio2 } from 'src/app/modelos/estudio';
import { Solicitud2 } from 'src/app/modelos/solicitud';
import { Usuario } from 'src/app/modelos/usuario';

@Component({
  selector: 'app-add-estudio',
  templateUrl: './add-estudio.component.html',
  styleUrls: ['./add-estudio.component.css']
})
export class AddEstudioComponent implements OnInit {

  estudioForm: FormGroup;
  estudio: Estudio2;
  usuarios: Usuario[];


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Solicitud2,
    private fb: FormBuilder,
  ) { 
    this.estudioForm = this.fb.group({

      nombre: new FormControl( '',[ Validators.maxLength(150)]),
      instrumento: new FormControl('',[ Validators.maxLength(50)]),
      fechaFin: new FormControl('',[  Validators.maxLength(50)]),
      analista: new FormControl('',[  Validators.maxLength(50)]),
    })
  }

  ngOnInit(): void {
    console.log(this.data.id);
  }
  addEstudio(){

  }
}
