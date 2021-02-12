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
import { AddPreguntaEstudioComponent } from '../add-pregunta-estudio/add-pregunta-estudio.component';
import { AddEncuestadoEstudioComponent } from '../add-encuestado-estudio/add-encuestado-estudio.component';
import { AddPreguntaComponent } from '../../pregunta/add-pregunta/add-pregunta.component';

@Component({
  selector: 'app-add-estudio',
  templateUrl: './add-estudio.component.html',
  styleUrls: ['./add-estudio.component.css']
})
export class AddEstudioComponent implements OnInit, OnChanges {

  estudioForm: FormGroup;
  estudio: any;
  analistas: Usuario3[];
  encuestados: Usuario3[];
  idEstudioCreado: number;
  position: number;
  flagClone: string;
  datosEst: any ={
    nombre: JSON.parse(localStorage.getItem('solicitudDes')),
    edadMin: localStorage.getItem('solicitudMin'),
    edadMax: localStorage.getItem('solicitudMax'),
    genero: JSON.parse(localStorage.getItem('solicitudGen')),
    producto: JSON.parse(localStorage.getItem('solicitudProducto')),
  }

  displayedColumns: string[] = ['nombre', 'correo', 'acciones'];
  dataSource: MatTableDataSource<Usuario3>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  preguntas: Pregunta3[] = [];
  displayedColumns2: string[] = ['descripcionPregunta', 'tipoPregunta','subcategoria', 'acciones'];
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

      // nombre: new FormControl( '',[ Validators.maxLength(150)]),
      // instrumento: new FormControl('',[ Validators.maxLength(50)]),
      // fechaInicio: new FormControl('',[  Validators.maxLength(50)]),
      // fechaFin: new FormControl('',[  Validators.maxLength(50)]),
      analista: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
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
      {this.analistas = analistasData.Usuarios} );
    console.log(this.analistas);

    this.curday();
    this.encuestadosService.getEncuestadosEstudio(JSON.parse(localStorage.getItem('solicitudId'))).subscribe( encuestadosData =>
      {

        console.log(encuestadosData.Encuestados);
        if(localStorage.getItem('flag') != '1'){
          this.dataSource = new MatTableDataSource<Usuario3>(encuestadosData.Encuestados);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
          console.log(encuestadosData.Encuestados)
          localStorage.setItem('flag', '1')
          localStorage.setItem('flagClone', '0')
          localStorage.setItem('encuestados', JSON.stringify(encuestadosData.Encuestados))
          console.log('if')
        }else{
          this.dataSource = new MatTableDataSource<Usuario3>(JSON.parse(localStorage.getItem('encuestados')));
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
          console.log('else')
        }
        
      } 
    );

    this.dataSource2 = new MatTableDataSource<Pregunta3>(
      JSON.parse(localStorage.getItem('preguntasEst'))
    );
    this.dataSource2.paginator = this.paginator2;
    this.dataSource2.sort = this.sort;
    
    this.flagClone = localStorage.getItem('flagClone')
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

  curday = function(){
    var today: Date;
    today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //As January is 0.
    var yyyy = today.getFullYear();
    console.log('dia:',dd)
    if(dd<10) var dd2='0'+dd;else{var dd2= dd.toString()}
    if(mm<10) var mm2='0'+mm;
    console.log('dia:',dd2)
    console.log((yyyy+'-'+mm2+'-'+dd2))
    return (yyyy+'-'+mm2+'-'+dd2);
  };

  addEstudio(){
    
    this.estudio = {
      id: 1,
      nombre: JSON.parse(localStorage.getItem('solicitudDes')),
      tipoInstrumento: 'Encuesta',
      observaciones: null,
      fechaInicio: this.curday(),
      fechaFin: null,
      estatus: 'Activo',
      estado: 'En espera',
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
        this.estudio
      ).subscribe( dataEstudio => 
        {
          if (dataEstudio != undefined){
            console.log(JSON.stringify(dataEstudio))
            this.onSucess('Estudio creado correctamente');
            this.estudiosService.addEncuestadosEstudio(dataEstudio.id.id,this.encuestados).subscribe();
            this.estudiosService.addPreguntasEstudio(dataEstudio.id.id,this.preguntas).subscribe();
            
          }
        }
      );
      setTimeout(() => {
        this.router.navigate(['/admin']);
        localStorage.setItem('encuestados', JSON.stringify([]));
        localStorage.setItem('preguntasEst', JSON.stringify([]));
        localStorage.setItem('flag', '0');
        localStorage.setItem('flagClone', '0')
      },3000);
    }
    
  }

  cancelar(){
    setTimeout(() => {
      this.router.navigate(['/admin']);
      localStorage.setItem('encuestados', JSON.stringify([]));
      localStorage.setItem('preguntasEst', JSON.stringify([]));
      localStorage.setItem('flag', '0');
      localStorage.setItem('flagClone', '0')
    },1000);
  }

  openModal0():void{
    this.dialog.open(AddEncuestadoEstudioComponent,
      {
        data: {id: JSON.parse(localStorage.getItem('solicitudId'))}
      }
    );
    
  }

  openModal1():void{
    this.dialog.open(AddPreguntaComponent,
      {
        data: {id: JSON.parse(localStorage.getItem('solicitudId'))}
      }
    );
    
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

  deleteEncuestado(encuestado: Usuario3){
    const encAgregados: Usuario3[] = JSON.parse(localStorage.getItem('encuestados'));
    this.position = null;
    console.log('antes:', encAgregados);
    console.log(this.position = encAgregados.findIndex(function(encuestado){return encuestado}));
    encAgregados.splice(encAgregados.findIndex(function(encuestado){return encuestado}),1)
    console.log('traer: ', encAgregados);
    localStorage.setItem('encuestados',  JSON.stringify (encAgregados))
  }

  deletePregunta(pregunta: Pregunta3){
    const pregAgregadas: Usuario3[] = JSON.parse(localStorage.getItem('preguntasEst'));
    this.position = null;
    console.log('antes:', pregAgregadas);
    console.log(this.position = pregAgregadas.findIndex(function(pregunta){return pregunta}));
    pregAgregadas.splice(pregAgregadas.findIndex(function(pregunta){return pregunta}),1)
    console.log('traer: ', pregAgregadas);
    localStorage.setItem('preguntasEst',  JSON.stringify (pregAgregadas))
  }
}




