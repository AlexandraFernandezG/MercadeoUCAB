import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
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
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';



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
  displayedColumns: string[] = ['nombre', 'correo', 'acciones'];
  dataSource: MatTableDataSource<Usuario>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.service.getUsuarios()
    .subscribe(data => {
      this.dataSource = new MatTableDataSource<Usuario>(data.Usuarios);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
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

  deleteUsuario(usuario: Usuario): void{
    console.log('segundo');
    const deleteU: Usuario2 = {
     id: usuario._id,
     nombreUsuario: usuario._nombre,
     correo: usuario._correoelectronico,
     codigoRecuperacion: usuario._codigoRecuperacion,
     estatus: usuario._estatus,
     contrasena: '',
     rol: usuario._rol._id
     };
    this.service.changeEstatusUsuario(deleteU).subscribe();
       }
}
