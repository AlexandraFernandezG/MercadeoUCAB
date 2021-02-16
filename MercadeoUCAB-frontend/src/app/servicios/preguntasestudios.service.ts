import { Injectable } from '@angular/core';
import { PreguntaEstudio, PreguntaEstudio2 } from '../modelos/pregunta_estudio';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, tap} from 'rxjs/operators';
import { CategoriasService } from './categorias.service';
import { respuestaPregunta2 } from '../modelos/respuestaPregunta';
import { Estudio } from '../modelos/estudio';
import { Pregunta, Pregunta3 } from '../modelos/pregunta';

@Injectable({
  providedIn: 'root'
})
export class PreguntasEstudioService {

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

  getPreguntasEstudio(id:number){
    return this.http.get<Pregunta3[]>(this.url + 'preguntasEstudio/listarPreguntasEstudio/' +  id);
  }

  getPreguntasSugeridasEstudio(id:number){
    console.log('sugerencias');
    return this.http.get<any>(this.url + 'sugerencias/preguntasEstudio/' +  id);
  }

  createPreguntaEstudio(preguntaEstudio: PreguntaEstudio2): Observable<PreguntaEstudio2>{
    console.log(preguntaEstudio);
    console.log('entre');
    return this.http.post<PreguntaEstudio2>(this.url + 'preguntasEstudio/addPreguntaEstudio', JSON.stringify(preguntaEstudio), this.httpOptions)
    .pipe(
      tap((newpregunta: PreguntaEstudio2) => console.log(`added preguntaEstudio w/ id=${newpregunta.id}`)),
      catchError(this.handleError)
    );
  }

  createPreguntaAddEstudio(preguntaEstudio: PreguntaEstudio2): Observable<PreguntaEstudio2>{
    console.log(preguntaEstudio);
    console.log('entre');
    return this.http.post<PreguntaEstudio2>(this.url + 'preguntasEstudio/addPreguntaEstudio', JSON.stringify(preguntaEstudio), this.httpOptions)
    .pipe(
      tap((newpregunta: PreguntaEstudio2) => 
        console.log(`added preguntaEstudio w/ id=${newpregunta.id}`),
        
      ),
      catchError(this.handleError)
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
