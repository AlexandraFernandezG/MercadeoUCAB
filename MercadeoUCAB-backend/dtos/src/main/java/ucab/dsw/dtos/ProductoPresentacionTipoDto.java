package ucab.dsw.dtos;

public class ProductoPresentacionTipoDto extends DtoBase{

    private ProductoDto _productoDto;
    private TipoDto _tipoDto;
    private PresentacionDto _presentacionDto;

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setProductoDto(ProductoDto _productoDto) {
        this._productoDto = _productoDto;
    }

    public TipoDto getTipoDto() {
        return _tipoDto;
    }

    public void setTipoDto(TipoDto _tipoDto) {
        this._tipoDto = _tipoDto;
    }

    public PresentacionDto getPresentacionDto() {
        return _presentacionDto;
    }

    public void setPresentacionDto(PresentacionDto _presentacionDto) {
        this._presentacionDto = _presentacionDto;
    }

    public ProductoPresentacionTipoDto (long id) throws Exception {
        super(id);
    }

    public ProductoPresentacionTipoDto (String estatus) throws Exception {
        super(estatus);
    }

    public ProductoPresentacionTipoDto (){
        super();
    }
}
