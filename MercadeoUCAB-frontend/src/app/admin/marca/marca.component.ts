import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { MarcasService } from 'src/app/servicios/marcas.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddMarcaComponent } from './add-marca/add-marca.component';
import { EditMarcaComponent } from './edit-marca/edit-marca.component';
import { Marca, Marca2 } from 'src/app/modelos/marca';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';




@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {

  marcas: Marca[];


  @Input() marcaData: any = {};
  constructor(
    private service: MarcasService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  marcaForm: FormGroup;
  displayedColumns: string[] = ['nombre', 'descripcion','estatus', 'acciones'];
  dataSource: MatTableDataSource<Marca>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.service.getMarcas()
    .subscribe(data => {
      this.dataSource = new MatTableDataSource<Marca>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );

  }
  openModal(){
    this.dialog.open(AddMarcaComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditMarcaComponent,
      {
        data: {id: id}
      }
    );
  }

  deleteMarca( marca: Marca): void{
    console.log('segundo', marca);
    const deleteMa: Marca2 = {
      id: marca._id,
      nombre: marca._nombre,
      descripcion: marca._descripcion,
      estatus: 'Inactivo'
    };
    this.service.updateMarca(deleteMa).subscribe();
      }
}
