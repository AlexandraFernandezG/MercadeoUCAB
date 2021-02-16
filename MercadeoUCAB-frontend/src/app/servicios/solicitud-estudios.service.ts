import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Solicitud } from '../modelos/solicitud';
import { Solicitud2 } from '../modelos/solicitud';
import { Observable, throwError } from 'rxjs';
import { catchError, tap, retry } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class SolicitudEstudiosService {

  url: string = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  
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

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor( private http: HttpClient) { }

  getSolicitudes():Observable<Solicitud[]>{
    return this.http.get<Solicitud[]>(this.url + 'solicitudEstudio/allSolicitudEstudio');
  }

  getSolicitudesCliente(id: number):Observable<Solicitud[]>{
    return this.http.get<Solicitud[]>(this.url + 'solicitudEstudio/mostrarSolicitudesCliente/' + id);
  }

  createSolicitud(solicitud: Solicitud2): Observable<any>{
    console.log(solicitud);
    return this.http.post<Solicitud2>(this.url + 'solicitudEstudio/addSolicitudEstudio', JSON.stringify(solicitud), this.httpOptions).
    pipe(
      tap((newSolicitudEstudio: Solicitud2) => console.log(`added solicitud w/ id=${newSolicitudEstudio.estado}`)),
      catchError(this.handleError)
    );
  }

  getSolicitud(id: number): Observable<Solicitud> {
    console.log(id);
    return this.http.get<Solicitud>(this.url + 'solicitudEstudio/consultarSolicitudEstudio/' + id)
    .pipe(
      tap(_ => console.log(`fetched categoria id=${id}`)),
      catchError(this.handleError)
    );
  }

  updateSolicitud(solicitud): Observable<Solicitud>{
    console.log(solicitud);
    return this.http.put<Solicitud>(this.url + 'solicitudEstudio/updateSolicitudEstudio/' + solicitud.id, JSON.stringify(solicitud), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }
  
}
