package ucab.dsw.dtos;

public class ProductoTipoDto extends DtoBase{

    private ProductoDto _productoDto;

    private TipoDto _tipoDto;

    public TipoDto get_tipoDto() {
        return _tipoDto;
    }

    public void set_tipoDto(TipoDto _tipoDto) {
        this._tipoDto = _tipoDto;
    }

    public ProductoDto get_productoDto() {
        return _productoDto;
    }

    public void set_productoDto(ProductoDto _productoDto) {
        this._productoDto = _productoDto;
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
