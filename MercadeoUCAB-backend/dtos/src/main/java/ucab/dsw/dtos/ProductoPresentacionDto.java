package ucab.dsw.dtos;

public class ProductoPresentacionDto extends DtoBase{

    private ProductoDto productoDto;

    private PresentacionDto presentacionDto;

    public ProductoDto getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    public PresentacionDto getPresentacionDto() {
        return presentacionDto;
    }

    public void setPresentacionDto(PresentacionDto presentacionDto) {
        this.presentacionDto = presentacionDto;
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
