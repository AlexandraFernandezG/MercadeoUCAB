package ucab.dsw.dtos;

public class NivelAcademicoDto extends DtoBase{

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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