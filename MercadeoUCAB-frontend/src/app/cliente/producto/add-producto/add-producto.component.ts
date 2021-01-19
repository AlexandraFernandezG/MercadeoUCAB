import { Component, OnInit } from '@angular/core';
import { Marca } from 'src/app/modelos/marca';
import { Presentacion } from 'src/app/modelos/presentacion';
import { Producto } from 'src/app/modelos/producto';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Tipo } from 'src/app/modelos/tipo';

@Component({
  selector: 'app-add-producto',
  templateUrl: './add-producto.component.html',
  styleUrls: ['./add-producto.component.css']
})
export class AddProductoComponent implements OnInit {

  constructor() { }

   // Form
   productoForm: any;
   producto: Producto;
   subcategorias: Subcategoria[] = [];
   marcas: Marca[] = [];
   

  ngOnInit(): void {
  }

}
