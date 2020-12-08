import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoriasService {

   public API = '//localhost:8080/servicio-1.0-SNAPSHOT/api';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.API + 'categoria/allCategoria');
  }

  get(id: string) {
    return this.http.get(this.API + 'categoria/consultarCategoria/' + id);
  }


}


