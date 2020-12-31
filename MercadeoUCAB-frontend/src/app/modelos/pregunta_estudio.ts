import { Estudio } from './estudio';
import { Pregunta } from './pregunta';


export class PreguntaEstudio {
    constructor(
       public _id: number,
       public _estatus: string,
       public _estudio: Estudio,
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



