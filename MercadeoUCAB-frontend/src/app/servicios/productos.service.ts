import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../modelos/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  url: string = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';
  

  constructor( private http: HttpClient) { }

  getProductos():Observable<Producto[]>{
    return this.http.get<Producto[]>(this.url + 'producto/allProductos');
  }

}

