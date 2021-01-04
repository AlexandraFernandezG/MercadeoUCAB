import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AuthenticationService } from 'src/app/servicios/auth.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  returnUrl: string;
  error: {};
  loginError: string;
  user: any;
  @Input() usuario = {correo: '', contrasena: '' };
  constructor(
    private service: AuthenticationService,
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

  onLogin() {
  if (this.loginForm.valid) {
      console.log('usuario:', this.usuario);
      this.service.login(this.usuario).subscribe( data  => {
        this.user = data;
        console.log('Data:',data);
        if (this.user.estado === 'success') {
          
          console.log('usuario:',this.user) ;
          console.log('Rol: ',this.user.rol) ;
          if (this.user.rol === 'admin'){
            console.log ('Soy un administrador');
            this.router.navigate(['/admin/categorias']);
           localStorage.setItem('usuarioID', JSON.stringify(this.user._id));
            localStorage.setItem('rol', JSON.stringify(this.user._fk_rol));
          }
          if (this.user.rol === 'Encuestado'){
            console.log('Soy un Encuestado');
            // Asignar ruta para el encuestado
            this.router.navigate(['encuestado/']);
            localStorage.setItem('usuarioID', JSON.stringify(this.user._id));
            localStorage.setItem('rol', JSON.stringify(this.user.rol));
          }
          if (this.user.rol === 'Analista'){
            console.log('Soy un  Analista');
            // Asignar ruta para el analista
            this.router.navigate(['/analista/']);
            localStorage.setItem('usuarioID', JSON.stringify(this.user._id));
            localStorage.setItem('rol', JSON.stringify(this.user.rol));
          }
          if (this.user.rol === 'Cliente'){
            console.log('Soy un Cliente');
            this.router.navigate(['/cliente/estudios']);
            localStorage.setItem('usuarioID', JSON.stringify(this.user._id));
            localStorage.setItem('rol', JSON.stringify(this.user.rol));
          }
        }else {
          window.alert('Usuario no registrado o Informacion Incorrecta');
        }
      });

}
}
  }
