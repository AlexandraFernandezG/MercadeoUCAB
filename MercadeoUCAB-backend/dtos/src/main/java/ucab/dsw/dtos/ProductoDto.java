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

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public UsuarioDto getUsuarioDto() {
        return _usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto _usuarioDto) {
        this._usuarioDto = _usuarioDto;
    }

    public SubcategoriaDto getSubcategoriaDto() {
        return _subcategoriaDto;
    }

    public void setSubcategoriaDto(SubcategoriaDto _subcategoriaDto) {
        this._subcategoriaDto = _subcategoriaDto;
    }

    public MarcaDto getMarcaDto() {
        return _marcaDto;
    }

    public void setMarcaDto(MarcaDto _marcaDto) {
        this._marcaDto = _marcaDto;
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
