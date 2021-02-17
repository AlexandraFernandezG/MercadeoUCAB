import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotificationsService } from 'angular2-notifications';
import { Rol } from '../modelos/rol';
import { Usuario, Usuario2 } from '../modelos/usuario';
import { UsuariosService } from '../servicios/usuarios.service';

@Component({
  selector: 'app-nuevoencuestado',
  templateUrl: './nuevoencuestado.component.html',
  styleUrls: ['./nuevoencuestado.component.css']
})
export class NuevoencuestadoComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: UsuariosService,
    public actRoute: ActivatedRoute,
    private servicenotifications: NotificationsService
  ) {

  this.envioForm = this.fb.group({
    correo: new FormControl ('', [Validators.required, Validators.minLength(6)])

  });
 }

usuario: Usuario = {
  _id: 1 ,
  _nombre: '',
  _correoelectronico: '',
  _codigoRecuperacion: '',
  _estatus: 'Activo',
  _rol: {
    _id: 0,
    _nombre: '',
    _estatus: ''
  }
};

roles: Rol[] = [
];
usuarios: any;
envioForm: FormGroup;

  ngOnInit(): void {
    this.envioForm = new FormGroup(
      {
        nombreUsuario: new FormControl('', [Validators.required, Validators.minLength(6)]),
        correo: new FormControl('', [Validators.required, Validators.minLength(6)]),
        contrasena: new FormControl('', [Validators.required, Validators.minLength(6)])
      }
    );
  }

  onSucess(message){
    this.servicenotifications.success('Exito', message, {
      position: ['bottom', 'right'],
      timeOut: 2000,
      animate: 'fade',
      showProgressBar: true,
      })
  }

  registrarUsuario(): void {
   
	 const envioPa: Usuario2 = {
    id: this.usuario._id,
    nombreUsuario: this.envioForm.get('nombreUsuario').value,
    correo: this.envioForm.get('correo').value,
    codigoRecuperacion: '',
    contrasena: this.envioForm.get('contrasena').value,
    estatus: 'Activo',
    rol: 4
  };
    this.service.createUsuario(envioPa).subscribe();
    this.onSucess('Creado el usuario exitosamente. Por favor llene los siguientes datos...');
    setTimeout(() => {
      this.router.navigate(['/registro-encuestado']);
    },2000);

  }

  atras(){
    this.router.navigate(['/login']);
  }
}
