package ucab.dsw.dtos;

public class SubcategoriaDto extends DtoBase{
    
    private String nombre;
    private String descripcion;
    private CategoriaDto categoriaDto;

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

    public CategoriaDto getCategoriaDto() {
        return categoriaDto;
    }

    public void setCategoriaDto(CategoriaDto categoriaDto) {
        this.categoriaDto = categoriaDto;
    }

    public SubcategoriaDto (long id) throws Exception{
        super(id);
    }

    public SubcategoriaDto (String estatus) throws Exception {
        super(estatus);
    }

    public SubcategoriaDto (){
        super();
    }
    
}
