import { Component, OnInit } from '@angular/core';
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
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { TiposPresentacionService } from 'src/app/servicios/tipospresentaciones.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Marca, Marca2 } from 'src/app/modelos/marca';
import { Tipo, Tipo2 } from 'src/app/modelos/tipo';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {

  // Form
  productoForm: any;
  productoFormTP: any;

  producto: Producto =
  {
    _id: 0,
    _nombre: '',
    _estatus: '',
    _descripcion: '',
      _marca: {
      _id: 0,
      _nombre: '',
      _descripcion: '',
     _estatus: '',
    },
    _subcategoria: {
      _id: 0,
      _nombre: '',
      _estatus: '',
      _descripcion: '',
      _categoria: {
        _id: 0,
        _nombre: '',
        _descripcion: '',
        _estatus: '',
      }
    }
  };
  subcategorias: Subcategoria[] = [];
  marcas: Marca[] = [];
  tipos: Tipo [] = [];
  presentaciones: Presentacion[] = [];
  productoTipoPresentacion: ProductoTipoPresentacion[] = [];


  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    // tslint:disable-next-line: variable-name
    private _productoService: ProductosService,
    // tslint:disable-next-line: variable-name
    private _subcategoriaService: SubcategoriasService,
    // tslint:disable-next-line: variable-name
    private _marcaService: MarcasService,
    // tslint:disable-next-line: variable-name
    private _tipoService: TiposService,
    // tslint:disable-next-line: variable-name
    private _presentacionService: PresentacionesService,
    // tslint:disable-next-line: variable-name
    private _tpService: TiposPresentacionService,
    // tslint:disable-next-line: variable-name
    private _snackBar: MatSnackBar,
    public dialog: MatDialog
  ) {

    this.productoForm = this.fb.group({
      nombre: new FormControl('', [ Validators.required]),
      descripcion: new FormControl( '', [ Validators.required]),
      marca: new FormControl ('', [Validators.required]),
      subcategoria: new FormControl('', [Validators.required])

    });
   }



  ngOnInit(): void {
   // this.getProducto();
   // this.getSubcategorias();
  //  this.getMarcas();
  //  this.getTipos();
  //  this.getPresentaciones();
  // this.getTipoPresentacion();
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
