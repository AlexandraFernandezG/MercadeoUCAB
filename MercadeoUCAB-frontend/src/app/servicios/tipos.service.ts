import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, tap, retry } from 'rxjs/operators';
import { Tipo, Tipo2 } from '../modelos/tipo';


@Injectable({
  providedIn: 'root'
})
export class TiposService {

  tipo: Tipo[];
  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getTipos(){
    return this.http.get<Tipo[]>(this.url + 'tipo/allTipo');
  }

  getTipo(id: number): Observable<Tipo> {
    console.log(id);
    return this.http.get<Tipo>(this.url + 'tipo/consultarTipo/' + id)
    .pipe(
      tap(_ => console.log(`fetched tipo id=${id}`)),
      catchError(this.handleError)
    );
  }

  createTipo(tipo: Tipo2): Observable<Tipo2>{
    console.log(tipo);
    return this.http.post<Tipo2>(this.url + 'tipo/addTipo', JSON.stringify(tipo), this.httpOptions)
    .pipe(
      tap((newtipo: Tipo2) => console.log(`added tipo w/ id=${newtipo.id}`)),
      catchError(this.handleError)
    );
  }

  updateTipo(tipo): Observable<Tipo>{
    console.log(tipo);
    return this.http.put<Tipo>(this.url + 'tipo/updateTipo/' + tipo.id, JSON.stringify(tipo), this.httpOptions)
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

