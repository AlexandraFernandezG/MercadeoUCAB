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



