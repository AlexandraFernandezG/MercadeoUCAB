import { Component, OnInit } from '@angular/core';
import { MatInputModule} from '@angular/material/input';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoriasService } from 'src/app/servicios/categorias.service';



@Component({
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html',
  styleUrls: ['./add-categoria.component.css']
})
export class AddCategoriaComponent implements OnInit {

  categoriaForm: FormGroup;


  constructor(private fb: FormBuilder, private route: Router, private service: CategoriasService) {

    this.categoriaForm = this.fb.group({
      nombre: new FormControl('',[ Validators.required, Validators.maxLength(100)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)])
    });
   }

  ngOnInit() {
    
  }

 onSubmit(){
  console.log(this.categoriaForm.value);

 }

}
