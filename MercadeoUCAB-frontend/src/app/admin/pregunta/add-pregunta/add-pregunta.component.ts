import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { Pregunta } from 'src/app/modelos/pregunta';
import { Pregunta2 } from 'src/app/modelos/pregunta';
import { PreguntaComponent } from 'src/app/admin/pregunta/pregunta.component';
import { Subcategoria, Subcategoria2 } from 'src/app/modelos/subcategoria';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';

@Component({
  selector: 'app-add-pregunta',
  templateUrl: './add-pregunta.component.html',
  styleUrls: ['./add-pregunta.component.css']
})
export class AddPreguntaComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: PreguntasService,
    private serviceSubcategoria: SubcategoriasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<PreguntaComponent>
    ) {}

    preguntaForm = this.fb.group({
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(200)]),
      subcategoriaDto: new FormControl('',[Validators.required]),
      tipoPregunta: new FormControl('',[Validators.required]),
      respuestas: this.fb.array([
      this.fb.control('')
       ])
   });
    subcategorias: Subcategoria[] =[
    ]
    tipoPreguntas = [
      { nombre: 'Abierta', value: 1 },
      { nombre: 'Verdadero o Falso', value: 2 },
      { nombre: 'Escala', value: 3 },
      { nombre: 'Selección Simple', value: 4 },
      { nombre: 'Selección Múltiple', value: 5 }
    ];
    preguntas: any;
    pregunta: Pregunta2 ={
      id: 0,
      estatus: '',
      descripcion: '',
      tipoPregunta:'',
      subcategoriaDto: 0,
      usuarioDto: 0
    };

  ngOnInit(): void {
    this.dialogRef.updateSize('600px','600px')
    this.service.getPreguntas()
    .subscribe(data => {this.preguntas = data;
    } );
    this.serviceSubcategoria.getSubcategorias()
    .subscribe(catego => {this.subcategorias = catego;
    } );
  }

    get respuestas(): FormArray {
      return this.preguntaForm.get('respuestas') as FormArray;
    }

    addRespuesta(){
      
      this.respuestas.push(this.fb.control(''));
    }

    deleteRespuesta(indice: number){
      this.respuestas.removeAt(indice);
    }
/*  addpregunta(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createpregunta({
     id,
    nombre,
    descripcion,
    estatus,
    categoriaDto: this.preguntaForm.get("categoriaDto").value
    } as pregunta2).subscribe();
    console.log(pregunta2);
    this.dialogRef.close();
  }*/
}

