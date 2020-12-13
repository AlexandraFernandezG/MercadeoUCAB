package ucab.dsw.dtos;

public class SubcategoriaDto extends DtoBase{
    
    private String _nombre;
    private String _descripcion;
    private CategoriaDto _categoriaDto;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String nombre) {
        this._nombre = nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String descripcion) {
        this._descripcion = descripcion;
    }

    public CategoriaDto getCategoriaDto() {
        return _categoriaDto;
    }

    public void setCategoriaDto(CategoriaDto categoriaDto) {
        this._categoriaDto = categoriaDto;
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
