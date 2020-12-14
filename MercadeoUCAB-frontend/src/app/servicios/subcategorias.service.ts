import { Injectable } from '@angular/core';
import { Subcategoria, Subcategoria2 } from '../modelos/subcategoria';
import { Categoria } from '../modelos/categoria';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriasService {

  categoria: Categoria[];
  subcategoria: Subcategoria[];
  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getSubcategorias(){
    return this.http.get<Subcategoria[]>(this.url + 'subcategoria/allSubcategoria');
  }

  createSubcategoria(subcategoria: Subcategoria2): Observable<Subcategoria2>{
    console.log(subcategoria);
    return this.http.post<Subcategoria2>(this.url + 'subcategoria/addCategoria', JSON.stringify(subcategoria), this.httpOptions)
    .pipe(
      tap((newSubcategoria: Subcategoria2) => console.log(`added categoria w/ id=${newSubcategoria.id}`)),
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
