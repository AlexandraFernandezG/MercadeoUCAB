import { Pregunta, Pregunta2} from './pregunta';


export class respuestaPregunta {
    constructor(
       public _id: number,
       public _nombre: string,
       public _estatus: string,
       public _preguntaEncuesta: Pregunta
     ){}
   }
export class respuestaPregunta2{
     constructor(
        public id: number,
        public nombre: string,
        public estatus: string,
        public preguntaEncuestaDto: number,
     ){}
     }

export class respuestaPregunta3{
      constructor(
         public pregunta: string,
         public fkPregunta: number,
         public id?: number,
         public estatus?: string,
         public completado?: boolean,
      ){}
      }
 



