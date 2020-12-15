import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { Usuario, UsuarioLDAP } from 'src/app/modelos/usuario';
import { Usuario2 } from 'src/app/modelos/usuario';
import { Rol } from 'src/app/modelos/rol';
import { Rol2 } from 'src/app/modelos/rol';
import { UsuarioComponent } from 'src/app/admin/usuario/usuario.component';



@Component({
  selector: 'app-add-usuario',
  templateUrl: './add-usuario.component.html',
  styleUrls: ['./add-usuario.component.css']
})
export class AddUsuarioComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
//    private router: Router,
//    private service: UsuariosService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<UsuarioComponent>
    ) {

    this.usuarioForm = this.fb.group({
      nombreUsuario: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      correo: new FormControl( '',[ Validators.required,  Validators.email, Validators.maxLength(100)]),
      contrasena: new FormControl ('', [Validators.required, Validators.minLength(6)])

    });
   }

  //usuario: Usuario = {_id: 1 , _nombreUsuario: '', _correo: '', _codigoRecuperacion: 1, _estatus: 'Activo', _fk_rol: 1 };
  usuario2: Usuario2 = {id: 1 , nombreUsuario: '', correo: '', codigoRecuperacion: 1, estatus: 'Activo', fk_rol: 1 };
  usuarioLDAP: UsuarioLDAP = { id: 1, nombreUsuario: '', correo: '', codigoRecuperacion: 1, estatus: 'Activo', contrasena: '',
  fk_rol: 1 }
  roles: Rol[] = [
    {_id: 1, _nombre: 'Administrador', _estatus: 'Activo'},
    {_id: 2, _nombre: 'Cliente', _estatus: 'Activo'},
    {_id: 3, _nombre: 'Encuestado', _estatus: 'Activo'},
    {_id: 4, _nombre: 'Analista', _estatus: 'Activo'}
  ];
  usuarioForm: FormGroup;
  ngOnInit(): void {
  }

  /*
  addUsuario(nombreUsuario: string, correo: string, fk_rol: number): void{
    const id = 1;
    const estatus = 'Activo';
    const codigoRecuperacion = 1;
    this.service.createUsuario({
     id,
    nombreUsuario,
    correo,
    codigoRecuperacion,
    estatus,
    fk_rol
    } as Usuario2).subscribe();
    console.log(id, nombreUsuario, correo, codigoRecuperacion, estatus, fk_rol);
    this.dialogRef.close();
  }
  */
}
