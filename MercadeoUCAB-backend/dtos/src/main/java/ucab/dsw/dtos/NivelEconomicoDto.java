package ucab.dsw.dtos;

public class NivelEconomicoDto extends DtoBase{
    
    private String _descripcion;

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }

    public NivelEconomicoDto (long id) throws Exception{
        super(id);
    }

    public NivelEconomicoDto (String estatus) throws Exception {
        super(estatus);
    }

    public NivelEconomicoDto (){
        super();
    }
}
