import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Estudio } from '../modelos/estudio';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstudiosService {

  url: string = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  constructor( private http: HttpClient) { }

  getEstudios():Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.url + 'estudio/allEstudio');
  }

}
