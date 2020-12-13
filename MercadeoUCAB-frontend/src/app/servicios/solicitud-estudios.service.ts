import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estudio } from '../modelos/estudio';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SolicitudEstudiosService {

  estudiosUrl: string = 'https://jsonplaceholder.typicode.com/albums';
  

  constructor( private http: HttpClient) { }

  getEstudios():Observable<Estudio[]>{
    return this.http.get<Estudio[]>(`${this.estudiosUrl}`);
  }
}
