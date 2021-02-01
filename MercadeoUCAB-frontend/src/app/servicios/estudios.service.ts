import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Estudio, Estudio2 } from '../modelos/estudio';
import { Observable, throwError } from 'rxjs';
import { tap, retry, catchError } from 'rxjs/operators';
import { Usuario3 } from '../modelos/usuario';
import { Pregunta3 } from '../modelos/pregunta';

@Injectable({
  providedIn: 'root'
})
export class EstudiosService {

  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  constructor( private http: HttpClient) { }
  
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  createEstudio(estudio: Estudio2, encuestados: Usuario3[], preguntas: Pregunta3[]):Observable<any>{
    console.log(JSON.stringify(estudio))
    console.log(JSON.stringify(encuestados))
    console.log(JSON.stringify(preguntas))
    var body = {
      estudioDto: JSON.stringify(estudio),
      listaEncuestados: JSON.stringify(encuestados),
      listaPreguntas: JSON.stringify(preguntas)}
    return this.http.post<any>(this.url + 'estudio/addEstudio', body, this.httpOptions).
    pipe(
      tap((newEstudio: Estudio2) => console.log(`added estudio w/ id=${newEstudio.id}`)),
      catchError(this.handleError)
    );
  }

  addEstudioSugerido(idSugerido: number, idEstudio: number):Observable<any>{
    return this.http.post(this.url + 'sugerencias/insertarEstudioRecomendado/' + idSugerido + '/' + idEstudio,
    null);
  }

  getEstudios():Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'estudio/allEstudio');
  }

  getEstudiosEncuestado(id: number):Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'sugerencias/estudiosEncuestado/' + id);
  }

  getEstudiosCliente(id: number):Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'sugerencias/estudiosCliente/' + id);
  }

  getEstudiosAnalista(id: number):Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'sugerencias/estudiosAnalista/' + id);
  }

  getEstudiosSugeridos(id:number):Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'sugerencias/solicitudEstudio/' + id);
  }

  getEstudio(id: number): Observable<Estudio> {
    console.log(id);
    return this.http.get<Estudio>(this.url + 'categoria/consultarCategoria/' + id)
    .pipe(
      tap(_ => console.log(`fetched estudio id=${id}`)),
      catchError(this.handleError)
    );
  }

  consultarEstudio(id: number): Observable<Estudio> {
    console.log(id);
    return this.http.get<Estudio>(this.url + 'estudio/consultarEstudio/' + id)
    .pipe(
      tap(_ => console.log(`fetched estudio id=${id}`)),
      catchError(this.handleError)
    );
  }

  updateEstudio(estudio): Observable<Estudio>{
    console.log(estudio);
    return this.http.put<Estudio>(this.url + 'estudio/updateEstudio/' + estudio.id, JSON.stringify(estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }
  updateEstudio2(id): Observable<Estudio>{
    console.log(id);
    return this.http.put<Estudio>(this.url + 'estudio/updateEstadoEstudio/' + id, JSON.stringify(id), this.httpOptions)
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
