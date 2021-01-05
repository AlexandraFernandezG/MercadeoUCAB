package ucab.dsw.dtos;

public class RolDto extends DtoBase{

    private String _nombre;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public RolDto (long id) throws Exception{
        super(id);
    }


    public RolDto (){
        super();
    }
}
