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
import { RolesService } from 'src/app/servicios/roles.service';



@Component({
  selector: 'app-add-usuario',
  templateUrl: './add-usuario.component.html',
  styleUrls: ['./add-usuario.component.css']
})
export class AddUsuarioComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: UsuariosService,
    private servicerol: RolesService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<UsuarioComponent>
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
    _token: '',
    _estatus: 'Activo',
    _rol: {
      _id: 0,
      _nombre: '',
      _estatus: 'Activo'
    }
  };


  roles: Rol[] = [
  ];
  usuarios: any;
  usuarioForm: FormGroup;
  ngOnInit(): void {
    this.service.getUsuarios()
    .subscribe(data => {this.usuarios = data;
    } );
    this.servicerol.getRoles().subscribe(rol => {this.roles = rol;
    } );
    console.log(this.usuario);
  }

  addUsuario( correo: string, nombreUsuario: string): void{
    const id = 1;
    const estatus = 'Activo';
    const token = '';
    this.service.createUsuario({
     id,
    nombreUsuario,
    correo,
    token,
    estatus,
    contrasena: this.usuarioForm.get("contrasena").value,
    rol: this.usuarioForm.get("rol").value
    } as Usuario2).subscribe();
    console.log(id, nombreUsuario, correo, token, estatus);
    this.dialogRef.close();
  }
}
