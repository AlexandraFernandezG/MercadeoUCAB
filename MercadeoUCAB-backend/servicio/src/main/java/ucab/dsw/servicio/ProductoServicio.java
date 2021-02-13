package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.Producto.AddProductoComando;
import ucab.dsw.comando.Producto.ConsultarProductosClienteComando;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.Response.ProductoResponse;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperProducto;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/producto" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoServicio extends AplicacionBase{

    private static Logger logger = LoggerFactory.getLogger(ProductoServicio.class);

    /**
     * Este método permite obtener todos los productos.
     * @author Emanuel Di Cristofaro
     * @return Este método retorna un objeto de tipo Json con el
     * arreglo de productos y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allProductos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarProductos() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los productos");
        JsonObject dataObject;
        DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);

        try {

            List<Producto> listaProductos = daoProducto.findAll(Producto.class);
            logger.debug("Saliendo del método que lista todos los productos");
            return Response.status(Response.Status.OK).entity(listaProductos).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener los productos de un cliente.
     * @author Emanuel Di Cristofaro
     * @param id El id del cliente.
     */
    @GET
    @Path("/consultarProductosCliente/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarProductosCliente(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que consulta todos los productos de un cliente");
        JsonObject dataObject;

        try {

            ConsultarProductosClienteComando comando = Fabrica.crearComandoConId(ConsultarProductosClienteComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta todos los productos de un cliente");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener un producto.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el  producto consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del producto que se quiere consultar
     *
     */
    @GET
    @Path("/consultarProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarProducto(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que consulta un producto");
        JsonObject dataObject;
        DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);

        try {

            Producto producto_consultado = daoProducto.find(id, Producto.class);
            logger.debug("Saliendo del método que consulta un producto");
            return Response.status(Response.Status.OK).entity(producto_consultado).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener todos los productos activos.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de productos activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarProductosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response productosActivos() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que te lista productos activos");
        DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);
        List<Producto> listaProducto = daoProducto.findAll(Producto.class);
        List<Producto> listaProductosActivos = new ArrayList<Producto>();
        JsonObject dataObject;

        try {

            for (Producto producto : listaProducto) {

                if (producto.get_estatus().equals("Activo")) {
                    listaProductosActivos.add(producto);
                }
            }

            logger.debug("Saliendo del método que te lista productos activos");
            return Response.status(Response.Status.OK).entity(listaProductosActivos).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite insertar un producto
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el producto insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param productoDto el objeto producto que el sistema desea insertar o crear.
     *
     */
    @POST
    @Path("/addProducto")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addProducto(ProductoDto productoDto) throws Exception {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que insertar un producto");
        JsonObject dataObject;

        try {

            Producto producto = MapperProducto.mapDtoToEntityInsert(productoDto);
            AddProductoComando comando = Fabrica.crearComandoConEntity(AddProductoComando.class, producto);
            comando.execute();

            logger.debug("Saliendo del método que insertar un producto");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException ex) {

            logger.error("Código de error: " + 600 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 600).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InstantiationException ex) {
            logger.error("Código de error: " + 601 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 601).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InvocationTargetException ex) {
            logger.error("Código de error: " + 602 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 602).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
        }
    }

    /**
     * Este método permite modificar un producto
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el producto modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param productoDto el objeto producto que el sistema desea modificar.
     * @param id el id del producto que se desee modificar.
     */
    @PUT
    @Path("/updateProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarProducto(@PathParam("id") long id, ProductoDto productoDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualiza un producto");
        JsonObject dataObject;
        DaoProducto daoProducto = new DaoProducto();

            try {

                Producto producto_modificar = daoProducto.find(id, Producto.class);

                producto_modificar.set_nombre(productoDto.getNombre());
                producto_modificar.set_descripcion(productoDto.getDescripcion());
                producto_modificar.set_estatus(productoDto.getEstatus());
                daoProducto.update(producto_modificar);

                logger.debug("Saliendo del método que actualiza un producto");
                return Response.status(Response.Status.OK).entity(producto_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar un producto.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del producto que se desee eliminar.
     */
    @DELETE
    @Path("/deleteProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProducto(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina un producto");
        JsonObject dataObject;
        DaoProducto daoProducto = new DaoProducto();

            try {

                Producto producto_eliminar = daoProducto.find(id, Producto.class);
                daoProducto.delete(producto_eliminar);
                logger.debug("Saliendo del método que elimina un producto");
                return Response.status(Response.Status.OK).entity(producto_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }
}
