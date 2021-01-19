import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
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
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<PreguntaComponent>,
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
    this.dialogRef.updateSize('600px', '600px');
    this.service.getPreguntas()
    .subscribe(data => {this.preguntas = data;
    } );
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

}
