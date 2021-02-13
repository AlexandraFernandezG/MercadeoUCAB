import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators/catchError';
import { tap } from 'rxjs/internal/operators/tap';
import { pipe } from 'rxjs/internal/util/pipe';
import { retry } from 'rxjs/operators';
import { Estudio, Estudio2 } from '../modelos/estudio';

@Injectable({
  providedIn: 'root'
})
export class ResultadosService {

  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  objeto = [];
  estudio: Estudio2[];


  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  getResultados(id: number): Observable<any[]> {
    return this.http.get<any>(this.url + 'reportes/cantidadesPregunta/' + id);
  }

  sendResultados(estudio): Observable<Estudio2>{
    console.log('estoy dentro del serv');
    console.log(estudio);
    return this.http.put<Estudio2>(this.url + 'reportes/agregarObservacion/' + estudio.id, JSON.stringify(estudio), this.httpOptions)
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
