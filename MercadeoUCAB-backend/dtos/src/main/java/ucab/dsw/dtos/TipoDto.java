package ucab.dsw.dtos;

public class TipoDto extends DtoBase{

    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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