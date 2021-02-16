import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Presentacion } from '../modelos/presentacion';
import { Presentacion2 } from '../modelos/presentacion';
import { catchError, map, tap, retry } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class PresentacionesService {

  presentacion: Presentacion[];
  constructor(public http: HttpClient) {
  }
  public url = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getPresentaciones(){
    return this.http.get<Presentacion[]>(this.url + 'presentacion/allPresentacion');
  }

  getPresentacion(id: number): Observable<Presentacion> {
    console.log(id);
    return this.http.get<Presentacion>(this.url + 'presentacion/consultarPresentacion/' + id)
    .pipe(
      tap(_ => console.log(`fetched presentacion id=${id}`)),
      catchError(this.handleError)
    );
  }

  createPresentacion(presentacion: Presentacion2): Observable<Presentacion2>{
    console.log(presentacion);
    return this.http.post<Presentacion2>(this.url + 'presentacion/addPresentacion', JSON.stringify(presentacion), this.httpOptions)
    .pipe(
      tap((newpresentacion: Presentacion2) => console.log(`added presentacion w/ id=${newpresentacion.id}`)),
      catchError(this.handleError)
    );
  }

  updatePresentacion(presentacion): Observable<Presentacion>{
    console.log(presentacion);
    return this.http.put<Presentacion>(this.url + 'presentacion/updatePresentacion/' + presentacion.id, JSON.stringify(presentacion), this.httpOptions)
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

