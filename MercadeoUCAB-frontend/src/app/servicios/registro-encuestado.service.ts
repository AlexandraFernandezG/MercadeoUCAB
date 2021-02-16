import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { RegistroEncuestado, RegistroEncuestado2 } from '../modelos/registro-encuestado';

@Injectable({
  providedIn: 'root'
})
export class RegistroEncuestadoService {

  url: string = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';
  
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

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor( private http: HttpClient) { }

  getInformacion(id: number):Observable<RegistroEncuestado[]>{
    return this.http.get<RegistroEncuestado[]>(this.url + 'informacion/consultarInformacion' + id);
  }

  createRegistro(informacion: RegistroEncuestado2): Observable<RegistroEncuestado2>{
    console.log(informacion);
    return this.http.post<RegistroEncuestado2>(this.url + 'informacion/addInformacion', JSON.stringify(informacion), this.httpOptions).
    pipe(
      tap((newInformacion: RegistroEncuestado2) => 
      console.log(`added informacion w/ id=${newInformacion.id}`)),
      catchError(this.handleError)
    );
  }
}
