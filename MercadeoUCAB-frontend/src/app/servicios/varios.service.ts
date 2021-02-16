import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NivelAcademico, NivelEconomico } from '../modelos/varios';

@Injectable({
  providedIn: 'root'
})
export class VariosService {

  url: string = '//45.76.60.252:8080/pruebaORM-1.0-SNAPSHOT/api/';
  

  constructor( private http: HttpClient) { }

  getNivelAcademico():Observable<NivelAcademico[]>{
    return this.http.get<NivelAcademico[]>(this.url + "nivelAcademico/allNivelAcademico");
  }
  getNivelEconomico():Observable<NivelEconomico[]>{
    return this.http.get<NivelEconomico[]>(this.url + "nivelEconomico/allNivelEconomico");
  }
}
