package ucab.dsw.dtos;

public class ProductoDto extends DtoBase{
    
    private String _nombre;
    private String _descripcion;
    private UsuarioDto _usuario;
    private SubcategoriaDto _subcategoriaDto;
    private MarcaDto _marcaDto;

    public String getNombre() {
        return _nombre;
    }

    public String getDescripcion() {
        return _descripcion;
    }


    public UsuarioDto getUsuario() {
        return _usuario;
    }

    public SubcategoriaDto getSubcategoria() {
        return _subcategoriaDto;
    }

    public MarcaDto getMarca() {
        return _marcaDto;
    }


    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public void setUsuario(UsuarioDto _usuario) {
        this._usuario = _usuario;
    }

    public void setSubcategoria(SubcategoriaDto _subcategoria) {
        this._subcategoriaDto = _subcategoria;
    }

    public void setMarca(MarcaDto _marca) {
        this._marcaDto = _marca;
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
