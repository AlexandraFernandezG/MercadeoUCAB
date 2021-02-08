import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Estudio, Estudio2 } from 'src/app/modelos/estudio';
import { Pregunta3 } from 'src/app/modelos/pregunta';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { PreguntasEstudioService } from 'src/app/servicios/preguntasestudios.service';
import { EstudiosComponent } from '../estudios/estudios.component';
import { PreguntasEstudioComponent } from '../estudios/preguntas-estudio/preguntas-estudio.component';

@Component({
  selector: 'app-estudios-sugeridos',
  templateUrl: './estudios-sugeridos.component.html',
  styleUrls: ['./estudios-sugeridos.component.css']
})
export class EstudiosSugeridosComponent implements OnInit {

  preguntasEst: Pregunta3;
  preguntasAgregadas: Pregunta3[];
  estudios: Estudio[];
  constructor(
    private service: EstudiosService,
    private servicePreguntas: PreguntasEstudioService,
    public dialogRef: MatDialogRef<PreguntasEstudioComponent>,
    @Inject(MAT_DIALOG_DATA) public est: Estudio2,
  ) { }

  displayedColumns: string[] = ['nombre', 'fechaInicio', 'fechaFin', 'usuario',
    'estatus', 'acciones'];
  dataSource: MatTableDataSource<Estudio>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    // this.service.getEstudios()
    // .subscribe(data => {
    //   this.dataSource = new MatTableDataSource<Estudio>(data);
    //   this.dataSource.paginator = this.paginator;
    //   this.dataSource.sort = this.sort;
    // } );

   this.service.getEstudiosSugeridos(JSON.parse(localStorage.getItem('solicitudId')))
   .subscribe(data => {
    this.dataSource = new MatTableDataSource<Estudio>(data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
   } );
  }

  cloneEstudio(idSugerido: number){
    this.servicePreguntas.getPreguntasEstudio(idSugerido).subscribe(
      dataPreguntas => {
        console.log(dataPreguntas)
        for (var i=0; i < dataPreguntas.length; i++ ){
          this.preguntasEst = dataPreguntas[i];
          console.log(this.preguntasEst);
          console.log('antes:', this.preguntasAgregadas)
          this.preguntasAgregadas = JSON.parse(localStorage.getItem('preguntasEst'));
          console.log('traer: ',this.preguntasAgregadas);
          this.preguntasAgregadas.push(this.preguntasEst);
          console.log('agregar: ', this.preguntasAgregadas);
          localStorage.setItem('preguntasEst',  JSON.stringify (this.preguntasAgregadas))
          
        }
      }
    );
    localStorage.setItem('flagClone', '1')
    this.dialogRef.close();
  }

}
