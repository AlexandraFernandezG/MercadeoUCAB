import { Rol, Rol2 } from './rol';

export class Usuario {
    constructor(
       public _id: number,
       public _nombre: string,
       public _correoelectronico: string,
       public _codigoRecuperacion: number,
       public _estatus: string,
       public _rol: Rol
     ){}
}

export class Usuario2 {
        constructor(
           public id: number,
           public nombreUsuario: string,
           public correo: string,
           public codigoRecuperacion: number,
           public estatus: string,
           public contrasena: string,
           public rolDto: Rol2
         ){}
}

export class UsuarioLDAP {
    constructor(
        public id: number,
        public nombreUsuario: string,
        public correo: string,
        public codigoRecuperacion: number,
        public estatus: string,
        public contrasena: string,
        public rolDto: number
      ){}
}