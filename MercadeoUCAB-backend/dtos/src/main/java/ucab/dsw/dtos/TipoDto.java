package ucab.dsw.dtos;

public class TipoDto extends DtoBase{

    private String _nombre;
    private String _descripcion;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public TipoDto (long id) throws Exception{
        super(id);
    }

    public TipoDto (String estatus) throws Exception {
        super(estatus);
    }

    public TipoDto (){
        super();
    }
}