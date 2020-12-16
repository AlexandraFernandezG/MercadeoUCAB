import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Solicitud } from '../modelos/solicitud';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SolicitudEstudiosService {

  url: string = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  

  constructor( private http: HttpClient) { }

  getSolicitudes():Observable<Solicitud[]>{
    return this.http.get<Solicitud[]>(this.url + 'solicitudEstudio/allSolicitudEstudio');
  }
  
}
