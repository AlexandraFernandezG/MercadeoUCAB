package ucab.dsw.dtos;

public class PresentacionDto extends DtoBase{

    private String _nombre;
    private String _descripcion;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
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