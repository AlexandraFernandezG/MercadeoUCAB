import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaComponent } from 'src/app/admin/categoria/categoria.component';
import { Marca } from 'src/app/modelos/marca';
import { Presentacion } from 'src/app/modelos/presentacion';
import { Producto } from 'src/app/modelos/producto';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Tipo } from 'src/app/modelos/tipo';
import { ProductosService } from 'src/app/servicios/productos.service';

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
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<CategoriaComponent>
    ) {

    this.productoForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      marca: new FormControl('',[Validators.required])
    });
   }


   // Form
   productoForm: any;
   producto: Producto;
   subcategorias: Subcategoria[] = [];
   marcas: Marca[] = [];
   

  ngOnInit(): void {
  }

}
