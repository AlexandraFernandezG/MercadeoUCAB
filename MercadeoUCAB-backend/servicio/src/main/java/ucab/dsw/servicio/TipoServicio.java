package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.excepciones.PruebaExcepcion;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path( "/tipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TipoServicio extends AplicacionBase{

    private static Logger logger = LoggerFactory.getLogger(TipoServicio.class);
    /**
     * Este método permite obtener todas los tipos.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de tipos y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allTipo")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarTipos() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los tipos");
        DaoTipo daoTipo = new DaoTipo();
        JsonObject dataObject;

        try {
            List<Tipo> listaTipos = daoTipo.findAll(Tipo.class);
            logger.debug("Saliendo del método que lista todos los tipos");
            return Response.status(Response.Status.OK).entity(listaTipos).build();

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
     * Este método permite obtener un tipo.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el tipo consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del tipo que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarTipo(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que consulta un tipo");
        DaoTipo daoTipo = new DaoTipo();
        JsonObject dataObject;

        try {

            Tipo tipo = daoTipo.find(id, Tipo.class);
            logger.debug("Saliendo del método que consulta un tipo");
            return Response.status(Response.Status.OK).entity(tipo).build();

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
     * Este método permite obtener todas los tipos activos.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de tipos activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarTiposActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response tiposActivos() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los tipos activos");
        DaoTipo daoTipo = new DaoTipo();
        List<Tipo> listaTipo = daoTipo.findAll(Tipo.class);
        List<Tipo> listaTipoActivo = new ArrayList<Tipo>();
        JsonObject dataObject;

        try {

            for (Tipo tipo : listaTipo) {

                if (tipo.get_estatus().equals("Activo")) {
                    listaTipoActivo.add(tipo);
                }
            }
            logger.debug("Saliendo del método que lista todos los tipos activos");
            return Response.status(Response.Status.OK).entity(listaTipoActivo).build();

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
     * Este método permite insertar un tipo
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el tipo insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un tipo duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param tipoDto el objeto tipo que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addTipo")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addTipo(TipoDto tipoDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que añade un tipo");
        TipoDto resultado = new TipoDto();
        JsonObject dataObject;

        try {

            DaoTipo daoTipo = new DaoTipo();
            Tipo tipo = new Tipo();

            tipo.set_nombre(tipoDto.getNombre());
            tipo.set_descripcion(tipoDto.getDescripcion());
            tipo.set_estatus(tipoDto.getEstatus());
            Tipo resul = daoTipo.insert(tipo);
            resultado.setId(resul.get_id());

            logger.debug("Saliendo del método que añade un tipo");
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
     * Este método permite modificar un tipo
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el tipo modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un tipo duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param tipoDto el objeto tipo que el sistema desea modificar.
     * @param id el id del tipo a modificar
     */
    @PUT
    @Path("/updateTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateTipo(@PathParam("id") long id, TipoDto tipoDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualiza un tipo");
        DaoTipo daoTipo = new DaoTipo();
        Tipo tipo_modificar = daoTipo.find(id, Tipo.class);
        JsonObject dataObject;

            try {

                tipo_modificar.set_nombre(tipoDto.getNombre());
                tipo_modificar.set_descripcion(tipoDto.getDescripcion());
                tipo_modificar.set_estatus(tipoDto.getEstatus());
                daoTipo.update(tipo_modificar);

                logger.debug("Saliendo del método que actualiza un tipo");
                return Response.status(Response.Status.OK).entity(tipo_modificar).build();

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
     * Este método permite eliminar un tipo
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se elimina un tipo duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la tipo a eliminar
     */
    @DELETE
    @Path("/deleteTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarTipo(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina un tipo");
        DaoTipo daoTipo = new DaoTipo();
        Tipo tipo_eliminar = daoTipo.find(id, Tipo.class);
        JsonObject dataObject;

            try {

                daoTipo.delete(tipo_eliminar);
                logger.debug("Saliendo del método que elimina un tipo");
                return Response.status(Response.Status.OK).entity(tipo_eliminar).build();

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
