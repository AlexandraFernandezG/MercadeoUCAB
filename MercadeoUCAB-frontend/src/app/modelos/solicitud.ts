import { Ocupacion } from "./ocupacion";
import { Producto } from "./producto";
import { Usuario } from "./usuario";
import { NivelAcademico, NivelEconomico } from "./varios";

export class Solicitud{
    constructor(
        public _id: number,
        public _descripcion: string,
        public _genero: string,
        public _edadMinima: number,
        public _edadMaxima: number,
        public _estadoCivil: string,
        public _disponibilidadEnLinea: string,
        public _cantidadPersonas: number,
        public _cantidadHijos: number,
        public _generoHijos: string,
        public _edadMinimaHijos: number,
        public _edadMaximaHijos: number,
        public _estatus: string,
        public _estado: string,
        public _nivelEconomico: NivelEconomico,
        public _usuario: Usuario,
        public _producto: Producto,
        public _ocupacion: Ocupacion,
        public _nivelAcademico: NivelAcademico
    ){}
}
   
export class Solicitud2{
    constructor(
        public id: number,
        public descripcion: string,
        public genero: string,
        public edadMinima: number,
        public edadMaxima: number,
        public estadoCivil: string,
        public disponibilidadEnLinea: string,
        public cantidadPersonas: number,
        public cantidadHijos: number,
        public generoHijos: string,
        public edadMinimaHijos: number,
        public edadMaximaHijos: number,
        public estatus: string,
        public estado: string,
        public nivelEconomicoDto: number,
        public usuarioDto: number,
        public productoDto: number,
        public ocupacionDto: number,
        public nivelAcademicoDto: number
    ){}
}  