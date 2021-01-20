import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AuthenticationService } from 'src/app/servicios/auth.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UsuariosService } from '../servicios/usuarios.service';
import { Usuario, UsuarioCorreo } from '../modelos/usuario';
import { useAnimation } from '@angular/animations';
import { NotificationsService } from 'angular2-notifications'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  usuarioLog: UsuarioCorreo;
  submitted = false;
  returnUrl: string;
  error: {};
  loginError: string;
  user: any;
  @Input() usuario = {correo: '', contrasena: '' };
  constructor(
    private service: AuthenticationService,
    private serviceUsuario: UsuariosService,
    private servicenotifications: NotificationsService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
   // private location: Location

  ) { }

  ngOnInit() {
    this.loginForm = new FormGroup(
      {
        correo: new FormControl('', [Validators.required, Validators.email]),
        contrasena: new FormControl('', [Validators.required, Validators.minLength(6)])
      }
    );
   // this.logout();
  }

  onSucess(message){
    this.servicenotifications.success('Exitoso', message, {
      position: ['bottom', 'right'],
      timeOut: 5000,
      animate: 'fade',
      showProgressBar: true,
      })
  }

  onError(message){
    this.servicenotifications.error('¡Algo falló!', message, {
      position: ['bottom', 'right'],
      timeOut: 5000,
      animate: 'fade',
      showProgressBar: true,
      });
  }

  onLogin() {
  if (this.loginForm.valid) {
      console.log('entre1');
      this.service.login(this.usuario).subscribe( data  => {
        this.user = data;
        if (this.user.estado === 'success') {
          this.serviceUsuario.getUsuarioCorreo(this.usuario.correo)
            .subscribe( userData => {this.usuarioLog = userData;
              localStorage.setItem('usuarioID', JSON.stringify(this.usuarioLog[0].id));
              localStorage.setItem('rol',  JSON.stringify (this.user.rol));
              localStorage.setItem('nombre', JSON.stringify(this.usuarioLog[0].nombre));
  
            } );
          if (this.user.rol === 'Administrador'){
            console.log ('Soy un administrador');
            this.onSucess('Inicio sesión exitoso');
            setTimeout(() => {
              this.router.navigate(['/admin']);
            },2000);
          }
          if (this.user.rol === 'Encuestado'){
            console.log('Soy un Encuestado');
            // Asignar ruta para el encuestado
            this.onSucess('Inicio sesión exitoso');
            setTimeout(() => {
              this.router.navigate(['encuestado/']);
            },2000);
          }
          if (this.user.rol === 'Analista'){
            console.log('Soy un  Analista');
            // Asignar ruta para el analista
            this.onSucess('Inicio sesión exitoso');
            setTimeout(() => {
              this.router.navigate(['/analista/']);
            },2000);
          }
          if (this.user.rol === 'Cliente'){
            console.log('Soy un Cliente');
            this.onSucess('Inicio sesión exitoso');
            setTimeout(() => {
              this.router.navigate(['/cliente']);
            },2000);
          }

        }else {
         // window.alert('Usuario no registrado o Informacion Incorrecta');
        }
      });

}
}
  }
