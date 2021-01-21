import { Estudio } from './estudio';
import { Pregunta } from './pregunta';


export class PreguntaEstudio {
    constructor(
       // tslint:disable-next-line: variable-name
       public _id: number,
       // tslint:disable-next-line: variable-name
       public _estatus: string,
       // tslint:disable-next-line: variable-name
       public _estudio: Estudio,
       // tslint:disable-next-line: variable-name
       public _preguntaEncuesta: Pregunta
     ){}
   }
export class PreguntaEstudio2{
    constructor(
      public id: number,
      public estatus: string,
      public estudioDto: number,
      public preguntaEncuestaDto: number
     ){}
     }

export class PreguntaEstudio3{
  constructor(
    public idPreguntaEncuesta: number,
    public pregunta: string,
    public tipoPregunta: string,
 ){}
    }



