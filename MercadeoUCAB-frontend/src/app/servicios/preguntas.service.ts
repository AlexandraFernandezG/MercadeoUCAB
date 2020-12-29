import { Injectable } from '@angular/core';
import { Pregunta, Pregunta2 } from '../modelos/pregunta';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, tap} from 'rxjs/operators';
import { CategoriasService } from './categorias.service';
import { respuestaPregunta2 } from '../modelos/respuestaPregunta';

@Injectable({
  providedIn: 'root'
})
export class PreguntasService {

  pregunta: Pregunta[];
  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getPreguntas(){
    return this.http.get<Pregunta[]>(this.url + 'preguntasEncuesta/allPreguntasEncuesta');
  }

  getPregunta(id: number): Observable<Pregunta> {
    console.log(id);
    return this.http.get<Pregunta>(this.url + 'preguntasEncuesta/consultarpreguntaEncuesta/' + id)
    .pipe(
      tap(_ => console.log(`fetched pregunta id=${id}`)),
      catchError(this.handleError)
    );
  }

  createPregunta(pregunta: Pregunta2): Observable<Pregunta2>{
    console.log(pregunta);
    console.log('entre');
    return this.http.post<Pregunta2>(this.url + 'preguntasEncuesta/addPreguntaEncuesta', JSON.stringify(pregunta), this.httpOptions)
    .pipe(
      tap((newpregunta: Pregunta2) => console.log(`added pregunta w/ id=${newpregunta.id}`)),
      catchError(this.handleError)
    );
  }

  createPreguntaRespuesta(idPregunta: number, respuesta: any): Observable<any>{
   let headers = new HttpHeaders().set('Content-Type', 'application/json');
   return this.http.post(this.url + 'respuestaPregunta/addRespuestaPregunta/'+`${idPregunta}`,JSON.stringify(respuesta), this.httpOptions );
  }

  updatepregunta(pregunta): Observable<Pregunta>{
    console.log(pregunta);
    return this.http.put<Pregunta>(this.url + 'preguntasEncuesta/updatePreguntaEncuesta/' +
    pregunta.id, JSON.stringify(pregunta), this.httpOptions)
    .pipe(
      retry(1),
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
