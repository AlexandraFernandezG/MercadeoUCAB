import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-solicitud-estudio',
  templateUrl: './solicitud-estudio.component.html',
  styleUrls: ['./solicitud-estudio.component.css']
})
export class SolicitudEstudioComponent implements OnInit {

  solicitudForm: FormGroup;


  constructor(private fb: FormBuilder, private router: Router) {

    this.solicitudForm = this.fb.group({
      producto: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      edadMinima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      edadMaxima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      genero: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      estadoCivil: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      estado: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      municipio: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      nivelEconomico: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      conexion: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      ocupacion: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      nivelAcademico: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      
    });
   }

  ngOnInit(): void {
  }

  regresarEstudios(): void {
    this.router.navigate(['/cliente/estudios']);
  }

  onSubmit(){
    console.log(this.solicitudForm.value);
   }

}
