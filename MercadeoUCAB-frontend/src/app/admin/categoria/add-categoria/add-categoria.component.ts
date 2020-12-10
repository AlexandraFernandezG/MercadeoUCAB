import { Component, OnInit, Input } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router , ActivatedRoute} from '@angular/router';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { Categoria } from 'src/app/modelos/categoria';
import { Categoria2 } from 'src/app/modelos/categoria';



@Component({
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html',
  styleUrls: ['./add-categoria.component.css']
})
export class AddCategoriaComponent implements OnInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private service: CategoriasService,
    public actRoute: ActivatedRoute,
    ) {

    this.categoriaForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)])
    });
   }

  categoria: Categoria = {_id: 1 , _nombre: '', _descripcion: '', estatus: 'Activo'};
  categoria2: Categoria2 = {id: 1 , nombre: '', descripcion: '', estatus: 'Activo'};
  categoriaForm: FormGroup;
  ngOnInit(): void {
  }

  addCategoria(nombre: string, descripcion: string): void{
    const id = 1;
    const estatus = 'Activo';
    this.service.createCategoria({
     id,
    nombre,
    descripcion,
    estatus
    } as Categoria2).subscribe();
    console.log(id, nombre, descripcion, estatus);
  }
}
