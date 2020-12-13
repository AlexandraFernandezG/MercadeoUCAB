import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.ProductoPresentacion;

public class ProductoPresentacionAPI_Test {

    //Listar presentaciones de un producto (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarProductoPresentacion(){

        ucab.dsw.servicio.ProductoPresentacionAPI servicio = new ucab.dsw.servicio.ProductoPresentacionAPI();

        try {
            Assertions.assertTrue(servicio.listarProductoPresentacion(1).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    // Esta prueba permite insertar la relacion Producto presentacion
    @Test
    public void pruebaInsertarProductoPresentacion() throws Exception {

        ucab.dsw.servicio.ProductoPresentacionAPI servicio = new ucab.dsw.servicio.ProductoPresentacionAPI();
        ProductoPresentacionDto productoPresentacionDto = new ProductoPresentacionDto();

        productoPresentacionDto.setEstatus("Activo");
        //Estar mosca con los Id de la base de datos
        ProductoDto productoDto = new ProductoDto(1);
        PresentacionDto presentacionDto = new PresentacionDto(1);
        productoPresentacionDto.setProductoDto(productoDto);
        productoPresentacionDto.setPresentacionDto(presentacionDto);
        ProductoPresentacionDto resultado = servicio.addProductoPresentacion(productoPresentacionDto);
        Assert.assertNotEquals( resultado.getId(), 0 );
    }

    //Actualizar estatus de la relacion Producto presentacion
    @Test
    public void pruebaModificarEstatusProductoPresentacion() {

        ucab.dsw.servicio.ProductoPresentacionAPI servicio = new ucab.dsw.servicio.ProductoPresentacionAPI();
        ProductoPresentacionDto productoPresentacionDto = new ProductoPresentacionDto();

        productoPresentacionDto.setEstatus("Activo");
        //Estar mosca con los Id de la base de datos
        servicio.updateEstatusProductoPresentacion(1, productoPresentacionDto);
    }

    //Eliminar una relacion de producto presentacion
    @Test
    public void pruebaEliminarProductoPresentacion() {

        ucab.dsw.servicio.ProductoPresentacionAPI servicio = new ucab.dsw.servicio.ProductoPresentacionAPI();
        //Estar mosca con los Id de la base de datos
        servicio.deleteProductoPresentacion(1);
    }
}
