import { Component, OnInit, Input, Inject } from '@angular/core';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddUsuarioComponent } from './add-usuario/add-usuario.component';
import { EditUsuarioComponent } from './edit-usuario/edit-usuario.component';
import { Usuario, Usuario2 } from 'src/app/modelos/usuario';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Rol } from 'src/app/modelos/rol';
import { RolesService } from 'src/app/servicios/roles.service';



@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  usuarios: Usuario[];
  roles: Rol[];


  @Input() usuarioData: any = {};
  constructor(
    private service: UsuariosService,
    private servicerol: RolesService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  usuarioForm: FormGroup;
  ngOnInit() {
    this.service.getUsuarios()
    .subscribe(data => {this.usuarios = data;
    } );
    this.servicerol.getRoles().subscribe(rol => {this.roles = rol;
    } );

  }
  openModal(){
    this.dialog.open(AddUsuarioComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditUsuarioComponent,
      {
        data: {id: id}
      }
    );
  }

  /*
  deleteUsuario( usuario: Usuario): void{
    console.log('segundo', usuario);
    const deleteUs: Usuario2 = {
       id: usuario._id,
       nombreUsuario: usuario._nombreUsuario,
       correo: usuario._correo,
       codigoRecuperacion: usuario._codigoRecuperacion,
       estatus: 'Inactivo',
      // rol: usuario._rol
    };
    this.service.updateUsuario(deleteUs).subscribe();
      }*/
}
