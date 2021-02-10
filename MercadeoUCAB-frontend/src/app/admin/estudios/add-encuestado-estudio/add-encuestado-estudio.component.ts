import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Usuario3 } from 'src/app/modelos/usuario';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { PreguntasEstudioComponent } from '../preguntas-estudio/preguntas-estudio.component';

@Component({
  selector: 'app-add-encuestado-estudio',
  templateUrl: './add-encuestado-estudio.component.html',
  styleUrls: ['./add-encuestado-estudio.component.css']
})
export class AddEncuestadoEstudioComponent implements OnInit {

  constructor(
    public serviceEncuestados: UsuariosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<PreguntasEstudioComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }
  
  encuestados: Usuario3[];
  encuestadosAgregados: Usuario3[] = [];
  preguntaForm: FormGroup;
  idestudio: number;

  displayedColumns: string[] = ['nombre','correo', 'acciones'];
  dataSource: MatTableDataSource<Usuario3>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.dialogRef.updateSize('650px', '450px')
    console.log('id:', this.data.idSolicitud);
    this.serviceEncuestados.getEncuestados()
    .subscribe(data => {
      console.log(data.Usuarios)
      this.dataSource = new MatTableDataSource<Usuario3>(data.Usuarios);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );
  }

  Agregar(encuestado: Usuario3) {
    
    console.log('antes:', this.encuestadosAgregados)
    this.encuestadosAgregados = JSON.parse(localStorage.getItem('encuestados'));
    console.log('traer: ',this.encuestadosAgregados);
    this.encuestadosAgregados.push(encuestado);
    console.log('agregar: ', this.encuestadosAgregados);
    localStorage.setItem('encuestados',  JSON.stringify (this.encuestadosAgregados))
    return this.dialogRef.close();
  }

}
