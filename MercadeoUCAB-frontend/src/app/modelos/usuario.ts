export class Usuario {
    constructor(
       public _id: number,
       public _nombreUsuario: string,
       public _correo: string,
       public _codigoRecuperacion: number,
       public _estatus: string,
       public _fk_rol: number
     ){}
}

export class Usuario2 {
        constructor(
           public id: number,
           public nombreUsuario: string,
           public correo: string,
           public codigoRecuperacion: number,
           public estatus: string,
           public fk_rol: number
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
        public fk_rol: number
      ){}
}