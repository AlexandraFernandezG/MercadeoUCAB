package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;
import ucab.dsw.excepciones.PruebaExcepcion;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ucab.dsw.fabrica.Fabrica;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentacionServicio extends AplicacionBase{

    private static Logger logger = LoggerFactory.getLogger(PresentacionServicio.class);

    /**
     * Este método permite obtener todas las presentaciones.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de presentaciones y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allPresentacion")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPresentaciones()  {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las presentaciones");
        JsonObject dataObject;
        DaoPresentacion daoPresentacion = Fabrica.crear(DaoPresentacion.class);


        try {
            List<Presentacion> listaPresentaciones = daoPresentacion.findAll(Presentacion.class);
            logger.debug("Saliendo del método que lista todas las presentaciones");
            return Response.status(Response.Status.OK).entity(listaPresentaciones).build();

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
     * Este método permite obtener una presentacion.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la presentacion consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la presentacion que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarPresentacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarPresentacion(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que te permite consultar una presentación");
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        JsonObject dataObject;

        try {

            Presentacion presentacion = daoPresentacion.find(id, Presentacion.class);
            logger.debug("Saliendo del método que te permite consultar una presentación");
            return Response.status(Response.Status.OK).entity(presentacion).build();

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
     * Este método permite obtener todas las presentaciones activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de presentaciones activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarPresentacionesActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response presentacionesActivas() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las presentaciones activas");
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        List<Presentacion> listaPresentacion = daoPresentacion.findAll(Presentacion.class);
        List<Presentacion> listaPresentacionActivas = new ArrayList<Presentacion>();
        JsonObject dataObject;

        try {

            for (Presentacion presentacion : listaPresentacion) {

                if (presentacion.get_estatus().equals("Activo")) {
                    listaPresentacionActivas.add(presentacion);
                }
            }

            logger.debug("Saliendo del método que lista todas las presentaciones activas");
            return Response.status(Response.Status.OK).entity(listaPresentacionActivas).build();

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
     * Este método permite insertar una presentacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la presentacion insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un presentacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param presentacionDto el objeto presentacion que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addPresentacion")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPresentacion(PresentacionDto presentacionDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al metodo para insertar una presentacion");
        PresentacionDto resultado = new PresentacionDto();
        JsonObject dataObject;

        try {

            DaoPresentacion daoPresentacion = new DaoPresentacion();
            Presentacion presentacion = new Presentacion();

            presentacion.set_nombre(presentacionDto.getNombre());
            presentacion.set_descripcion(presentacionDto.getDescripcion());
            presentacion.set_estatus(presentacionDto.getEstatus());
            Presentacion resul = daoPresentacion.insert(presentacion);
            resultado.setId(resul.get_id());

            logger.debug("Saliendo del método que inserta una presentacion");
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

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite actualizar una presentacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la presentacion modifica y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un presentacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la presentacion a modificar.
     * @param presentacionDto el objeto presentacion que el sistema desea modificar.
     */
    @PUT
    @Path("/updatePresentacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updatePresentacion(@PathParam("id") long id, PresentacionDto presentacionDto) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que te permite actualizar una presentacion");
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        Presentacion presentacion_modificar = daoPresentacion.find(id, Presentacion.class);
        JsonObject dataObject;

        try {

            presentacion_modificar.set_nombre(presentacionDto.getNombre());
            presentacion_modificar.set_descripcion(presentacionDto.getDescripcion());
            presentacion_modificar.set_estatus(presentacionDto.getEstatus());
            daoPresentacion.update(presentacion_modificar);

            logger.debug("Saliendo del método que te permite actualizar una presentacion");
            return Response.status(Response.Status.OK).entity(presentacion_modificar).build();

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
     * Este método permite eliminar una presentacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la presentacion eliminada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se elimina un presentacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la presentacion a eliminar.
     */
    @DELETE
    @Path("/deletePresentacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarPresentacion(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina una presentacion");
        JsonObject dataObject;
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        Presentacion presentacion_eliminar = daoPresentacion.find(id, Presentacion.class);


            try {

                daoPresentacion.delete(presentacion_eliminar);
                logger.debug("Saliendo del método que elimina una presentacion");
                return Response.status(Response.Status.OK).entity(presentacion_eliminar).build();

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
