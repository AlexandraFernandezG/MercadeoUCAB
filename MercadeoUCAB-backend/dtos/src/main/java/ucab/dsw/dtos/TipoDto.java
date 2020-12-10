package ucab.dsw.dtos;

public class TipoDto extends DtoBase{
    private String _nombre;
    private String _descripcion;
    private ProductoDto _productoDto;

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setProductoDto(ProductoDto _productoDto) {
        this._productoDto = _productoDto;
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