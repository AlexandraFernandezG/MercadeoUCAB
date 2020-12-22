import { SubcategoriasService } from "../servicios/subcategorias.service";
import { Marca } from "./marca";
import { Presentacion } from "./presentacion";
import { Subcategoria } from "./subcategoria";
import { Tipo } from "./tipo";
import { Usuario } from "./usuario";

export class Producto {
    _id: number;
    _nombre: string;
    _descripcion: string;
    _estatus: string;
    _marca: Marca;
  //  _usuario: Usuario;
    _subcategoria: Subcategoria;
}
export class Producto2 {
    id: number;
    nombre: string;
    descripcion: string;
    estatus: string;
    marcaDto: number;
  //  usuarioDto:number;
    subcategoriaDto: number;

}

export class ProductoTipoPresentacion  {
    _id: number;
    _estatus: string;
    _producto: Producto;
    _presentacion: Presentacion;
    _tipo: Tipo;
}
export class ProductoTipoPresentacion2{
    id: number;
    estatus: string;
    productoDto: number;
    presentacionDto: number;
    tipoDto: number;
}
