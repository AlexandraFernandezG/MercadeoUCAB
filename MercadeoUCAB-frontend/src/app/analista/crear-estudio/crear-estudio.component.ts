import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-crear-estudio',
  templateUrl: './crear-estudio.component.html',
  styleUrls: ['./crear-estudio.component.css']
})
export class CrearEstudioComponent{

  data = {
    preguntas: [
      {
        pregunta: "",
        respuestas: [
          {
            respuesta: "",
          }
        ]
      }
    ]
  }

  myForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.myForm = this.fb.group({
      preguntas: this.fb.array([])
    })

    this.setPreguntas();
  }

  addPregunta() {
    let control = <FormArray>this.myForm.controls.preguntas;
    control.push(
      this.fb.group({
        pregunta: [''],
        respuestas: this.fb.array([])
      })
    )
  }

  deletePregunta(index) {
    let control = <FormArray>this.myForm.controls.preguntas;
    control.removeAt(index)
  }

  addRespuesta(control) {
    control.push(
      this.fb.group({
        respuesta: ['']
      }))
  }

  deleteRespuesta(control, index) {
    control.removeAt(index)
  }

  setPreguntas() {
    let control = <FormArray>this.myForm.controls.preguntas;
    this.data.preguntas.forEach(x => {
      control.push(this.fb.group({ 
        pregunta: x.pregunta, 
        respuestas: this.setRespuestas(x) }))
    })
  }

  setRespuestas(x) {
    let arr = new FormArray([])
    x.respuestas.forEach(y => {
      arr.push(this.fb.group({ 
        respuesta: y.respuesta
      }))
    })
    return arr;
  }
}
