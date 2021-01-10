import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { PresentacionesService } from 'src/app/servicios/presentaciones.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddPresentacionComponent } from './add-presentacion/add-presentacion.component';
import { EditPresentacionComponent } from './edit-presentacion/edit-presentacion.component';
import { Presentacion, Presentacion2 } from 'src/app/modelos/presentacion';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';



@Component({
  selector: 'app-presentacion',
  templateUrl: './presentacion.component.html',
  styleUrls: ['./presentacion.component.css']
})
export class PresentacionComponent implements OnInit {

  presentaciones: Presentacion[];


  @Input() presentacionData: any = {};
  constructor(
    private service: PresentacionesService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  presentacionForm: FormGroup;
  displayedColumns: string[] = ['nombre', 'descripcion','estatus', 'acciones'];
  dataSource: MatTableDataSource<Presentacion>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.service.getPresentaciones()
    .subscribe(data => {
      this.dataSource = new MatTableDataSource<Presentacion>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );

  }
  openModal(){
    this.dialog.open(AddPresentacionComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditPresentacionComponent,
      {
        data: {id: id}
      }
    );
  }

  deletePresentacion( presentacion: Presentacion): void{
    console.log('segundo', presentacion);
    const deletePre: Presentacion2 = {
      id: presentacion._id,
      nombre: presentacion._nombre,
      descripcion: presentacion._descripcion,
      estatus: 'Inactivo'
    };
    this.service.updatePresentacion(deletePre).subscribe();
      }
}
