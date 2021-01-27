import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaComponent } from 'src/app/admin/categoria/categoria.component';
import { Marca } from 'src/app/modelos/marca';
import { Presentacion } from 'src/app/modelos/presentacion';
import { Producto, Producto2 } from 'src/app/modelos/producto';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Tipo } from 'src/app/modelos/tipo';
import { MarcasService } from 'src/app/servicios/marcas.service';
import { ProductosService } from 'src/app/servicios/productos.service';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { ProductoComponent } from '../producto.component';

@Component({
  selector: 'app-add-producto',
  templateUrl: './add-producto.component.html',
  styleUrls: ['./add-producto.component.css']
})
export class AddProductoComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: ProductosService,
    private serviceSub: SubcategoriasService,
    private serviceMarca: MarcasService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<ProductoComponent>
    ) {

    this.productoForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      subcategoriaDto: new FormControl('', [ Validators.required]),
      marcaDto: new FormControl('',[Validators.required])
    });
   }


   // Form
   productoForm: any;
   producto: Producto= { 
     _id: 1,
    _nombre: '',
    _descripcion: '',
    _estatus: 'Activo',
    _marca: {
      _id: 1 , 
      _nombre: '', 
      _descripcion: '',
      _estatus: 'Activo'
    },
    _usuario: {
      _id: 1 ,
      _nombre: '',
      _correoelectronico: '',
      _codigoRecuperacion: '',
      _estatus: 'Activo',
      _rol: {
        _id: 0,
        _nombre: '',
        _estatus: 'Activo'
      }
    },
    _subcategoria: {
      _id: 0,
      _nombre: '',
      _estatus: '',
      _descripcion: '',
      _categoria: {
        _id: 0,
        _nombre: '',
        _estatus: '',
        _descripcion: ''
    }
  }
}

producto2: Producto2 = {
  id: 1,
 nombre: '',
 descripcion: '',
 estatus: 'Activo',
 marcaDto: 1,
 usuarioDto: 1,
 subcategoriaDto: 1
}

   subcategorias: Subcategoria[] = [];
   marcas: Marca[] = [];

  ngOnInit(): void {

      this.serviceSub.getSubcategorias()
      .subscribe(data => {this.subcategorias = data;
      } );
      this.serviceMarca.getMarcas()
      .subscribe(marca => {this.marcas = marca;
      } );
    }

    addProducto(nombre: string, descripcion: string): void{
      const id = 1;
      const estatus = 'Activo';
      this.service.createProducto({
       id,
      nombre,
      descripcion,
      estatus,
      usuarioDto:1,
      subcategoriaDto: this.productoForm.get("subcategoriaDto").value,
      marcaDto: this.productoForm.get("marcaDto").value
      } as Producto2).subscribe();
      console.log(Producto2);
      this.dialogRef.close();
    }
  
  }
