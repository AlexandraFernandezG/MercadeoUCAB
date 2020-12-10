package ucab.dsw.dtos;

public class PresentacionDto extends DtoBase{
    private String _nombre;
    private String _caracteristicas;
    private ProductoDto _productoDto;

    public String getCaracteristicas() {
        return _caracteristicas;
    }

    public void setCaracteristicas(String _caracteristicas) {
        this._caracteristicas = _caracteristicas;
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

    public PresentacionDto (long id) throws Exception{
        super(id);
    }

    public PresentacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public PresentacionDto (){
        super();
    }
}