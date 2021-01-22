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

  openModal(){
    this.dialog.open(AddProductoComponent);
  }



  // Metodos Productos

/*
  getProducto(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this._productoService.getProducto(id).subscribe(data => {this.producto = data;});
  }

  saveProducto(): void {
    this.isWait = true;
    const newProducto: Producto = {
      id: this.id,
      nombre: this.productoForm.get("nombre").value,
      estado: "A",
      descripcion: this.productoForm.get("descripcion").value,
      subcategoriaDto: this.productoForm.get("subcategoriaDto").value,
      marcaDto: this.productoForm.get("marcaDto").value
    };

    console.log(newProducto)
    this._productoService.editProducto(newProducto).subscribe(() => {
    this.openSnackBar();
    this.isWait = false;
    this.getProducto();
    this.getTipoPresentacion(); });
   }


  addTipoPresentacion(): void {
  this.isWait = true;
  const newTP: ProductoTipoPresentacion = {
    id:0,
    estado: 'A',
    productoDto: this.id,
    tipoDto: this.productoFormTP.get("tipoDto").value,
    presentacionDto: this.productoFormTP.get("presentacionDto").value,
  }

  console.log(newTP);
  this._tpService.createProductoTipoPresentacion(newTP).subscribe(() => {
    this.isWait = false;
    this.openSnackBar();
    this.getProducto();
    this.getTipoPresentacion();
  });

  this.getTipoPresentacion();
}

deleteTipoPresentacion(tp: ProductoTipoPresentacion): void {
  this.isWait = true;
  const newCa: ProductoTipoPresentacion = {
    id: tp._id,
    estado: 'I',
    productoDto: this.id,
    tipoDto: tp._tipo._id,
    presentacionDto: tp._presentacion._id
  };

  if(confirm("Estas seguro de eliminar "+tp._producto._nombre)) {
    this._tpService.editProductoTipoPresentacion(newCa).subscribe(() =>  {
      this.isWait = false;
      this.getProducto();
      this.getTipoPresentacion();
    });
  }
}
o


    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getProducto();
      this.getTipoPresentacion();
    });

  }


  *

  // Get Tipo, Presentacion, Subcategoria, Marca

 getSubcategorias(): void {
  this._subcategoriaService.getSubcategorias().subscribe(data => {this.subcategorias = data});
}

getMarcas(): void {
  this._marcaService.getMarcas().subscribe(data => {this.marcas = data});
}

getTipos(): void {
  this._tipoService.getTipos().subscribe(data => {this.tipos = data});
}

getPresentaciones(): void {
 this._presentacionService.getPresentaciones().subscribe(data => {this.presentaciones = data});
}


// Snackbar

openSnackBar() {
  this._snackBar.open('Guardado!', 'Ok', {
    duration: 2000,
  });
}*/
}
