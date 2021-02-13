package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoProductoPresentacionTipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ProductoPresentacionTipoDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.ProductoPresentacionTipo;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ucab.dsw.fabrica.Fabrica;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/ProductoPresentacionTipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoPresentacionTipoServicio extends AplicacionBase{

    private static Logger logger = LoggerFactory.getLogger(ProductoPresentacionTipoServicio.class);

    /**
     * Este método permite obtener los tipos de un producto.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el o los tipos de un producto y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del producto que se quiere consultar
     *
     */
    @GET
    @Path("/productoTipoLista/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarTiposProducto(@PathParam("id") long id)  {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los tipos de un producto");
        DaoProductoPresentacionTipo dao = Fabrica.crear(DaoProductoPresentacionTipo.class);
        List<ProductoPresentacionTipo> listaProductoPT = dao.findAll(ProductoPresentacionTipo.class);
        List<Tipo> listaTiposProducto = new ArrayList<Tipo>();
        JsonObject dataObject;

        try {

            for (ProductoPresentacionTipo productoPresentacionTipo : listaProductoPT) {

                if (productoPresentacionTipo.get_producto().get_id() == id) {

                    long idFK = productoPresentacionTipo.get_tipo().get_id();
                    DaoTipo daoTipo = Fabrica.crear(DaoTipo.class);
                    Tipo tipo = daoTipo.find(idFK, Tipo.class);
                    listaTiposProducto.add(tipo);
                }
            }

            logger.debug("Saliendo del método que lista todos los tipos de un producto");
            return Response.status(Response.Status.OK).entity(listaTiposProducto).build();

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
     * Este método permite obtener las presentaciones de un producto.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la o las presentaciones de un producto y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del producto que se quiere consultar
     *
     */
    @GET
    @Path("/productoPresentacionLista/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPresentacionesProducto(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las presentaciones de un producto");
        DaoProductoPresentacionTipo dao = Fabrica.crear(DaoProductoPresentacionTipo.class);
        List<ProductoPresentacionTipo> listaProductoPT = dao.findAll(ProductoPresentacionTipo.class);
        List<Presentacion> listaPresentacionProducto = new ArrayList<Presentacion>();
        JsonObject dataObject;

        try {

            for (ProductoPresentacionTipo productoPresentacionTipo : listaProductoPT) {

                if (productoPresentacionTipo.get_producto().get_id() == id) {

                    long idFK = productoPresentacionTipo.get_presentacion().get_id();
                    DaoPresentacion daoPresentacion = Fabrica.crear(DaoPresentacion.class);
                    Presentacion presentacion = daoPresentacion.find(idFK, Presentacion.class);
                    listaPresentacionProducto.add(presentacion);
                }
            }

            BasicConfigurator.configure();
            logger.debug("Saliendo del método que lista todas las presentaciones de un producto");
            return Response.status(Response.Status.OK).entity(listaPresentacionProducto).build();

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
     * Este método permite insertar la relacion producto presentacion tipo.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la relacion insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una relacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param productoPresentacionTipoDto el objeto de relacion que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addProductoPresentacionTipo")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addProductoPresentacionTipo(ProductoPresentacionTipoDto productoPresentacionTipoDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que añade Presentación");
        ProductoPresentacionTipoDto resultado = Fabrica.crear(ProductoPresentacionTipoDto.class);
        JsonObject dataObject;

        try {

            DaoProductoPresentacionTipo dao = Fabrica.crear(DaoProductoPresentacionTipo.class);
            ProductoPresentacionTipo productoPresentacionTipo = Fabrica.crear(ProductoPresentacionTipo.class);
            DaoProducto daoProducto = Fabrica.crear(DaoProducto.class);
            DaoPresentacion daoPresentacion = Fabrica.crear(DaoPresentacion.class);
            DaoTipo daoTipo = Fabrica.crear(DaoTipo.class);

            productoPresentacionTipo.set_estatus(productoPresentacionTipoDto.getEstatus());
            Producto producto = daoProducto.find(productoPresentacionTipoDto.getProductoDto().getId(), Producto.class);
            Presentacion presentacion = daoPresentacion.find(productoPresentacionTipoDto.getPresentacionDto().getId(), Presentacion.class);
            Tipo tipo = daoTipo.find(productoPresentacionTipoDto.getTipoDto().getId(), Tipo.class);
            productoPresentacionTipo.set_producto(producto);
            productoPresentacionTipo.set_presentacion(presentacion);
            productoPresentacionTipo.set_tipo(tipo);
            ProductoPresentacionTipo resul = dao.insert(productoPresentacionTipo);
            resultado.setId(resul.get_id());

            logger.debug("Saliendo del método que añade Presentación");
            return Response.status(Response.Status.OK).entity(resultado).build();

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

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite modificar el estatus de la relacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la relacion modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una relacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param productoPresentacionTipoDto el objeto relacion que el sistema desea modificar.
     * @param id el id de la relacion a modificar
     */
    @PUT
    @Path("/updateProductoPresentacionTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateEstatusProductoPresentacionTipo(@PathParam("id") long id, ProductoPresentacionTipoDto productoPresentacionTipoDto){

            BasicConfigurator.configure();
            logger.debug("Ingresando al método que actualiza ProductoPresentacionTipo");
           DaoProductoPresentacionTipo dao = Fabrica.crear(DaoProductoPresentacionTipo.class);
           ProductoPresentacionTipo productoPresentacionTipo_modificar = dao.find(id, ProductoPresentacionTipo.class);
           JsonObject dataObject;

           try {

               productoPresentacionTipo_modificar.set_estatus(productoPresentacionTipoDto.getEstatus());
               logger.debug("Saliendo del método que actualiza ProductoPresentacionTipo");
               dao.update(productoPresentacionTipo_modificar);

               return Response.status(Response.Status.OK).entity(productoPresentacionTipo_modificar).build();

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
     * Este método permite eliminar la relacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se elimina una relacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la relacion a elminar.
     */
    @DELETE
    @Path("/deleteProductoPresentacionTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProductoPresentacionTipo(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Entrando al método que eliminar ProductoPresentacionTipo");
        DaoProductoPresentacionTipo dao = Fabrica.crear(DaoProductoPresentacionTipo.class);
        ProductoPresentacionTipo productoPresentacionTipo_eliminar = dao.find(id, ProductoPresentacionTipo.class);
        JsonObject dataObject;

        try {

            dao.delete(productoPresentacionTipo_eliminar);
            logger.debug("Saliendo del método que eliminar ProductoPresentacionTipo");
            return Response.status(Response.Status.OK).entity(productoPresentacionTipo_eliminar).build();

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
