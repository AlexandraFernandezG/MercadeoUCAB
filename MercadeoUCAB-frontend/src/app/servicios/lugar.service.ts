import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lugar } from '../modelos/lugar';

@Injectable({
  providedIn: 'root'
})
export class LugarService {

  url: string = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';
  

  constructor( private http: HttpClient) { }

  getLugares():Observable<Lugar[]>{
    return this.http.get<Lugar[]>(this.url + "lugar/allLugares");
  }
}
