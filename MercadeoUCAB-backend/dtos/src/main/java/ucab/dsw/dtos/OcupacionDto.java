package ucab.dsw.dtos;

public class OcupacionDto extends DtoBase{
    private String _nombre;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public OcupacionDto (long id) throws Exception{
        super(id);
    }

    public OcupacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public OcupacionDto (){
        super();
    }

}