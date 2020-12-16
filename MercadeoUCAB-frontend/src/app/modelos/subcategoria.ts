import { Categoria, Categoria2 } from './categoria';

export class Subcategoria {
    constructor(
       public _id: number,
       public _estatus: string,
       public _descripcion: string,
       public _nombre: string,
       public _categoria: Categoria
     ){}
   }
export class Subcategoria2{
     constructor(
       public id: number,
       public estatus: string,
       public descripcion: string,
       public nombre: string,
       public categoriaDto: number
     ){}
     }