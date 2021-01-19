import { Solicitud } from "./solicitud";
import { Usuario } from "./usuario";

export class Estudio {
    _id: number;
    _nombre: string;
    _tipoInstrumento: string;
    _fechaInicio: Date;
    _fechaFin: Date;
    _estatus: string;
    _estado: string;
    _usuario: Usuario;
    _solicitudEstudio: Solicitud;
}
export class Estudio2 {
    id: number;
    nombre: string;
    tipoInstrumento: string;
    fechaInicio: Date;
    fechaFin: Date;
    estatus: string;
    estado: string;
    usuarioDto: number;
    solicitudEstudioDto: number;
}
