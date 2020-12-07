import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-solicitud-estudio',
  templateUrl: './solicitud-estudio.component.html',
  styleUrls: ['./solicitud-estudio.component.css']
})
export class SolicitudEstudioComponent implements OnInit {

  solicitudForm: FormGroup;


  constructor(private fb: FormBuilder) {

    this.solicitudForm = this.fb.group({
      producto: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      edadMinima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      edadMaxima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      nivelEconomico: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      genero: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
    });
   }

  ngOnInit(): void {
  }

  onSubmit(){
    console.log(this.solicitudForm.value);
  
   }

}
