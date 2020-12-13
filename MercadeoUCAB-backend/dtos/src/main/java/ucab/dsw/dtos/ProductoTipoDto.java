package ucab.dsw.dtos;

public class ProductoTipoDto extends DtoBase{

    private ProductoDto _productoDto;

    private TipoDto _tipoDto;

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this._productoDto = productoDto;
    }

    public TipoDto getTipoDto() {
        return _tipoDto;
    }

    public void setTipoDto(TipoDto tipoDto) {
        this._tipoDto = tipoDto;
    }

    public ProductoTipoDto (long id) throws Exception{
        super(id);
    }

    public ProductoTipoDto (String estatus) throws Exception {
        super(estatus);
    }

    public ProductoTipoDto (){
        super();
    }
}
