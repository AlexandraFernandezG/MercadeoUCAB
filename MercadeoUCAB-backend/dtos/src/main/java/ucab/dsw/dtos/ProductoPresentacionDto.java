package ucab.dsw.dtos;

public class ProductoPresentacionDto extends DtoBase{

    private ProductoDto _productoDto;

    private PresentacionDto _presentacionDto;

    public ProductoDto getProductoDto() {
        return _productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this._productoDto = productoDto;
    }

    public PresentacionDto getPresentacionDto() {
        return _presentacionDto;
    }

    public void setPresentacionDto(PresentacionDto presentacionDto) {
        this._presentacionDto = presentacionDto;
    }

    public ProductoPresentacionDto (long id) throws Exception{
        super(id);
    }

    public ProductoPresentacionDto (String estatus) throws Exception {
        super(estatus);
    }

    public ProductoPresentacionDto (){
        super();
    }
}
