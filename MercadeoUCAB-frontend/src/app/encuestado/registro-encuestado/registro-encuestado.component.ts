import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Ocupacion } from 'src/app/modelos/ocupacion';
import { OcupacionService } from 'src/app/servicios/ocupacion.service';
import { Lugar } from '../../modelos/lugar';
import { LugarService } from 'src/app/servicios/lugar.service';
import { VariosService } from 'src/app/servicios/varios.service';
import { NivelAcademico, NivelAcademico2, NivelEconomico } from 'src/app/modelos/varios';
import { RegistroEncuestadoService } from 'src/app/servicios/registro-encuestado.service';
import { RegistroEncuestado2 } from 'src/app/modelos/registro-encuestado';

@Component({
  selector: 'app-registro-encuestado',
  templateUrl: './registro-encuestado.component.html',
  styleUrls: ['./registro-encuestado.component.css']
})
export class RegistroEncuestadoComponent implements OnInit {

  registroForm: FormGroup;
  registroEncuestado2: RegistroEncuestado2;
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

  

  constructor(
    private fb: FormBuilder, 
    private router: Router,
    private ocupacionService: OcupacionService,
    private lugarService: LugarService,
    private registroEncuestadoService: RegistroEncuestadoService,
    private variosService: VariosService,
  ) { 
    this.registroForm = this.fb.group({
      cedula: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      primerNombre: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      segundoNombre: new FormControl('',[Validators.maxLength(100)]),
      primerApellido: new FormControl( '',[ Validators.required, Validators.maxLength(150)]),
      segundoApellido: new FormControl('',[Validators.maxLength(100)]),
      fechaNacimiento: new FormControl('',[ Validators.required, Validators.maxLength(50)]),
      genero: new FormControl('',[Validators.required,Validators.maxLength(100)]),
      estadoCivil: new FormControl('',[Validators.maxLength(100)]),
      estado: new FormControl('',[Validators.maxLength(100)]),
      municipio: new FormControl('',[Validators.maxLength(100)]),
      disponibilidadEnLinea: new FormControl('',[Validators.maxLength(100)]),
      cantidadPersonas: new FormControl('',[Validators.maxLength(100)]),
      
      //lugarDto: new FormControl('',[Validators.required,Validators.maxLength(100)]),
      nivelEconomicoDto: new FormControl('',[Validators.maxLength(100)]),
      ocupacionDto: new FormControl('',[Validators.maxLength(100)]),
      nivelAcademicoDto: new FormControl('',[Validators.maxLength(100)]),
    });
  }

  ngOnInit(): void {

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
    
    this.ocupacionService.getOcupaciones().subscribe(ocupacionesData => console.log(ocupacionesData));
    this.lugarService.getLugares().subscribe(lugaresData => console.log(lugaresData));
  }
  onSubmit(){
    this.registroEncuestado2 = {
      id: 1,
      cedula: this.registroForm.value.cedula,
      primerNombre: this.registroForm.value.primerNombre,
      segundoNombre: this.registroForm.value.segundoNombre,
      primerApellido: this.registroForm.value.primerApellido,
      segundoApellido: this.registroForm.value.segundoApellido,
      genero: this.registroForm.value.genero,
      fechaNacimiento: this.registroForm.value.fechaNacimiento,
      estadoCivil: this.registroForm.value.estadoCivil,
      disponibilidadEnLinea: this.registroForm.value.disponibilidadEnLinea,
      cantidadPersonas: this.registroForm.value.cantidadPersonas,
      estatus: 'Activo',
      nivelEconomicoDto: this.registroForm.value.nivelEconomicoDto._id,
      usuarioDto: 17,
      lugarDto: this.registroForm.value.municipio._id,
      ocupacionDto: this.registroForm.value.ocupacionDto._id,
      nivelAcademicoDto: this.registroForm.value.nivelAcademicoDto._id
    };
    this.registroEncuestadoService.createRegistro(
      this.registroEncuestado2 as RegistroEncuestado2
     ).subscribe();
     console.log(this.registroEncuestado2);
    // this.router.navigate(['/cliente/estudios']);
   
  };
}
