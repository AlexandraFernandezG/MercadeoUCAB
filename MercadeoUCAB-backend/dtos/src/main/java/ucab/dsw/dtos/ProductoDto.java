package ucab.dsw.dtos;

public class ProductoDto extends DtoBase{
    
    private String nombre;
    private String descripcion;
    private UsuarioDto usuarioDto;
    private SubcategoriaDto subcategoriaDto;
    private MarcaDto marcaDto;

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

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto subcategoriaDto) {
        this.subcategoriaDto = subcategoriaDto;
    }

    public MarcaDto getMarcaDto() {
        return marcaDto;
    }

    public void setMarcaDto(MarcaDto marcaDto) {
        this.marcaDto = marcaDto;
    }

    public ProductoDto (long id) throws Exception{
        super(id);
    }

    public ProductoDto (String estatus) throws Exception {
        super(estatus);
    }

    public ProductoDto (){
        super();
    }
    
}
