import { Component, Inject, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { UsuarioComponent } from 'src/app/admin/usuario/usuario.component';
import { Usuario } from 'src/app/modelos/usuario';
import { Usuario2 } from 'src/app/modelos/usuario';
import { Rol } from 'src/app/modelos/rol';
import { RolesService } from 'src/app/servicios/roles.service';

@Component({
  selector: 'app-edit-usuario',
  templateUrl: './edit-usuario.component.html',
  styleUrls: ['./edit-usuario.component.css']
})
export class EditUsuarioComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: UsuariosService,
    private serviceRoles: RolesService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<UsuarioComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Usuario2,
    ) {

      this.usuarioForm = this.fb.group({
        nombreUsuario: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
        correo: new FormControl( '',[ Validators.required,  Validators.email, Validators.maxLength(100)]),
        contrasena: new FormControl ('', [Validators.required, Validators.minLength(6)]),
        rol: new FormControl('',[Validators.required])
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
    this.getId();
    this.service.getUsuarios()
    .subscribe(data => {this.usuarios = data;
    } );
    this.serviceRoles.getRoles()
    .subscribe(rol => {this.roles = rol;
    } );
      }

  getId(){
    console.log('primero', this.usuario);
    const id = this.data.id;
    this.service.getUsuario(id).subscribe(data => {this.usuario = data;
    });
  }


  editUsuario(): void{
   console.log('segundo');
   const editU: Usuario2 = {
    id: this.data.id,
    nombreUsuario: this.usuarioForm.get("nombreUsuario").value,
    correo: this.usuarioForm.get("correo").value,
    codigoRecuperacion: '',
    estatus: this.usuario._estatus,
    contrasena: this.usuarioForm.get("contrasena").value,
    rol: this.usuarioForm.get("rol").value
    };
   this.service.updateUsuario(editU).subscribe();

   this.dialogRef.close();
      }

}

