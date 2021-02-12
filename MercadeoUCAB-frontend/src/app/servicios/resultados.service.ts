import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators/catchError';
import { tap } from 'rxjs/internal/operators/tap';
import { pipe } from 'rxjs/internal/util/pipe';

@Injectable({
  providedIn: 'root'
})
export class ResultadosService {

  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  objeto = [];

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  getResultados(id: number): Observable<any[]> {
    return this.http.get<any>(this.url + 'reportes/cantidadesPregunta/' + id);
  }
/*
  sendResultados(respuesta: string, id: number):Observable<any> {
    return this.http.post<any>(this.url + 'preguntasEncuesta/addPreguntaEncuesta'+id)
    .pipe(
      tap((newrespuesta: any) => console.log(`added respuesta w/ id=${newrespuesta.id}`)),
      catchError(this.handleError)
    );
  }
*/
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
