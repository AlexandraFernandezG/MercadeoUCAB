import { Injectable } from '@angular/core';
import { PreguntaEstudio, PreguntaEstudio2 } from '../modelos/pregunta_estudio';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, tap} from 'rxjs/operators';
import { CategoriasService } from './categorias.service';
import { respuestaPregunta, respuestaPregunta2 } from '../modelos/respuestaPregunta';
import { Estudio } from '../modelos/estudio';
import { Pregunta, Pregunta3, PreguntaEncuesta } from '../modelos/pregunta';
import { Respuesta2 } from '../modelos/respuesta';

@Injectable({
  providedIn: 'root'
})
export class EncuestasService {

  preguntaEstudio: PreguntaEstudio[];
  estudio: Estudio[];
  preguntas: Pregunta[];
  preguntas2: Pregunta3[];

  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getPreguntasEncuesta(id:number){
    console.log("entre1");
    return this.http.get<PreguntaEncuesta[]>(this.url + 'encuesta/preguntas/' +  id);
  }

  getRespuestasAsociadas(id:number){
    console.log("entre2");
    return this.http.get<any[]>(this.url + 'encuesta/respuestas/' +  id);
  }

  addRespuesta(respuesta:Respuesta2[]){
    console.log('entre');
    return this.http.post(this.url +'encuesta/responder' , respuesta)
    .subscribe(
      response => {
        console.log('guardar respuestas' + response);
      },
      error => console.log('Error al guardar respuestas' + error)
    );
  }


    /// Error HandleError
    handleError(error): Observable<never> {
      let errorMessage = '';
      if (error.error instanceof ErrorEvent) {
        errorMessage = error.error.message;
      } else {
        errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
      window.alert(errorMessage);
      return throwError(errorMessage);
   }
}
