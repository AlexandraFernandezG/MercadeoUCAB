import { Ocupacion, Ocupacion2 } from "./ocupacion";
import { Producto, Producto2 } from "./producto";
import { Usuario, Usuario2 } from "./usuario";
import { NivelAcademico, NivelAcademico2, NivelEconomico, NivelEconomico2 } from "./varios";

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
        public nivelEconomicoDto: NivelEconomico2,
        public usuarioDto: Usuario2,
        public productoDto: Producto2,
        public ocupacionDto: Ocupacion2,
        public nivelAcademicoDto: NivelAcademico2
    ){}
}  