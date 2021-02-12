import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input'; 
import { ActivatedRoute, Router } from '@angular/router';
import { Pregunta, Pregunta2, PreguntaEncuesta } from 'src/app/modelos/pregunta';
import { Respuesta, Respuesta2 } from 'src/app/modelos/respuesta';
import { respuestaPregunta, respuestaPregunta2, respuestaPregunta3 } from 'src/app/modelos/respuestaPregunta';
import { EncuestasService } from 'src/app/servicios/encuestas.service';

@Component({
  selector: 'app-respuestas-encuesta',
  templateUrl: './respuestas-encuesta.component.html',
  styleUrls: ['./respuestas-encuesta.component.css']
})
export class RespuestasEncuestaComponent implements OnInit {

  preguntas: Pregunta[];
  preguntas2: PreguntaEncuesta[];

  constructor(
    private service: EncuestasService,
    public actRoute: ActivatedRoute
  ) { }
  
  respuestas = <any>[];
  respuestas2: Respuesta2[];
  favoriteSeason: string;
  escalas: number[] = [1, 2 , 3, 4, 5];
  opcionesVF: string[] = ['Verdadero' , 'Falso'];
  respuestasAso: respuestaPregunta[];
  respuestasAso2: respuestaPregunta3[];
  idEstudio: number;
  
  ngOnInit(): void {
    this.idEstudio = +this.actRoute.snapshot.paramMap.get("id");
    this.service.getPreguntasEncuesta(this.idEstudio)
    .subscribe(data => {this.preguntas2 = data;
      console.log(this.preguntas2);
      this.preguntas2[0].visible=true;
    } );
    this.service.getRespuestasAsociadas(this.idEstudio)
    .subscribe(data => {this.respuestasAso2 = data;
      console.log(this.respuestasAso);
    } );
  }

  Siguiente(index: number) {
   // this.mandarRespuestas(index);

    this.preguntas2[index].visible = false;
    this.preguntas2[index + 1].visible = true;


  }
  Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async enviarRespuestas(index: number) {

    if (this.preguntas2[index].tipoPregunta === 'Abierta') {

        let r: Respuesta2 = {
          pregunta: this.preguntas2[index].descripcion,
          estatus: 'Activo',
          respuestaAbierta: this.respuestas[index],
          usuarioDto:  JSON.parse(localStorage.getItem('usuarioID')),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.service.addRespuesta(r);
        /* this.resps = []; */
      }

    if (this.preguntas2[index].tipoPregunta === 'Seleccion Simple') {

        let r: Respuesta2 = {
          pregunta: this.preguntas2[index].descripcion,
          estatus: 'Activo',
          respuestaSimple: this.respuestas[index],
          usuarioDto:   JSON.parse(localStorage.getItem('usuarioID')),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.service.addRespuesta(r);
        /* this.resps = []; */
      }

    if (this.preguntas2[index].tipoPregunta === 'Verdadero o Falso') {

        let r: Respuesta2 = {
          pregunta: this.preguntas2[index].descripcion,
          estatus: 'Activo',
          verdaderoFalso: this.respuestas[index],
          usuarioDto: JSON.parse(localStorage.getItem('usuarioID')),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };
        this.service.addRespuesta(r);
      }

    if ( this.preguntas2[index].tipoPregunta === 'Escala') {

        let r: Respuesta2 = {
          pregunta: this.preguntas2[index].descripcion,
          estatus: 'Activo',
          escala: this.respuestas[index],
          usuarioDto: JSON.parse(localStorage.getItem('usuarioID')),
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };
        this.service.addRespuesta(r);
   
      }

    if (this.preguntas2[index].tipoPregunta === 'Seleccion Multiple'){
          for (let i =0; i < this.respuestas.length; i++){
           //for (let j = 0; j < this.preguntas2.length; j++){
            if ((this.respuestas[i].fkPregunta === this.preguntas2[index].idPreguntaEncuesta)
            && this.respuestas[i].completado === true){

              let r: Respuesta2 = {
                pregunta: this.preguntas2[index].descripcion,
                estatus: 'Activo',
                respuestaMultiple: this.respuestas[i].pregunta,
                usuarioDto:  JSON.parse(localStorage.getItem('usuarioID')),
                preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
              };

              await this.Delay(1000);
              this.service.addRespuesta(r);
              console.log(r);
              console.log(this.respuestas[i].pregunta);

            }
          }
    }
  }
}