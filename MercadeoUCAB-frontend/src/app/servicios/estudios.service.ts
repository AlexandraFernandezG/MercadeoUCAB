import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Estudio } from '../modelos/estudio';
import { Observable, throwError } from 'rxjs';
import { tap, retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EstudiosService {

  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  constructor( private http: HttpClient) { }
  
  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };


  getEstudios():Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'estudio/allEstudio');
  }

  getEstudio(id: number): Observable<Estudio> {
    console.log(id);
    return this.http.get<Estudio>(this.url + 'categoria/consultarCategoria/' + id)
    .pipe(
      tap(_ => console.log(`fetched estudio id=${id}`)),
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
