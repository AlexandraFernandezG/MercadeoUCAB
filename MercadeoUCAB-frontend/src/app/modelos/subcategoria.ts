import { Categoria } from './categoria';

export class Subcategoria {
    constructor(
       public _id: number,
       public _estatus: string,
       public _descripcion: string,
       public _nombre: string,
       public _fk_categoria: Categoria
     ){}
   }
export class Subcategoria2{
     constructor(
       public id: number,
       public estatus: string,
       public descripcion: string,
       public nombre: string,
       public fk_categoria: Categoria
     ){}
     }