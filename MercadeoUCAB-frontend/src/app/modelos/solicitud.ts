export class Solicitud2{
    constructor(
      public _id: number,
      public _descricion: string,
      public _genero: string,
      public _edadMinima: string,
      public _edadMaxima: string,
      public _estadoCivil: string,
      public _disponibilidadEnLinea: string,
      public _cantidadPersonas: string,
      public _cantidadHijos: string,
      public _generoHijos: string,
      public _edadMinimaHijos: string,
      public _edadMaximaHijos: string,
      public _estatus: string
    ){}
}
   
export class Solicitud{
    constructor(
        public id: number,
        public descricion: string,
        public genero: string,
        public edadMinima: string,
        public edadMaxima: string,
        public estadoCivil: string,
        public disponibilidadEnLinea: string,
        public cantidadPersonas: string,
        public cantidadHijos: string,
        public generoHijos: string,
        public edadMinimaHijos: string,
        public edadMaximaHijos: string,
        public estatus: string
    ){}
}  