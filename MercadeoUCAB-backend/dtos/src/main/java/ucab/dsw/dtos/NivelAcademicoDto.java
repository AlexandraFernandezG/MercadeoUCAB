package ucab.dsw.dtos;

public class NivelAcademicoDto extends DtoBase{
    private String _descripcion;

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public NivelAcademicoDto (long id) throws Exception{
        super(id);
    }

    public NivelAcademicoDto (String estatus) throws Exception {
        super(estatus);
    }

    public NivelAcademicoDto (){
        super();
    }
}