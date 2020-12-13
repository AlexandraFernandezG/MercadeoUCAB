package ucab.dsw.dtos;

public class ProductoDto extends DtoBase{
    
    private String _nombre;
    private String _descripcion;
    private UsuarioDto _usuarioDto;
    private SubcategoriaDto _subcategoriaDto;
    private MarcaDto _marcaDto;

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

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this._usuarioDto = usuarioDto;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return _subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto subcategoriaDto) {
        this._subcategoriaDto = subcategoriaDto;
    }

    public MarcaDto getMarcaDto() {
        return _marcaDto;
    }

    public void setMarcaDto(MarcaDto marcaDto) {
        this._marcaDto = marcaDto;
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
