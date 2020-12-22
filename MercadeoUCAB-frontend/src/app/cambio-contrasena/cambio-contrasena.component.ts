import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { Usuario } from 'src/app/modelos/usuario';
import { Usuario2 } from 'src/app/modelos/usuario';
import { Rol } from '../modelos/rol';


@Component({
  selector: 'app-cambio-contrasena',
  templateUrl: './cambio-contrasena.component.html',
  styleUrls: ['./cambio-contrasena.component.css']
})
export class CambioContrasenaComponent implements OnInit {
  cambioContrasenaForm: FormGroup;
  error: {};

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: UsuariosService,
    public actRoute: ActivatedRoute
  ) {

  this.usuarioForm = this.fb.group({
    contrasena: new FormControl ('', [Validators.required, Validators.minLength(6)]),
    contrasena2: new FormControl ('', [Validators.required, Validators.minLength(6)])

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
usuarioForm: FormGroup;

  ngOnInit(): void {
    this.cambioContrasenaForm = new FormGroup(
      {
        contrasena: new FormControl('', [Validators.required, Validators.minLength(6)]),
        contrasena2: new FormControl('', [Validators.required, Validators.minLength(6)])
      }
    );
  }

  cambiarPassword( usuario: Usuario): void {
    if (!this.cambioContrasenaForm.valid) {
      alert('Formulario inválido');
    }
    // Verifica que la contraseña y la confirmación de la contraseña sean iguales.
    if (this.cambioContrasenaForm.value.contrasena2 != this.cambioContrasenaForm.value.contrasena) {
      alert('Las contraseñas no coinciden');
    }
	   const editPa: Usuario2 = {
    id: this.usuario._id,
    nombreUsuario: this.usuario._nombre,
    correo: this.usuario._correoelectronico,
    codigoRecuperacion: '',
    contrasena: this.usuarioForm.get('contrasena2').value,
    estatus: 'Activo',
    rol: usuario._rol._id
  };
    this.service.updateUsuario(editPa).subscribe();

  }
}
