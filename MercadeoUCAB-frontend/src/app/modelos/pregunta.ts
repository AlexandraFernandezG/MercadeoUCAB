import { Subcategoria } from './subcategoria';
import { Usuario } from './usuario';

export class Pregunta {
    constructor(
       public _id: number,
       public _descripcion: string,
       public _tipoPregunta: string,
       public _usuario: Usuario,
       public _subcategoria: Subcategoria,
       public _estatus: string,
     ){}
   }
export class Pregunta2{
     constructor(
        public id: number,
        public descripcion: string,
        public tipoPregunta: string,
        public usuarioDto: number,
        public subcategoriaDto: number,
        public estatus: string,
     ){}
     }
export class Pregunta3{
   constructor(
      public idPregunta: number,
      public descripcionPregunta: string,
      public tipoPregunta: string,
      public estatusPregunta: string,
   ){}
   }

export class  PreguntaEncuesta{
      constructor(
      public idPreguntaEncuesta: number,
      public descripcion: string,
      public tipoPregunta: string,
      public estado?: string,
      public subcategoriaDto?: number,
      public usuarioDto?: number,
      public visible?:boolean,
      public idPreguntaEstudio?: number,
      ){}
    }



