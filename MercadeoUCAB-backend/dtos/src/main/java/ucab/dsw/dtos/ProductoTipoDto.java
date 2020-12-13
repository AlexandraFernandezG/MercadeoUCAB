package ucab.dsw.dtos;

public class ProductoTipoDto extends DtoBase{

    private ProductoDto productoDto;

    private TipoDto tipoDto;

    public ProductoDto getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    public TipoDto getTipoDto() {
        return tipoDto;
    }

    public void setTipoDto(TipoDto tipoDto) {
        this.tipoDto = tipoDto;
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
