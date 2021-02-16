import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Categoria } from '../modelos/categoria';
import { Categoria2 } from '../modelos/categoria';
import { catchError, map, tap, retry } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  categoria: Categoria[];
  constructor(public http: HttpClient) {
  }
  public url = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getCategorias(){
    return this.http.get<any>(this.url + 'categoria/allCategoria');
  }

  getCategoria(id: number): Observable<Categoria> {
    console.log(id);
    return this.http.get<Categoria>(this.url + 'categoria/consultarCategoria/' + id)
    .pipe(
      tap(_ => console.log(`fetched categoria id=${id}`)),
      catchError(this.handleError)
    );
  }

  createCategoria(categoria: Categoria2): Observable<Categoria2>{
    console.log(categoria);
    return this.http.post<Categoria2>(this.url + 'categoria/addCategoria', JSON.stringify(categoria), this.httpOptions)
    .pipe(
      tap((newCategoria: Categoria2) => console.log(`added categoria w/ id=${newCategoria.id}`)),
      catchError(this.handleError)
    );
  }

  updateCategoria(categoria): Observable<Categoria>{
    console.log(categoria);
    return this.http.put<Categoria>(this.url + 'categoria/updateCategoria/' + categoria.id, JSON.stringify(categoria), this.httpOptions)
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

