import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Pregunta, Pregunta2, Pregunta3 } from 'src/app/modelos/pregunta';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { DialogComponent } from '../dialog/dialog.component';
import { PreguntasEstudioService } from 'src/app/servicios/preguntasestudios.service';
import { PreguntaEstudio, PreguntaEstudio2 } from 'src/app/modelos/pregunta_estudio';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AddPreguntaEstudioComponent } from '../add-pregunta-estudio/add-pregunta-estudio.component';
import { EstudiosSugeridosComponent } from '../../estudios-sugeridos/estudios-sugeridos.component';
import { PreguntasSugeridasComponent } from '../../preguntas-sugeridas/preguntas-sugeridas.component';
import { Estudio } from 'src/app/modelos/estudio';
import { EstudiosService } from 'src/app/servicios/estudios.service';

@Component({
  selector: 'app-preguntas-estudio',
  templateUrl: './preguntas-estudio.component.html',
  styleUrls: ['./preguntas-estudio.component.css']
})
export class PreguntasEstudioComponent implements OnInit {

  preguntasEstudios: PreguntaEstudio[];
  preguntas: Pregunta[];
  id: number;
  estudio: Estudio;

  constructor(
    private service: PreguntasEstudioService,
    private estudiosService: EstudiosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  displayedColumns: string[] = ['descripcion', 'tipoPregunta', 'subCategoria', 'estatus'];
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
      this.id = +this.actRoute.snapshot.paramMap.get('id');
      this.estudiosService.consultarEstudio(this.id).subscribe(
        data => { this.estudio = data; }
      );
      console.log(this.estudio);

      // this.service.getPreguntasEstudio(this.id)
      // .subscribe(data => {
      //   this.dataSource = new MatTableDataSource<Pregunta3>(data);
      //   this.dataSource.paginator = this.paginator;
      // } );
  }

  // tslint:disable-next-line: typedef
  newAnswer(){
    this.encuesta.push(new FormGroup({
      pregunta: new FormControl(null, Validators.required),
      tipo: new FormControl(null, Validators.required)
    }));
  }

  // tslint:disable-next-line: typedef
  delete(){ this.encuesta.removeAt(this.item); }

  // tslint:disable-next-line: typedef
  openModal1(){
    this.dialog.open(AddPreguntaEstudioComponent,
      {
        data: {id: this.id}
      }
    );
  }

  // tslint:disable-next-line: typedef
  openModal2(){
    this.dialog.open(PreguntasSugeridasComponent,
      {
        data: {
          id: this.id,
          idSolicitud: this.estudio._solicitudEstudio._id
        }
      }
    );
  }

  // tslint:disable-next-line: typedef-whitespace
  openModal3(id: number): void{
    this.dialog.open(EstudiosSugeridosComponent,
      {
        // tslint:disable-next-line: object-literal-shorthand
        data: {id: id}
      }
    );
  }

  // tslint:disable-next-line: typedef
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}

