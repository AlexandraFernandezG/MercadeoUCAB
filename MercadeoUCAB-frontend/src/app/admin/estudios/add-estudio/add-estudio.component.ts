import { Component, Inject, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { NotificationsService } from 'angular2-notifications';
import { Estudio2 } from 'src/app/modelos/estudio';
import { Pregunta, Pregunta3 } from 'src/app/modelos/pregunta';
import { Solicitud2 } from 'src/app/modelos/solicitud';
import { Usuario3 } from 'src/app/modelos/usuario';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { UsuariosService } from 'src/app/servicios/usuarios.service';
import { EstudiosSugeridosComponent } from '../../estudios-sugeridos/estudios-sugeridos.component';
import { PreguntasSugeridasComponent } from '../../preguntas-sugeridas/preguntas-sugeridas.component';
import { SolicitudesComponent } from '../../solicitudes/solicitudes.component';
import { Observable } from 'rxjs';
import { OnChanges } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-estudio',
  templateUrl: './add-estudio.component.html',
  styleUrls: ['./add-estudio.component.css']
})
export class AddEstudioComponent implements OnInit, OnChanges {

  estudioForm: FormGroup;
  estudio: Estudio2;
  analistas: Usuario3[];
  encuestados: Usuario3[];
  idEstudioCreado: number;

  displayedColumns: string[] = ['nombre', 'correo', 'acciones'];
  dataSource: MatTableDataSource<Usuario3>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  preguntas: Pregunta3[] = [];
  displayedColumns2: string[] = ['descripcionPregunta', 'tipoPregunta','subcategoria'];
  dataSource2: MatTableDataSource<Pregunta3>;
  @ViewChild(MatPaginator) paginator2: MatPaginator;


  constructor(
    // public dialogRef: MatDialogRef<SolicitudesComponent>,
    // @Inject(MAT_DIALOG_DATA) public data: Solicitud2,
    private fb: FormBuilder,
    private estudiosService: EstudiosService,
    private analistasService: UsuariosService,
    private encuestadosService: UsuariosService,
    private servicenotifications: NotificationsService,
    public dialog: MatDialog,
    public router: Router,
  ) { 
    this.estudioForm = this.fb.group({

      nombre: new FormControl( '',[ Validators.maxLength(150)]),
      instrumento: new FormControl('',[ Validators.maxLength(50)]),
      fechaInicio: new FormControl('',[  Validators.maxLength(50)]),
      fechaFin: new FormControl('',[  Validators.maxLength(50)]),
      analista: new FormControl('',[  Validators.maxLength(50)]),
    })
  }

  ngOnChanges(changes: SimpleChanges): void {
    const preguntasChange = changes['algo'];
    if (preguntasChange.currentValue == true){
      this.dataSource2 = new MatTableDataSource<Pregunta3>(this.preguntas)
    }
  }

  ngOnInit(): void {
    // console.log(this.data.id);
    this.analistasService.getAnalistas().subscribe( analistasData =>
      {this.analistas = analistasData} );
    console.log(this.analistas);

    this.encuestadosService.getEncuestadosEstudio(JSON.parse(localStorage.getItem('solicitudId'))).subscribe( encuestadosData =>
      {

        this.dataSource = new MatTableDataSource<Usuario3>(encuestadosData);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        localStorage.setItem('encuestados', JSON.stringify(encuestadosData))
      } 
    );

    this.dataSource2 = new MatTableDataSource<Pregunta3>(
      JSON.parse(localStorage.getItem('preguntasEst'))
    );
    this.dataSource2.paginator = this.paginator;
    this.dataSource2.sort = this.sort;

  }

  onSucess(message){
    this.servicenotifications.success('Exito', message, {
      position: ['bottom', 'right'],
      timeOut: 2000,
      animate: 'fade',
      showProgressBar: true,
      })
  }

  onError(message){
    this.servicenotifications.error('¡ERROR!', message, {
      position: ['bottom', 'right'],
      timeOut: 2000,
      animate: 'fade',
      showProgressBar: true,
      });
  }

  addEstudio(){
    
    this.estudio = {
      id: 1,
      nombre: this.estudioForm.value.nombre,
      tipoInstrumento: this.estudioForm.value.instrumento,
      fechaInicio: this.estudioForm.value.fechaInicio,
      fechaFin: this.estudioForm.value.fechaFin,
      estatus: 'activo',
      estado: 'en espera',
      usuarioDto: this.estudioForm.value.analista,
      solicitudEstudioDto: JSON.parse(localStorage.getItem('solicitudId')),
    }
    this.encuestados = JSON.parse(localStorage.getItem('encuestados'));
    console.log(JSON.parse(localStorage.getItem('encuestados')))
    this.preguntas = JSON.parse(localStorage.getItem('preguntasEst'));
    console.log(this.estudio);
    console.log(this.encuestados);
    console.log(this.preguntas);
    
    //Lógica de cracion de estudio
    if( this.encuestados.length == 0){
      this.onError('El estudio debe tener encuestados asignados');
    }else if (this.preguntas.length == 0){
      this.onError('El estudio debe tener preguntas asignadas');
    }else{
      this.estudiosService.createEstudio(
        this.estudio as Estudio2
      ).subscribe( dataEstudio => 
        {
          if (dataEstudio != undefined){
            this.onSucess('Estudio creado correctamente');
            this.estudiosService.addEncuestadosEstudio(dataEstudio.id,this.encuestados).subscribe();
            this.estudiosService.addPreguntasEstudio(dataEstudio.id,this.preguntas).subscribe();
            
          }
        }
      );
      setTimeout(() => {
        this.router.navigate(['/admin']);
        localStorage.setItem('encuestados', JSON.stringify([]));
        localStorage.setItem('preguntasEst', JSON.stringify([]));
      },3000);
    }
    
  }


  openModal2(){
    const dialogRef = this.dialog.open(PreguntasSugeridasComponent,
      {
        data: {
          idSolicitud: JSON.parse(localStorage.getItem('solicitudId'))
        }
      }
    );
      
    // dialogRef.afterClosed().subscribe(result => {
    //   console.log(result)
    //   if(result != undefined){this.preguntas.push(result)}
    //   console.log('test preg: ', this.preguntas)
    // });
    // console.log('test preg: ', this.preguntas)
  }
  
  openModal3():void{
    this.dialog.open(EstudiosSugeridosComponent,
      {
        data: {id: JSON.parse(localStorage.getItem('solicitudId'))}
      }
    );
    
  }
}




