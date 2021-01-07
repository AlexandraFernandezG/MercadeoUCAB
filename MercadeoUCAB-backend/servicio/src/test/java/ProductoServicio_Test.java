import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.servicio.ProductoServicio;

public class ProductoServicio_Test {

    //Listar todos los productos
    @Test
    public void pruebaListarProductos(){

        ProductoServicio servicio = new ProductoServicio();

        try {
            Assertions.assertTrue(servicio.listarProductos().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un producto
    @Test
    public void pruebaConsultarProducto(){

        ProductoServicio servicio = new ProductoServicio();
        Producto producto_buscar = servicio.consultarProducto(1);

        try {
            Assertions.assertEquals(1, producto_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar productos activos
    @Test
    public void pruebaListarProductosActivos(){

        try {
            Assertions.assertNotNull(new ProductoServicio().productosActivos());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba para insertar un Producto
    @Test
    public void pruebaInsertarProducto() throws Exception {

        ProductoServicio servicio = new ProductoServicio();

        try {
            ProductoDto productoDto = new ProductoDto();

            productoDto.setNombre("");
            productoDto.setDescripcion("");
            productoDto.setEstatus("Activo");
            //Revisar los ID de los registros de la BD
            UsuarioDto usuarioDto = new UsuarioDto(1);
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);
            MarcaDto marcaDto = new MarcaDto(1);
            productoDto.setUsuarioDto(usuarioDto);
            productoDto.setSubcategoriaDto(subcategoriaDto);
            productoDto.setMarcaDto(marcaDto);
            ProductoDto resultado = servicio.addProducto(productoDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Modificar el producto
    @Test
    public void pruebaModificarProducto(){

        ProductoServicio servicio = new ProductoServicio();

        try {
            ProductoDto productoDto = new ProductoDto();

            productoDto.setNombre("");
            productoDto.setDescripcion("");
            productoDto.setEstatus("Activo");
            //Revisar los ID de los registros de la BD
            servicio.modificarProducto(1, productoDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }


    }

    //Eliminar el producto
    @Test
    public void pruebaEliminarProducto(){

        ProductoServicio servicio = new ProductoServicio();

        try {
            //Revisar los ID de los registros de la BD
            servicio.deleteProducto(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
