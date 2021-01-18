import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductosService } from '../../servicios/productos.service';
import { Producto, Producto2 } from '../../modelos/producto';
import { Ocupacion } from 'src/app/modelos/ocupacion';
import { OcupacionService } from 'src/app/servicios/ocupacion.service';
import { Lugar } from '../../modelos/lugar';
import { LugarService } from 'src/app/servicios/lugar.service';
import { SolicitudEstudiosService } from '../../servicios/solicitud-estudios.service';
import { Solicitud2 } from '../../modelos/solicitud';
import { VariosService } from 'src/app/servicios/varios.service';
import { NivelAcademico, NivelAcademico2, NivelEconomico } from 'src/app/modelos/varios';
import { Usuario2 } from 'src/app/modelos/usuario';
import { Rol } from 'src/app/modelos/rol';


@Component({
  selector: 'app-solicitud-estudio',
  templateUrl: './solicitud-estudio.component.html',
  styleUrls: ['./solicitud-estudio.component.css']
})
export class SolicitudEstudioComponent implements OnInit {

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
  }
  municipios: Lugar[];
  // allMunicipios: Lugar[];
  solicitud2: Solicitud2 ;
  



  constructor(
    private fb: FormBuilder, 
    private router: Router, 
    private productoService: ProductosService,
    private ocupacionService: OcupacionService,
    private lugarService: LugarService,
    private solicitudEstudiosService: SolicitudEstudiosService,
    private variosService: VariosService,
    ) 
    
    {
    this.solicitudForm = this.fb.group({
      
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      edadMinima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      edadMaxima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      genero: new FormControl('',[Validators.maxLength(100)]),
      estadoCivil: new FormControl('',[Validators.maxLength(100)]),
      estado: new FormControl('',[Validators.maxLength(100)]),
      municipio: new FormControl('',[Validators.maxLength(100)]),
      disponibilidadEnLinea: new FormControl('',[Validators.maxLength(100)]),
      cantidadPersonas: new FormControl('',[Validators.maxLength(100)]),
      cantidadHijos: new FormControl('',[Validators.maxLength(100)]),
      generoHijos: new FormControl('',[Validators.maxLength(100)]),
      edadMinimaHijos: new FormControl('',[Validators.maxLength(100)]),
      edadMaximaHijos: new FormControl('',[Validators.maxLength(100)]),

      nivelEconomicoDto: new FormControl('',[Validators.maxLength(100)]),
      productoDto: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      ocupacionDto: new FormControl('',[Validators.maxLength(100)]),
      nivelAcademicoDto: new FormControl('',[Validators.maxLength(100)]),
    });
   }

  ngOnInit(): void {
    //Productos de la BD para el select
    this.productoService.getProductos()
    .subscribe(productosData => {this.productos = productosData;});

    //Ocupaciones de la BD para el select
    this.ocupacionService.getOcupaciones()
    .subscribe(ocupacionesData => {this.ocupaciones = ocupacionesData;});

    //Nivel Académico de la BD para el select
    this.variosService.getNivelAcademico()
    .subscribe(nivelAcademicoData => {this.nivelAcademicoArray = nivelAcademicoData;});

    //Nivel Económico de la BD para el select
    this.variosService.getNivelEconomico()
    .subscribe(nivelEconomicoData => {this.nivelEconomicoArray = nivelEconomicoData;});

    //Lugares de la BD para el select
    this.lugarService.getLugares().subscribe(lugaresData => {
      this.estados = lugaresData.filter(
        (obj) => {
          if(obj._tipo == "Estado"){
            return true;
          }
          return false;
        }
      );
    });

    this.lugarService.getLugares().subscribe(lugaresData => {
      this.municipios = lugaresData.filter(
        (obj) => {
          if(obj._tipo == "Municipio"){
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

  onSubmit(): void{
    this.solicitud2 = {
      id: 1,
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
      estatus: 'Activo',
      nivelEconomicoDto: this.solicitudForm.value.nivelEconomicoDto._id,
      usuarioDto: 1,
      productoDto: this.solicitudForm.value.productoDto._id,
      ocupacionDto: this.solicitudForm.value.ocupacionDto._id,
      nivelAcademicoDto: this.solicitudForm.value.nivelAcademicoDto._id
    };
    this.solicitudEstudiosService.createSolicitud(
      this.solicitud2 as Solicitud2
     ).subscribe();
     console.log(Solicitud2);
     this.router.navigate(['/cliente/estudios']);
   }
   

}
