import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Categoria } from '../modelos/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

  categoria: Categoria[];
  constructor(private http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  getCategorias() {
    return this.http.get<Categoria[]>(this.url + 'categoria/allCategoria');
  }


}


