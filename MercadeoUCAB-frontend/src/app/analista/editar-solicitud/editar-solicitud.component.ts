import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Lugar } from 'src/app/modelos/lugar';
import { Ocupacion } from 'src/app/modelos/ocupacion';
import { Producto, Producto2 } from 'src/app/modelos/producto';
import { Solicitud, Solicitud2 } from 'src/app/modelos/solicitud';
import { NivelAcademico, NivelAcademico2, NivelEconomico } from 'src/app/modelos/varios';
import { LugarService } from 'src/app/servicios/lugar.service';
import { OcupacionService } from 'src/app/servicios/ocupacion.service';
import { ProductosService } from 'src/app/servicios/productos.service';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { VariosService } from 'src/app/servicios/varios.service';
import { EstudiosAnalistaComponent } from '../estudios-analista/estudios-analista.component';

@Component({
  selector: 'app-editar-solicitud',
  templateUrl: './editar-solicitud.component.html',
  styleUrls: ['./editar-solicitud.component.css']
})
export class EditarSolicitudComponent implements OnInit {

  solicitudForm: FormGroup;
  productos: Producto[];
  producto2: Producto2;
  ocupaciones: Ocupacion[];
  estados: Lugar[];
  nivelAcademicoArray: NivelAcademico[];
  nivelAcademico2: NivelAcademico2;
  nivelEconomicoArray: NivelEconomico[];
  selectedEstado: Lugar = {
    _id: 0,
    _nombre: '',
    _tipo: '',
    _categoriaSocioEconominca: '',
    _estatus: '',
    _fk_lugar: null,
  };
  municipios: Lugar[];

  constructor(
    private service: SolicitudEstudiosService,
    public dialogRef: MatDialogRef<EstudiosAnalistaComponent>,
    private fb: FormBuilder,
    private productoService: ProductosService,
    private ocupacionService: OcupacionService,
    private lugarService: LugarService,
    private variosService: VariosService,
    @Inject(MAT_DIALOG_DATA) public data: Solicitud2,
  ) {
    this.solicitudForm = this.fb.group({
      descripcion: new FormControl( '', [ Validators.required, Validators.maxLength(150)]),
      edadMinima: new FormControl('', [ Validators.required, Validators.maxLength(50)]),
      edadMaxima: new FormControl('', [ Validators.required, Validators.maxLength(50)]),
      genero: new FormControl('', [Validators.maxLength(100)]),
      estadoCivil: new FormControl('', [Validators.maxLength(100)]),
      estado: new FormControl('', [Validators.maxLength(100)]),
      municipio: new FormControl('', [Validators.maxLength(100)]),
      disponibilidadEnLinea: new FormControl('', [Validators.maxLength(100)]),
      cantidadPersonas: new FormControl('', [Validators.maxLength(100)]),
      cantidadHijos: new FormControl('', [Validators.maxLength(100)]),
      generoHijos: new FormControl('', [Validators.maxLength(100)]),
      edadMinimaHijos: new FormControl('', [Validators.maxLength(100)]),
      edadMaximaHijos: new FormControl('', [Validators.maxLength(100)]),

      nivelEconomicoDto: new FormControl('', [Validators.maxLength(100)]),
      productoDto: new FormControl( '', [ Validators.required, Validators.maxLength(150)]),
      ocupacionDto: new FormControl('', [Validators.maxLength(100)]),
      nivelAcademicoDto: new FormControl('', [Validators.maxLength(100)]),
    });
  }

  solicitud: Solicitud = {
    _id: 0,
    _descripcion: '',
    _genero: '',
    _edadMinima: 0,
    _edadMaxima: 0,
    _estadoCivil: '',
    _disponibilidadEnLinea: '',
    _cantidadPersonas: 0,
    _cantidadHijos: 0,
    _generoHijos: '',
    _edadMinimaHijos: 0,
    _edadMaximaHijos: 0,
    _estatus: '',
    _estado: '',
    _nivelEconomico: null,
    _usuario: null,
    _producto: null,
    _ocupacion: null,
    _nivelAcademico: null
  };
  solicitud2: Solicitud2;

  ngOnInit(): void {
    this.getId();

    // Productos de la BD para el select
    this.productoService.getProductos()
    .subscribe(productosData => {this.productos = productosData; });

    // Ocupaciones de la BD para el select
    this.ocupacionService.getOcupaciones()
    .subscribe(ocupacionesData => {this.ocupaciones = ocupacionesData; });

    // Nivel Académico de la BD para el select
    this.variosService.getNivelAcademico()
    .subscribe(nivelAcademicoData => {this.nivelAcademicoArray = nivelAcademicoData; });

    // Nivel Económico de la BD para el select
    this.variosService.getNivelEconomico()
    .subscribe(nivelEconomicoData => {this.nivelEconomicoArray = nivelEconomicoData; });

    // Lugares de la BD para el select
    this.lugarService.getLugares().subscribe(lugaresData => {
      this.estados = lugaresData.filter(
        (obj) => {
          if (obj._tipo === 'estado'){
            return true;
          }
          return false;
        }
      );
    });

    this.lugarService.getLugares().subscribe(lugaresData => {
      this.municipios = lugaresData.filter(
        (obj) => {
          if (obj._tipo === 'municipio'){
            return true;
          }
          return false;
        }
      );
    });

    this.productoService.getProductos().subscribe(productosData => console.log(productosData));
    this.ocupacionService.getOcupaciones().subscribe(ocupacionesData => console.log(ocupacionesData));
    this.lugarService.getLugares().subscribe(lugaresData => console.log(lugaresData));
  }

  // tslint:disable-next-line: typedef
  getId(){
    console.log('primero', this.solicitud);
    const id = this.data.id;
    this.service.getSolicitud(id).subscribe(data => {this.solicitud = data;
                                                     console.log('segundo', this.solicitud);
    });

  }

  editSolicitud(): void{
    console.log('segundo', this.solicitudForm);
    const editedSol: Solicitud2 = {
      id: this.data.id,
      descripcion: this.solicitudForm.value.descripcion,
      genero: this.solicitudForm.value.genero,
      edadMinima: this.solicitudForm.value.edadMinima,
      edadMaxima: this.solicitudForm.value.edadMaxima,
      estadoCivil: this.solicitudForm.value.estadoCivil,
      disponibilidadEnLinea: this.solicitudForm.value.disponibilidadEnLinea,
      cantidadPersonas: this.solicitudForm.value.cantidadPersonas,
      cantidadHijos: this.solicitudForm.value.cantidadHijos,
      generoHijos: this.solicitudForm.value.generoHijos,
      edadMinimaHijos: this.solicitudForm.value.edadMinimaHijos,
      edadMaximaHijos: this.solicitudForm.value.edadMaximaHijos,
      estatus: this.solicitud._estatus,
      estado: this.solicitud._estado,
      nivelEconomicoDto: this.solicitudForm.value.nivelEconomicoDto._id,
      usuarioDto: this.solicitud._usuario._id,
      productoDto: this.solicitudForm.value.productoDto._id,
      ocupacionDto: this.solicitudForm.value.ocupacionDto._id,
      nivelAcademicoDto: this.solicitudForm.value.nivelAcademicoDto._id
    };
    console.log('tercero', editedSol);
    this.service.updateSolicitud(editedSol).subscribe();
    this.dialogRef.close();
      }

}
