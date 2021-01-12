import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, map, retry } from 'rxjs/operators';
import { Usuario2 } from '../modelos/usuario';



@Injectable({ providedIn: 'root' })

export class AuthenticationService {

    constructor(private http: HttpClient) {
    }
    public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

    usuario: any;

login(usuario): Observable<Usuario2[]>{

  return  this.http.post<Usuario2[]>(this.url + 'login/ldap', JSON.stringify(usuario), this.httpOptions)
 // .pipe(
 //  retry(1),
  // catchError(this.handleError)
// )
}

logout() {
// remove user from local storage to log user out
 localStorage.removeItem('Usuario actual');
}

    ///////////////////// Error HandleError
handleError(error) {
  let errorMessage = '';
  if(error.error instanceof ErrorEvent) {
    // Get client-side error
    errorMessage = error.error.message;
  } else {
    // Get server-side error
    errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
  }
  window.alert(errorMessage);
  return throwError(errorMessage);
  }
}