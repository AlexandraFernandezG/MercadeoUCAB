import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.servicio.ProductoServicio;

import javax.ws.rs.core.Response;

public class ProductoServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarProductos
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarProductos(){

        ProductoServicio servicio = new ProductoServicio();
        Response respuesta = servicio.listarProductos();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarProductosClient
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarProductosCliente(){

        ProductoServicio servicio = new ProductoServicio();
        Response respuesta = servicio.consultarProductosCliente(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarProducto
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarProducto(){

        ProductoServicio servicio = new ProductoServicio();
        Response respuesta = servicio.consultarProducto(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarProductosActivos
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarProductosActivos(){

        ProductoServicio servicio = new ProductoServicio();
        Response respuesta = servicio.productosActivos();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarProducto
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarProducto() {

        ProductoServicio servicio = new ProductoServicio();

        try {

            ProductoDto productoDto = new ProductoDto();

            productoDto.setNombre("Pizzas Bob");
            productoDto.setDescripcion("Pizzas bob el constructor");
            productoDto.setEstatus("Activo");
            //Revisar los ID de los registros de la BD
            UsuarioDto usuarioDto = new UsuarioDto(2);
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto(2);
            MarcaDto marcaDto = new MarcaDto(1);
            productoDto.setUsuarioDto(usuarioDto);
            productoDto.setSubcategoriaDto(subcategoriaDto);
            productoDto.setMarcaDto(marcaDto);
            Response resultado = servicio.addProducto(productoDto);
            Assert.assertEquals(resultado.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del ModificarProducto
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarProducto(){

        ProductoServicio servicio = new ProductoServicio();

        try {

            ProductoDto productoDto = new ProductoDto();

            productoDto.setNombre("Pizzas Bob");
            productoDto.setDescripcion("Pizzas deliciosas");
            productoDto.setEstatus("Activo");
            //Revisar los ID de los registros de la BD
            Response resultado = servicio.modificarProducto(4, productoDto);
            Assert.assertEquals(resultado.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }


    }

    /**
     * Prueba unitaria para verificar el funcionamiento del EliminarProducto
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarProducto(){

        ProductoServicio servicio = new ProductoServicio();

        try {

            //Revisar los ID de los registros de la BD
            Response resultado = servicio.deleteProducto(4);
            Assert.assertEquals(resultado.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
