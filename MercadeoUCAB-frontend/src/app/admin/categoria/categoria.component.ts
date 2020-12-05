import { Component, OnInit, Inject } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AddCategoriaComponent } from './add-categoria/add-categoria.component';


@Component({
  selector: 'app-categoria',
  templateUrl: './categoria.component.html',
  styleUrls: ['./categoria.component.css']
})
export class CategoriaComponent implements OnInit {

  constructor(
    private service: CategoriasService,
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
  }


  openModal(){
    this.dialog.open(AddCategoriaComponent);
  }

}
