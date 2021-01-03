import { Component, OnInit, Input, Inject } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Pregunta, Pregunta2, Pregunta3 } from 'src/app/modelos/pregunta';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { Subcategoria, Subcategoria2 } from 'src/app/modelos/subcategoria';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { PreguntasEstudioService } from 'src/app/servicios/preguntasestudios.service';
import { PreguntaEstudio, PreguntaEstudio2 } from 'src/app/modelos/pregunta_estudio';
import { Estudio2 } from 'src/app/modelos/estudio';


@Component({
  selector: 'app-preguntas-sugeridas',
  templateUrl: './preguntas-sugeridas.component.html',
  styleUrls: ['./preguntas-sugeridas.component.css']
})
export class PreguntasSugeridasComponent implements OnInit {

  preguntas: Pregunta3[];
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

  ngOnInit(): void {
    this.idestudio = +this.actRoute.snapshot.paramMap.get("id");
    console.log(this.idestudio);
    this.servicePreguntaEstudio.getPreguntasSugeridasEstudio(this.idestudio)
    .subscribe(data => {this.preguntas = data;
    } );
  }

  Agregar(pregunta: Pregunta3): void {
    const id = 1;
    const estatus = 'Activo';
    this.servicePreguntaEstudio.createPreguntaEstudio({
     id,
    estatus,
    preguntaEncuestaDto: pregunta.idPregunta,
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
