import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Pregunta, Pregunta2 } from 'src/app/modelos/pregunta';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { DialogComponent } from '../dialog/dialog.component';
import { PreguntasEstudioService } from 'src/app/servicios/preguntasestudios.service';
import { PreguntaEstudio, PreguntaEstudio2 } from 'src/app/modelos/pregunta_estudio';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AddPreguntaEstudioComponent } from '../add-pregunta-estudio/add-pregunta-estudio.component';
import { EstudiosSugeridosComponent } from '../../estudios-sugeridos/estudios-sugeridos.component';

@Component({
  selector: 'app-preguntas-estudio',
  templateUrl: './preguntas-estudio.component.html',
  styleUrls: ['./preguntas-estudio.component.css']
})
export class PreguntasEstudioComponent implements OnInit {

  preguntasEstudios: PreguntaEstudio[];
  preguntas: Pregunta[];
  id: number;

  constructor(
    private service: PreguntasEstudioService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  displayedColumns: string[] = ['descripcion', 'tipoPregunta', 'subCategoria','estatus'];
  dataSource: MatTableDataSource<Pregunta>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  public formAnswers = new FormGroup({
    nameAnswer : new FormControl(null, Validators.required),
    encuesta : new FormArray([])
  });
  public encuesta = this.formAnswers.get('encuesta') as FormArray;
  public validated = false;
  public dateSave = false;
  private item: number;

  ngOnInit(): void {
      this.id= +this.actRoute.snapshot.paramMap.get("id");
      this.service.getPreguntasEstudio(this.id)
      .subscribe(data => {
        this.dataSource = new MatTableDataSource<Pregunta>(data);
        this.dataSource.paginator = this.paginator;
      } );
  }

  newAnswer(){
    this.encuesta.push(new FormGroup({
      pregunta: new FormControl(null, Validators.required),
      tipo: new FormControl(null, Validators.required)
    }));
  }

  delete(){ this.encuesta.removeAt(this.item); }

  openModal(id: number):void{
    this.dialog.open(EstudiosSugeridosComponent,
      {
        data: {id: id}
      }
      );
  }

  openModal1(){
    this.dialog.open(AddPreguntaEstudioComponent);
  }

}

