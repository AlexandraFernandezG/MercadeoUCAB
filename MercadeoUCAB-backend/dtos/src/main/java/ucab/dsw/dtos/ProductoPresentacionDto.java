package ucab.dsw.dtos;

public class ProductoPresentacionDto extends DtoBase{

    private ProductoDto _productoDto;

    private PresentacionDto _presentacionDto;

    public ProductoDto get_productoDto() {
        return _productoDto;
    }

    public void set_productoDto(ProductoDto _productoDto) {
        this._productoDto = _productoDto;
    }

    public PresentacionDto get_presentacionDto() {
        return _presentacionDto;
    }

    public void set_presentacionDto(PresentacionDto _presentacionDto) {
        this._presentacionDto = _presentacionDto;
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
