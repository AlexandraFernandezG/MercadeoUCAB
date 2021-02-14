import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input'; 
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
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
    public actRoute: ActivatedRoute,
    public router: Router,
    private servicenotifications: NotificationsService,
  ) { }
  
  respuestas = <any>[];
  respuestas2: Respuesta2[];
  favoriteSeason: string;
  escalas: number[] = [1, 2 , 3, 4, 5];
  opcionesVF: string[] = ['Verdadero' , 'Falso'];
  respuestasAso: respuestaPregunta[];
  respuestasAso2: respuestaPregunta3[];
  idEstudio: number;
  idUsuario: number;
  j: boolean;
  
  
  ngOnInit(): void {
    this.idEstudio = +this.actRoute.snapshot.paramMap.get("idEstudio");
    this.idUsuario = +this.actRoute.snapshot.paramMap.get("idUsuario");
    this.service.getPreguntasEncuesta(this.idEstudio, this.idUsuario)
    .subscribe(data => {this.preguntas2 = data;
      console.log(this.preguntas2);
      this.preguntas2[0].visible=true;
    } );
    this.service.getRespuestasAsociadas(this.idEstudio )
    .subscribe(data => {this.respuestasAso2 = data;
      console.log(this.respuestasAso);
    } );
  }

  onSucess(message){
    this.servicenotifications.success('Exito', message, {
      position: ['bottom', 'right'],
      timeOut: 2000,
      animate: 'fade',
      showProgressBar: true,
      })
  }

  Siguiente(index: number) {
   // this.mandarRespuestas(index);

    this.preguntas2[index].visible = false;
    this.preguntas2[index + 1].visible = true;
    console.log(index);
    this.enviarRespuestas(index, false);
    if (index == 0){
      console.log('control index 0')
      console.log('estudio:',this.idEstudio)
      console.log('usuario',this.idUsuario)
      this.service.cambiarEstatus(this.idEstudio, this.idUsuario).subscribe();
    }

  }
  Delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  async enviarRespuestas(index: number, j: boolean) {

    console.log('hola');

    if (this.preguntas2[index].tipoPregunta === 'Abierta') {

        let r: Respuesta2 = {
          estatus: 'Activo',
          respuestaAbierta: this.respuestas[index],
          usuarioDto: this.idUsuario,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.service.addRespuesta(r);
        
      }

    if (this.preguntas2[index].tipoPregunta === 'Selección Simple') {

        let r: Respuesta2 = {
          estatus: 'Activo',
          respuestaSimple: this.respuestas[index],
          usuarioDto: this.idUsuario,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };


        /* respuestas2.push(r); */
        this.service.addRespuesta(r);
      }

    if (this.preguntas2[index].tipoPregunta === 'Verdadero o Falso') {

        let r: Respuesta2 = {
          estatus: 'Activo',
          verdaderoFalso: this.respuestas[index],
          usuarioDto:  this.idUsuario,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };
        this.service.addRespuesta(r);
      }

    if ( this.preguntas2[index].tipoPregunta === 'Escala') {

        let r: Respuesta2 = {
          estatus: 'Activo',
          escala: this.respuestas[index],
          usuarioDto: this.idUsuario,
          preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
        };
        this.service.addRespuesta(r);
      }

    if (this.preguntas2[index].tipoPregunta === 'Selección Múltiple'){
      console.log('entree');
          for (let i =0; i < this.respuestas.length; i++){
           //for (let j = 0; j < this.preguntas2.length; j++){
            if ((this.respuestas[i].fkPregunta === this.preguntas2[index].idPreguntaEncuesta)
            && this.respuestas[i].completado === true){
              let r: Respuesta2 = {
                estatus: 'Activo',
                respuestaMultiple: this.respuestas[i].pregunta,
                usuarioDto: this.idUsuario,
                preguntaEstudioDto: this.preguntas2[index].idPreguntaEstudio
              };

              this.service.addRespuesta(r);
              console.log(r);
              console.log(this.respuestas[i].pregunta);

            }
          }
    }
    
    
    if(j){
      setTimeout(() => {
        this.service.cambiarEstatus(this.idEstudio, this.idUsuario).subscribe(
          result => {this.onSucess(result.mensaje)}
        );
      },2000);
      setTimeout(() => {
      if ( JSON.parse(localStorage.getItem('rol')) == 'Analista' ){
        this.router.navigate(['/analista']);
      }else if( JSON.parse(localStorage.getItem('rol')) == 'Encuestado' ){
        this.router.navigate(['/encuestado']);
      }
    },3000);
    }
  }
  atras(){
    this.router.navigate(['encuestado/estudios']);
  }
}