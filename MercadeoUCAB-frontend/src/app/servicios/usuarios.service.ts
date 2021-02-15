import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Usuario, Usuario3, UsuarioCorreo } from '../modelos/usuario';
import { Usuario2 } from '../modelos/usuario';
import { catchError, map, tap, retry } from 'rxjs/operators';
import { RegistroEncuestado } from '../modelos/registro-encuestado';


@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  usuario: Usuario[];
  constructor(public http: HttpClient) {
  }
  public url = '//localhost:8080/servicio-1.0-SNAPSHOT/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getUsuarios(): Observable<any>{
    return this.http.get<Usuario[]>(this.url + 'usuario/allUsuarios');
  }

  getUsuario(id: number): Observable<Usuario> {
    console.log(id);
    return this.http.get<Usuario>(this.url + 'usuario/consultarUsuario/' + id)
    .pipe(
      tap(_ => console.log(`fetched usuario id=${id}`)),
      catchError(this.handleError)
    );
  }

  getUsuarioCorreo(correo: string): Observable<any> {
    console.log(correo);
    return this.http.get<any>(this.url + 'usuario/consultarUsuarioCorreo/' + correo)
    .pipe(
      tap(usuario => console.log(`fetched usuario id=${usuario.Usuario.id}`)),
      catchError(this.handleError)
    );
  }

  getAnalistas(){
    return this.http.get<any>(this.url + 'usuario/allAnalistas');
  }

  getEncuestados(){
    return this.http.get<any>(this.url + 'usuario/allEncuestados');
  }

  createUsuario(usuario: Usuario2): Observable<Usuario2>{
    console.log(usuario);
    return this.http.post<Usuario2>(this.url + 'usuario/addUsuario', JSON.stringify(usuario), this.httpOptions)
    .pipe(
      tap((newusuario: Usuario2) => console.log(`added usuario w/ id=${newusuario.id}`)),
      catchError(this.handleError)
    );
  }

  updateUsuario(usuario): Observable<Usuario>{
    console.log(usuario);
    return this.http.put<Usuario>(this.url + 'usuario/updateUsuario/' + usuario.id, JSON.stringify(usuario), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEncuestadosSugeridos(id: number): Observable<any>{
    console.log(id);
    return this.http.get<any>(this.url + 'estudio/solicitudEncuestados/' + id)
    .pipe(
      tap((dataObject: any)=> console.log(`fetched usuario encuestados=${dataObject.mensaje}`)),
      catchError(this.handleError)
    );
  }

  getEncuestadosEstudio(id: number): Observable<any>{
    console.log(id);
    return this.http.get<any>(this.url + 'usuarioEstudio/encuestadosEstudio/' + id)
    .pipe(
      tap((dataObject: any)=> console.log(`fetched usuario encuestados=${dataObject.Encuestados}`)),
      catchError(this.handleError)
    );
  }

  changeEstatusUsuario(usuario): Observable<Usuario>{
    console.log(usuario);
    console.log('entre');
    return this.http.put<Usuario>(this.url + 'usuario/estatusUsuario/' + usuario.id, JSON.stringify(usuario), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  enviarClave(usuario): Observable<Usuario>{
    console.log(usuario);
    console.log('entre');
    return this.http.put<Usuario>(this.url + 'usuario/recuperarClave/' +  JSON.stringify(usuario), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

    /// Error HandleError
  handleError(error): Observable<never> {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }

  getInfoUsuario(id: number): Observable<RegistroEncuestado>{
    return this.http.get<RegistroEncuestado>(this.url + 'informacion/consultarInformacion/' + id)
    .pipe(
      tap(_ => console.log(`fetched usuario id=${id}`)),
      catchError(this.handleError)
    );
  }

}

