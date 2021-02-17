import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { Pregunta, Pregunta3 } from 'src/app/modelos/pregunta';
import { Pregunta2 } from 'src/app/modelos/pregunta';
import { respuestaPregunta, respuestaPregunta2 } from 'src/app/modelos/respuestaPregunta';
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
    public dialogRef: MatDialogRef<PreguntaComponent>,
    
    ) {}

    preguntaForm = this.fb.group({
      descripcion: new FormControl( '', [ Validators.required, Validators.maxLength(200)]),
      subcategoriaDto: new FormControl('', [Validators.required]),
      tipoPregunta: new FormControl('', [Validators.required]),
      respuestas: this.fb.array([
        this.addRespuesta()
       ])
   });
    subcategorias: any = [
    ];
    preguntasAgregadas: Pregunta3[] = [];
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
    this.service.getPreguntas()
    .subscribe(data => {this.preguntas = data;
    } );
    this.serviceSubcategoria.getSubcategorias()
    .subscribe(catego => {
      console.log(catego.Subcategorias)
      this.subcategorias = catego.Subcategorias;
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

    Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

    addPregunta(): void{
    let id = 1;
    const estatus = 'Activo';
    const usuarioDto = 1;
    const respuestas = this.preguntaForm.get('respuestas').value;
    this.service.createPregunta({
    id,
    descripcion: this.preguntaForm.get('descripcion').value,
    tipoPregunta: this.preguntaForm.get('tipoPregunta').value,
    estatus,
    usuarioDto,
    subcategoriaDto: this.preguntaForm.get('subcategoriaDto').value
    } as Pregunta2).subscribe(
      async response => {
      console.log('antes:', this.preguntasAgregadas)
      this.preguntasAgregadas = JSON.parse(localStorage.getItem('preguntasEst'));
      console.log('traer: ',this.preguntasAgregadas);
      this.preguntasAgregadas.push(response.Pregunta);
      console.log('agregar: ', this.preguntasAgregadas);
      localStorage.setItem('preguntasEst',  JSON.stringify (this.preguntasAgregadas))
      console.log(response);
      console.log(respuestas);
      await this.Delay(2000);
      if (this.preguntaForm.get('tipoPregunta').value == 'Selección Simple' || this.preguntaForm.get('tipoPregunta').value == 'Selección Múltiple'){
        this.service.createPreguntaRespuesta(response.Pregunta.id,respuestas).subscribe(
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
