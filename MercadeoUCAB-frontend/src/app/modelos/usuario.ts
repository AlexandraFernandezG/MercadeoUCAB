import { Rol, Rol2 } from './rol';

export class Usuario {
    constructor(
       public _id: number,
       public _nombre: string,
       public _correoelectronico: string,
       public _codigoRecuperacion: string,
       public _estatus: string,
       public _rol: Rol
     ){}
}

export class Usuario2 {
        constructor(
           public id: number,
           public nombreUsuario: string,
           public correo: string,
           public codigoRecuperacion: string,
           public estatus: string,
           public contrasena: string,
           public rol: number
         ){}
}

export class UsuarioLDAP {
    constructor(
        public id: number,
        public nombreUsuario: string,
        public correo: string,
        public codigoRecuperacion: string,
        public estatus: string,
        public contrasena: string,
        public rol: number
      ){}
}