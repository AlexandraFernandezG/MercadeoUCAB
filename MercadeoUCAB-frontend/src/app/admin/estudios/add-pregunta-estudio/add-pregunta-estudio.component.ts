import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Estudio2 } from 'src/app/modelos/estudio';
import { Pregunta2 } from 'src/app/modelos/pregunta';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Usuario } from 'src/app/modelos/usuario';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { PreguntaComponent } from '../../pregunta/pregunta.component';

@Component({
  selector: 'app-add-pregunta-estudio',
  templateUrl: './add-pregunta-estudio.component.html',
  styleUrls: ['./add-pregunta-estudio.component.css']
})
export class AddPreguntaEstudioComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private service: PreguntasService,
    private serviceSubcategoria: SubcategoriasService,
    private preguntasService: PreguntasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<PreguntaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Estudio2,
  ) { }

  preguntaForm = this.fb.group({
    descripcion: new FormControl( '', [ Validators.required, Validators.maxLength(200)]),
    subcategoriaDto: new FormControl('', [Validators.required]),
    tipoPregunta: new FormControl('', [Validators.required]),
    respuestas: this.fb.array([
      this.addRespuesta()
     ])
  });

  subcategorias: Subcategoria[] = [
  ];
  analistas: Usuario[];
  preguntas: any;
  pregunta: Pregunta2 = {
    id: 0,
    estatus: '',
    descripcion: '',
    tipoPregunta: '',
    subcategoriaDto: 0,
    usuarioDto: 0
  };

  ngOnInit(): void {
    //this.dialogRef.updateSize('600px', '600px');
    this.service.getPreguntas()
    .subscribe(data => {this.preguntas = data;
    } );
    console.log(this.data)
    this.serviceSubcategoria.getSubcategorias()
    .subscribe(catego => {this.subcategorias = catego;
    } );
  }

  addRespuesta(){
    return this.fb.group({
      id: 1,
      nombre: [''],
      estatus: 'Activo',
      preguntaEncuestaDto: 1
    });
  }

  addResp(){
    (this.preguntaForm.controls.respuestas as FormArray).push(this.addRespuesta());
  }
  
  deleteRespuesta(indice: number){
    (this.preguntaForm.controls.respuestas as FormArray).removeAt(indice);
  }

  close(){
    this.dialogRef.close();
  }

  addPreguntaEstudio(): void{
    let id = 1;
    const estatus = 'Activo';
    const usuarioDto = 1;
    const respuestas = this.preguntaForm.get('respuestas').value;
    this.service.createPreguntaEstudio({
    id,
    descripcion: this.preguntaForm.get('descripcion').value,
    tipoPregunta: this.preguntaForm.get('tipoPregunta').value,
    estatus,
    usuarioDto,
    subcategoriaDto: this.preguntaForm.get('subcategoriaDto').value
    } as Pregunta2, this.data.id).subscribe(

      response => {
      if (this.preguntaForm.get('tipoPregunta').value == 'Selección Simple' || this.preguntaForm.get('tipoPregunta').value == 'Selección Múltiple'){
        this.service.createPreguntaRespuesta(response.id,respuestas).subscribe(
          respuesta => {
            console.log(respuesta);
          }
        );
     }
      }
    );
    this.dialogRef.close();
  }
}