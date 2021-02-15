import { Injectable } from '@angular/core';
import { Subcategoria, Subcategoria2 } from '../modelos/subcategoria';
import { Categoria } from '../modelos/categoria';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';
import { CategoriasService } from './categorias.service';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriasService {

  categoria: Categoria[];
  subcategoria: Subcategoria[];
  constructor(public http: HttpClient) {
  }
  public url = '//45.76.60.252:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getSubcategorias(){
    return this.http.get<any>(this.url + 'subcategoria/allSubcategoria');
  }

  getSubcategoria(id: number): Observable<Subcategoria> {
    console.log(id);
    return this.http.get<Subcategoria>(this.url + 'subcategoria/consultarSubCategoria/' + id)
    .pipe(
      tap(_ => console.log(`fetched subcategoria id=${id}`)),
      catchError(this.handleError)
    );
  }

  createSubcategoria(subcategoria: Subcategoria2): Observable<Subcategoria2>{
    console.log(subcategoria);
    console.log('entre');
    return this.http.post<Subcategoria2>(this.url + 'subcategoria/addSubCategoria', JSON.stringify(subcategoria), this.httpOptions)
    .pipe(
      tap((newSubcategoria: Subcategoria2) => console.log(`added subcategoria w/ id=${newSubcategoria.id}`)),
      catchError(this.handleError) 
    );
  }

  updateSubcategoria(subcategoria): Observable<Subcategoria>{
    console.log(subcategoria);
    return this.http.put<Subcategoria>(this.url + 'subcategoria/updateSubCategoria/' +
    subcategoria.id, JSON.stringify(subcategoria), this.httpOptions)
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
