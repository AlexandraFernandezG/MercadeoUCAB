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
    private service: EncuestasService
  ) { }
  
  respuestas = <any>[];
  respuestas2: Respuesta2[];
  favoriteSeason: string;
  escalas: number[] = [1, 2 , 3, 4, 5];
  opcionesVF: string[] = ['Verdadero' , 'Falso'];
  respuestasAso: respuestaPregunta[];
  respuestasAso2: respuestaPregunta3[];
  
  ngOnInit(): void {
    this.service.getPreguntasEncuesta(1)
    .subscribe(data => {this.preguntas2 = data;
      console.log(this.preguntas2);
      this.preguntas2[0].visible=true;
    } );
    this.service.getRespuestasAsociadas(1)
    .subscribe(data => {this.respuestasAso2 = data;
      console.log(this.respuestasAso);
    } );
  }

  siguiente(index: number) {
   // this.mandarRespuestas(index);

    this.preguntas2[index].visible = false;
    this.preguntas2[index + 1].visible = true;


  }


  enviarRespuestas() {
    let respuestas2: Respuesta2[] = [];
    let h = 0;
    for(let j = 0; j < this.respuestas.length; j++){
      if (this.respuestas[j] === undefined){
        this.respuestas.splice(j, 1);
      }
    }

    for(let k = 0; k < this.preguntas2.length; k++){

      if (this.preguntas2[k].tipoPregunta === 'Abierta') {

        let resp: Respuesta2 = {
          estatus: 'Activo',
          respuestaAbierta: this.respuestas[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        h++;

        respuestas2.push(resp);
        console.log('respuestaabierta h:', h);
        console.log('respuestaabierta k:', k);
        console.log('respuestaabierta:', resp);
      }


      if (this.preguntas2[k].tipoPregunta === 'Verdadero o Falso') {

        let resp: Respuesta2 = {
          estatus: 'Activo',
          verdaderoFalso: this.respuestas[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        console.log('respuestavf h:', h);
        console.log('respuestavf k:', k);
        console.log('respuestavf:', resp);
        h++;

        respuestas2.push(resp);
      }

      if ( this.preguntas2[k].tipoPregunta === 'Escala') {

        let resp: Respuesta2 = {
          estatus: 'Activo',
          escala: this.respuestas[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        console.log('respuestaescala h:', h);
        console.log('respuestaescala k:', k);
        console.log('respuestaescala:', resp);
        h++;

        respuestas2.push(resp);
      }

      if (this.preguntas2[k].tipoPregunta === 'Selección Simple') {

        let resp: Respuesta2 = {
          estatus: 'Activo',
          respuestaSimple: this.respuestas[h],
          usuarioDto: 1,
          preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
        };
        console.log('respuestaseleccions h:', h);
        console.log('respuestaseleccions k:', k);
        console.log('respuestaseleccions:', resp);
        h++;

        respuestas2.push(resp);
      }

      if (this.preguntas2[k].tipoPregunta === 'Selección Múltiple'){
          for (let i =0; i < this.respuestasAso2.length; i++){
            if ((this.respuestasAso2[i].fkPregunta === this.preguntas2[k].idPreguntaEncuesta)
               &&(this.respuestasAso2[i].completado === true)){

               let resp: Respuesta2 = {
                estatus: 'Activo',
                respuestaMultiple: this.respuestasAso2[i].pregunta,
                usuarioDto: 1,
                preguntaEstudioDto: this.preguntas2[k].idPreguntaEstudio
              };
              respuestas2.push(resp);
              console.log('respuestamultiple h:', h);
              console.log('respuestamultiple k:', k);
              console.log('respuestamultiple:', resp);
            }
          }
        }
    }
    console.log(this.respuestas);
    console.log(respuestas2);
    this.service.addRespuesta(respuestas2);
    }

}
