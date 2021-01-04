import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Estudio } from '../modelos/estudio';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstudiosService {

  estudiosUrl: string = 'https://jsonplaceholder.typicode.com/albums';
  limiteEstudios = '?_limit=5';

  constructor( private http: HttpClient) { }

  getEstudios():Observable<Estudio[]>{
    return this.http.get<Estudio[]>(`${this.estudiosUrl}${this.limiteEstudios}`);
  }

}
