import { Solicitud } from './solicitud';
import { Usuario } from './usuario';

export class Estudio {
    // tslint:disable-next-line: variable-name
    _id: number;
    // tslint:disable-next-line: variable-name
    _nombre: string;
    // tslint:disable-next-line: variable-name
    _tipoInstrumento: string;
    // tslint:disable-next-line: variable-name
    _fechaInicio: Date;
    // tslint:disable-next-line: variable-name
    _fechaFin: Date;
    // tslint:disable-next-line: variable-name
    _estatus: string;
    // tslint:disable-next-line: variable-name
    _estado: string;
    // tslint:disable-next-line: variable-name
    _usuario: Usuario;
    // tslint:disable-next-line: variable-name
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
