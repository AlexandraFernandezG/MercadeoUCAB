package ucab.dsw.dtos;

public class PresentacionDto extends DtoBase{

    private String _nombre;
    private String _descripcion;

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _caracteristicas) {
        this._descripcion = _caracteristicas;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public PresentacionDto (long id) throws Exception{
        super(id);
    }

    public PresentacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public PresentacionDto (){
        super();
    }
}