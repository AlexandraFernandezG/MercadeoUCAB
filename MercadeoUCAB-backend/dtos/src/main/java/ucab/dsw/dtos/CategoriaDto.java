package ucab.dsw.dtos;

public class CategoriaDto extends DtoBase{
    
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

    public CategoriaDto (long id) throws Exception{
        super(id);
    }

    public CategoriaDto (String estatus) throws Exception {
        super(estatus);
    }

    public CategoriaDto (){
        super();
    }
}
