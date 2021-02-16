import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Rol } from '../modelos/rol';
import { Rol2 } from '../modelos/rol';
import { catchError, map, tap, retry } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RolesService {

  rol: Rol[];
  constructor(public http: HttpClient) {
  }
  public url = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getRoles(){
    return this.http.get<Rol[]>(this.url + 'rol/allRoles');
  }

  getRol(id: number): Observable<Rol> {
    console.log(id);
    return this.http.get<Rol>(this.url + 'rol/consultarRol/' + id)
    .pipe(
      tap(_ => console.log(`fetched rol id=${id}`)),
      catchError(this.handleError)
    );
  }

  createRol(rol: Rol2): Observable<Rol2>{
    console.log(rol);
    return this.http.post<Rol2>(this.url + 'rol/addRol', JSON.stringify(rol), this.httpOptions)
    .pipe(
      tap((newrol: Rol2) => console.log(`added rol w/ id=${newrol.id}`)),
      catchError(this.handleError)
    );
  }

  updateRol(rol): Observable<Rol>{
    console.log(rol);
    return this.http.put<Rol>(this.url + 'rol/updateRol/' + rol.id, JSON.stringify(rol), this.httpOptions)
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

