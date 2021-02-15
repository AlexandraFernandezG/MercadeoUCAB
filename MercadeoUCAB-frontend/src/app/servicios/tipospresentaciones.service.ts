import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tipo } from '../modelos/tipo';
import { TipoPresentacion } from '../modelos/tipopresentacion';

@Injectable({
  providedIn: 'root'
})
export class TiposPresentacionService {

  tipo: TipoPresentacion[];
  constructor(public http: HttpClient) {
  }
  public url = '//45.76.60.252:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getTiposPrentaciones(){
    return this.http.get<Tipo[]>(this.url + 'tipo/allTipo');
  }
}
