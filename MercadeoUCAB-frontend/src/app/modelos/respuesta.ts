import { PreguntaEstudio } from "./pregunta_estudio";
import { Usuario } from "./usuario";

export class Respuesta {
    constructor(
       public _id: number,
       public _estatus: string,
       public _respuestaAbierta: string,
       public _escala: string,
       public _verdaderoFalso: string,
       public _respuestaSimple: string,
       public _respuestaMultiple: string,
       public _usuario: Usuario,
       public _preguntaEstudio: PreguntaEstudio
       
     ){}
   }
export class Respuesta2{
     constructor(
       public estatus: string,
       public usuarioDto: number,
       public preguntaEstudioDto: number,
       public id?: number,
       public respuestaAbierta?: string,
       public escala?: string,
       public verdaderoFalso?: string,
       public respuestaSimple?: string,
       public respuestaMultiple?: string,
       public completado?: boolean,
     ){}
     }