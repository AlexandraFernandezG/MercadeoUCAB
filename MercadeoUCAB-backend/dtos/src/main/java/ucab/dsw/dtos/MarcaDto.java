package ucab.dsw.dtos;

public class MarcaDto extends DtoBase {
    
    private String _nombre;

    public String getNombre() {
        return _nombre;
    }


    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public MarcaDto (long id) throws Exception{
        super(id);
    }

    public MarcaDto (String estatus) throws Exception {
        super(estatus);
    }

    public MarcaDto (){
        super();
    }
}
