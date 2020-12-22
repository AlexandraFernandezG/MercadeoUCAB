import { Presentacion } from "./presentacion";
import { Producto } from "./producto";
import { Tipo } from "./tipo";

export class TipoPresentacion{
    constructor(
       public _id: number,
       public _producto: Producto, 
       public _presentacion: Presentacion, 
       public _tipo: Tipo
     ){}
}

export class TipoPresentacion2{
    constructor(
       public id: number,
       public producto: number, 
       public presentacion: number, 
       public tipo: number
     ){}
}
