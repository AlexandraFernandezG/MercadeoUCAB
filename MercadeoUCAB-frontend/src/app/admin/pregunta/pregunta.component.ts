import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Pregunta, Pregunta2 } from 'src/app/modelos/pregunta';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { AddPreguntaComponent } from './add-pregunta/add-pregunta.component';
import { EditPreguntaComponent } from '../pregunta/edit-pregunta/edit-pregunta.component';
import { Subcategoria, Subcategoria2 } from 'src/app/modelos/subcategoria';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { PreguntasEstudioService } from 'src/app/servicios/preguntasestudios.service';
import { PreguntaEstudio, PreguntaEstudio2 } from 'src/app/modelos/pregunta_estudio';
import { Estudio2 } from 'src/app/modelos/estudio';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-pregunta',
  templateUrl: './pregunta.component.html',
  styleUrls: ['./pregunta.component.css']
})
export class PreguntaComponent implements OnInit {

  preguntas: Pregunta[];
  subcategorias: Subcategoria[];

  constructor(
    private service: PreguntasService,
    private servicePreguntaEstudio: PreguntasEstudioService,
    private serviceSubcategoria: SubcategoriasService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }
  preguntaForm: FormGroup;
  idestudio: number;
  displayedColumns: string[] = ['descripcion', 'tipoPregunta', 'subCategoria','estatus', 'acciones'];
  dataSource: MatTableDataSource<Pregunta>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.idestudio = +this.actRoute.snapshot.paramMap.get("id");
    console.log(this.idestudio);
    this.service.getPreguntas()
    .subscribe(data => {
      this.dataSource = new MatTableDataSource<Pregunta>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );
  }

  openModal(){
    this.dialog.open(AddPreguntaComponent);
  }


  openEModal( id: number): void{
    this.dialog.open(EditPreguntaComponent,
      {
        data: {id}
      }
    );
  }

  Agregar(pregunta: Pregunta):void {
    const id = 1;
    const estatus = 'Activo';
    this.servicePreguntaEstudio.createPreguntaEstudio({
     id,
    estatus,
    preguntaEncuestaDto: pregunta._id,
    estudioDto: this.idestudio

    } as PreguntaEstudio2).subscribe();
    console.log(PreguntaEstudio);
  }

  deletepregunta( pregunta: Pregunta): void{
    console.log('segundo', pregunta);
    const editPr: Pregunta2 = {
      id: pregunta._id,
      tipoPregunta: pregunta._tipoPregunta,
      descripcion: pregunta._descripcion,
      estatus: 'Inactivo',
      usuarioDto: pregunta._usuario._id,
      subcategoriaDto: pregunta._subcategoria._id
    };
//    this.service.updatePregunta(editPr).subscribe();
      }


}
