import { Component, OnInit, Inject } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddCategoriaComponent } from './add-categoria/add-categoria.component';
import { Categoria } from 'src/app/modelos/categoria';
import { Router } from '@angular/router';



@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  categorias: Categoria[];
  constructor(
    private service: CategoriasService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() {
    this.service.getCategorias()
    .subscribe(data => {this.categorias = data;
    } );
  }


  openModal(){
    this.dialog.open(AddCategoriaComponent);
  }

}
