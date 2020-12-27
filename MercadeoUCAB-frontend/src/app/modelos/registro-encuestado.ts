import { Lugar } from "./lugar";
import { Ocupacion } from "./ocupacion";
import { Producto } from "./producto";
import { Usuario } from "./usuario";
import { NivelAcademico, NivelEconomico } from "./varios";

export class RegistroEncuestado{
    constructor(
        public _id: number,
        public _cedula: number,
        public _primerNombre: string,
        public _segundoNombre: string,
        public _primerApellido: string,
        public _segundoApellido: string,
        public _genero: string,
        public _fechaNacimiento: Date,
        public _estadoCivil: string,
        public _disponibilidadEnLinea: string,
        public _cantidadPersonas: number,
        //public _estatus: string,
        public _nivelEconomico: NivelEconomico,
        public _usuario: Usuario,
        public _lugar: Lugar,
        public _nivelAcademico: NivelAcademico,
        public _ocupacion: Ocupacion,
    ){}
}
   
export class RegistroEncuestado2{
    constructor(
        public id: number,
        public cedula: number,
        public primerNombre: string,
        public segundoNombre: string,
        public primerApellido: string,
        public segundoApellido: string,
        public genero: string,
        public fechaNacimiento: Date,
        public estadoCivil: string,
        public disponibilidadEnLinea: string,
        public cantidadPersonas: number,
        //public estatus: string,
        public nivelEconomicoDto: number,
        public usuarioDto: number,
        public lugarDto: number,
        public nivelAcademicoDto: number,
        public ocupacionDto: number,
    ){}
}  