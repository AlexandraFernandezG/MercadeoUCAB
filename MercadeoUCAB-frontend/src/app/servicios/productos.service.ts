import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, tap, retry } from 'rxjs/operators';
import { Producto, Producto2 } from '../modelos/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  producto: Producto[];
  constructor(public http: HttpClient) {
  }
  public url = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };


  getProductos():Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + 'producto/allProductos');
  }

  createProducto(producto: Producto2): Observable<Producto2>{
    console.log(producto);
    return this.http.post<Producto2>(this.url + 'producto/addProducto', JSON.stringify(producto), this.httpOptions)
    .pipe(
      tap((newproducto: Producto2) => console.log(`added producto w/ id=${newproducto.id}`)),
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

