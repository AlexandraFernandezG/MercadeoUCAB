import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../modelos/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  url: string = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  

  constructor( private http: HttpClient) { }

  getLugares():Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.url + "usuario/allUsuario");
  }
}
