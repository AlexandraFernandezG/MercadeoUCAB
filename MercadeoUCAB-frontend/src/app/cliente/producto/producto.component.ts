import { Component, OnInit, ViewChild } from '@angular/core';
import { Routes, RouterModule, ActivatedRoute } from '@angular/router';
import { Producto, Producto2 } from 'src/app/modelos/producto';
import { ProductosService } from 'src/app/servicios/productos.service';
import { PresentacionesService } from 'src/app/servicios/presentaciones.service';
import { TiposService } from 'src/app/servicios/tipos.service';
import { MarcasService } from 'src/app/servicios/marcas.service';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { Subcategoria, Subcategoria2 } from 'src/app/modelos/subcategoria';
import { Presentacion, Presentacion2 } from 'src/app/modelos/presentacion';
import { ProductoTipoPresentacion, ProductoTipoPresentacion2 } from 'src/app/modelos/producto';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { TiposPresentacionService } from 'src/app/servicios/tipospresentaciones.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Marca, Marca2 } from 'src/app/modelos/marca';
import { Tipo, Tipo2 } from 'src/app/modelos/tipo';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AddProductoComponent } from './add-producto/add-producto.component';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {

  // Form
  productoForm: FormGroup;
  productoFormTP: any;
  displayedColumns: string[] = ['nombre', 'descripcion', 'subcategoria', 'marca', 'estatus', 'acciones'];
  dataSource: MatTableDataSource<Producto>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  producto: Producto [];
  subcategorias: Subcategoria[] = [];
  marcas: Marca[] = [];
  tipos: Tipo [] = [];
  presentaciones: Presentacion[] = [];
  productoTipoPresentacion: ProductoTipoPresentacion[] = [];


  constructor(
    private service: ProductosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
  ) {

   }



  ngOnInit(): void {
   // this.getProducto();
   // this.getSubcategorias();
  //  this.getMarcas();
  //  this.getTipos();
  //  this.getPresentaciones();
  // this.getTipoPresentacion();
  this.service.getProductos()
  .subscribe(data => {
    this.dataSource = new MatTableDataSource<Producto>(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  } );
  }

  // tslint:disable-next-line: typedef
  openModal(){
    this.dialog.open(AddProductoComponent);
  }

  // tslint:disable-next-line: typedef
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}
