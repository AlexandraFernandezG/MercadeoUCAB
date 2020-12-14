import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Marca } from '../modelos/marca';
import { Marca2 } from '../modelos/marca';
import { catchError, map, tap, retry } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class MarcasService {

  marca: Marca[];
  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getMarcas(){
    return this.http.get<Marca[]>(this.url + 'marca/allMarca');
  }

  getMarca(id: number): Observable<Marca> {
    console.log(id);
    return this.http.get<Marca>(this.url + 'marca/consultarMarca/' + id)
    .pipe(
      tap(_ => console.log(`fetched marca id=${id}`)),
      catchError(this.handleError)
    );
  }

  createMarca(marca: Marca2): Observable<Marca2>{
    console.log(marca);
    return this.http.post<Marca2>(this.url + 'marca/addMarca', JSON.stringify(marca), this.httpOptions)
    .pipe(
      tap((newmarca: Marca2) => console.log(`added marca w/ id=${newmarca.id}`)),
      catchError(this.handleError)
    );
  }

  updateMarca(marca: Marca2): Observable<Marca2>{
    return this.http.put<Marca2>(this.url + 'marca/updateMarca/' + marca.id, JSON.stringify(marca), this.httpOptions)
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

