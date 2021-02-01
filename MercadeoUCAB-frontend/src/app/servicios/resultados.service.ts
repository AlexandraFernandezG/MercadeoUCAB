import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResultadosService {

  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  objeto = [];

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  getResultados(id: number) {
    return this.http.get(this.url + 'reportes/cantidadesPregunta/' + id).toPromise().then(data => {
      console.log(data);

      for (const key in data) {
      if (data.hasOwnProperty(key)) {
      this.objeto.push(data[key]);
        }
      }
    });
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
