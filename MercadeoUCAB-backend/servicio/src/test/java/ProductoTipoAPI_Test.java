import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.*;

public class ProductoTipoAPI_Test {

    //Listar tipos de un producto (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarProductoTipo(){

        ucab.dsw.servicio.ProductoTipoAPI servicio = new ucab.dsw.servicio.ProductoTipoAPI();

        try {
            Assertions.assertTrue(servicio.listarProductoTipo(1).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    // Esta prueba permite insertar la relacion Producto tipo
    @Test
    public void pruebaInsertarProductoTipo() throws Exception {

        ucab.dsw.servicio.ProductoTipoAPI servicio = new ucab.dsw.servicio.ProductoTipoAPI();
        ProductoTipoDto productoTipoDto = new ProductoTipoDto();

        productoTipoDto.setEstatus("Activo");
        //Estar mosca con los Id de la base de datos
        ProductoDto productoDto = new ProductoDto(1);
        TipoDto tipoDto = new TipoDto(1);
        productoTipoDto.setProductoDto(productoDto);
        productoTipoDto.setTipoDto(tipoDto);
        ProductoTipoDto resultado = servicio.addProductoTipo(productoTipoDto);
        Assert.assertNotEquals( resultado.getId(), 0 );
    }

    //Actualizar el estatus de la relacion producto tipo
    @Test
    public void pruebaModificarEstatusProductoTipo(){

        ucab.dsw.servicio.ProductoTipoAPI servicio = new ucab.dsw.servicio.ProductoTipoAPI();
        ProductoTipoDto productoTipoDto = new ProductoTipoDto();
        productoTipoDto.setEstatus("Activo");
        //Estar mosca con los Id de la base de datos
        servicio.updateEstatusProductoTipo(1,productoTipoDto);

    }

    //Eliminar la relacion producto tipo
    @Test
    public void pruebaEliminarProductoTipo(){

        ucab.dsw.servicio.ProductoTipoAPI servicio = new ucab.dsw.servicio.ProductoTipoAPI();
        ProductoTipoDto productoTipoDto = new ProductoTipoDto();
        //Estar mosca con los Id de la base de datos
        servicio.deleteProductoTipo(1);

    }
}
