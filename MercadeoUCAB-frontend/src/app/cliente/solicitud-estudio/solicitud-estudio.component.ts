import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductosService } from '../../servicios/productos.service';
import { Producto } from '../../modelos/producto';
import { Ocupacion } from 'src/app/modelos/ocupacion';
import { OcupacionService } from 'src/app/servicios/ocupacion.service';
import { Lugar } from '../../modelos/lugar';
import { LugarService } from 'src/app/servicios/lugar.service';


@Component({
  selector: 'app-solicitud-estudio',
  templateUrl: './solicitud-estudio.component.html',
  styleUrls: ['./solicitud-estudio.component.css']
})
export class SolicitudEstudioComponent implements OnInit {

  solicitudForm: FormGroup;
  productos: Producto[];
  ocupaciones: Ocupacion[];
  estados: Lugar[];
  selectedEstado: Lugar = { 
    _id: 0,
    _nombre: '',
    _tipo: '',
    _categoriaSocioEconominca: '',
    _estatus: '',
    _fk_lugar: null,
  }
  municipios: Lugar[];
  allMunicipios: Lugar[];


  constructor(
    private fb: FormBuilder, 
    private router: Router, 
    private productoService: ProductosService,
    private ocupacionService: OcupacionService,
    private lugarService: LugarService,
    ) 
    
    {
    this.solicitudForm = this.fb.group({
      producto: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      descripcion: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      edadMinima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      edadMaxima: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      genero: new FormControl('',[Validators.maxLength(100)]),
      estadoCivil: new FormControl('',[Validators.maxLength(100)]),
      estado: new FormControl('',[Validators.maxLength(100)]),
      municipio: new FormControl('',[Validators.maxLength(100)]),
      nivelEconomico: new FormControl('',[Validators.maxLength(100)]),
      conexion: new FormControl('',[Validators.maxLength(100)]),
      ocupacion: new FormControl('',[Validators.maxLength(100)]),
      nivelAcademico: new FormControl('',[Validators.maxLength(100)]),
      cantidadPersonas: new FormControl('',[Validators.maxLength(100)]),
      cantidadHijos: new FormControl('',[Validators.maxLength(100)]),
      generoHijos: new FormControl('',[Validators.maxLength(100)]),
      edadMinimaHijos: new FormControl('',[Validators.maxLength(100)]),
      edadMaximaHijos: new FormControl('',[Validators.maxLength(100)]),
    });
   }

  ngOnInit(): void {
    //Productos de la BD para el select
    this.productoService.getProductos()
    .subscribe(productosData => {this.productos = productosData;});

    //Ocupaciones de la BD para el select
    this.ocupacionService.getOcupaciones()
    .subscribe(ocupacionesData => {this.ocupaciones = ocupacionesData;});

    //Lugares de la BD para el select
    this.lugarService.getLugares().subscribe(lugaresData => {
      this.estados = lugaresData.filter(
        (obj) => {
          if(obj._tipo == "estado"){
            return true;
          }
          return false;
        }
      );
    });

    this.lugarService.getLugares().subscribe(lugaresData => {
      this.allMunicipios = lugaresData.filter(
        (obj) => {
          if(obj._tipo == "municipio"){
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

  onSelect(_id: any): void{
    this.municipios = this.allMunicipios.filter( item => item._fk_lugar = _id);
    console.log(this.municipios);
  }

  regresarEstudios(): void {
    this.router.navigate(['/cliente/estudios']);
  }

  onSubmit(){
    console.log(this.solicitudForm.value);
   }

}
