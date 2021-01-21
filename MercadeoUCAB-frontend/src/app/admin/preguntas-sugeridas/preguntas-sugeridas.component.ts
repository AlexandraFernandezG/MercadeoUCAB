import { Component, OnInit, Input, Inject, ViewChild } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
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
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { PreguntasEstudioComponent } from '../estudios/preguntas-estudio/preguntas-estudio.component';


@Component({
  selector: 'app-preguntas-sugeridas',
  templateUrl: './preguntas-sugeridas.component.html',
  styleUrls: ['./preguntas-sugeridas.component.css']
})
export class PreguntasSugeridasComponent implements OnInit {

  preguntas: Pregunta3[];
  preguntasAgregadas: Pregunta3[] = [];
  subcategorias: Subcategoria[];

  constructor(
    private service: PreguntasService,
    private servicePreguntaEstudio: PreguntasEstudioService,
    private serviceSubcategoria: SubcategoriasService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<PreguntasEstudioComponent>,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }
  preguntaForm: FormGroup;
  idestudio: number;
  displayedColumns: string[] = ['descripcion','tipoPregunta','subcategoria', 'acciones'];
  dataSource: MatTableDataSource<Pregunta3>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.dialogRef.updateSize('650px', '450px');
    this.idestudio = +this.actRoute.snapshot.paramMap.get('id');
    console.log('id:', this.data.idSolicitud);
    this.servicePreguntaEstudio.getPreguntasSugeridasEstudio(this.data.idSolicitud)
    .subscribe(data => {
      this.dataSource = new MatTableDataSource<Pregunta3>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    } );
  }

  Agregar(pregunta: Pregunta3) {
    
    console.log('antes:', this.preguntasAgregadas)
    this.preguntasAgregadas = JSON.parse(localStorage.getItem('preguntasEst'));
    console.log('traer: ',this.preguntasAgregadas);
    this.preguntasAgregadas.push(pregunta);
    console.log('agregar: ', this.preguntasAgregadas);
    localStorage.setItem('preguntasEst',  JSON.stringify (this.preguntasAgregadas))
    return this.dialogRef.close();
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
