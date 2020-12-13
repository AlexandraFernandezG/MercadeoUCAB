package ucab.dsw.dtos;

public class RolDto extends DtoBase{

    private String _nombre;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public RolDto (long id) throws Exception{
        super(id);
    }

    public RolDto (String estatus) throws Exception {
        super(estatus);
    }

    public RolDto (){
        super();
    }
}
