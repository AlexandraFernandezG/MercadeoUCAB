package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.Response.PreguntasResponse;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/preguntasEstudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PreguntasEstudioServicio extends AplicacionBase {
    private static Logger logger = LoggerFactory.getLogger(PreguntasEstudioServicio.class);

    /**
     * Este método permite obtener todas las preguntas de un estudio.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de preguntas de un estudio y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del estudio que se quiere obtener las preguntas.
     */
    @GET
    @Path("/listarPreguntasEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPreguntasEstudio(@PathParam("id") long id) {
        logger.debug("Ingresando al método que consulta todas las preguntas de un estudio");
        JsonObject dataObject;

        try {

            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
            List<Object[]> listaPreguntas = daoPreguntaEncuesta.mostrarPreguntasEstudio(id);
            List<PreguntasResponse> listaPreguntasEstudio = new ArrayList<>(listaPreguntas.size());

            for (Object[] pre: listaPreguntas){

                listaPreguntasEstudio.add(new PreguntasResponse((long)pre[0], (String)pre[1], (String)pre[2], (String)pre[3], (String)pre[4]));

            }

            logger.debug("Saliendo del método que consulta todas las preguntas de un estudio");
            return Response.status(Response.Status.OK).entity(listaPreguntasEstudio).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha realizado la operacion con éxito: " + ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite insertar cada pregunta con un estudio.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * la relacion insertada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @throws PersistenceException si se inserta una relacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param preguntaEstudioDto el objeto con los datos correspondientes para ejecutar el insert.
     */
    @POST
    @Path("/addPreguntaEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPreguntaEstudio(PreguntaEstudioDto preguntaEstudioDto){

        logger.debug("Ingresando al método que permite agregar una pregunta de un estudio");
        PreguntaEstudioDto resultado = new PreguntaEstudioDto();
        JsonObject dataObject;

            try {

                DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
                PreguntaEstudio preguntaEstudio = new PreguntaEstudio();
                DaoEstudio daoEstudio = new DaoEstudio();
                DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

                preguntaEstudio.set_estatus(preguntaEstudioDto.getEstatus());
                Estudio estudio = daoEstudio.find(preguntaEstudioDto.getEstudioDto().getId(), Estudio.class);
                preguntaEstudio.set_estudio(estudio);
                PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(preguntaEstudioDto.getPreguntaEncuestaDto().getId(), PreguntaEncuesta.class);
                preguntaEstudio.set_preguntaEncuesta(preguntaEncuesta);
                PreguntaEstudio resul = daoPreguntaEstudio.insert(preguntaEstudio);
                resultado.setId(resul.get_id());

                logger.debug("Saliendo del método que permite agregar una pregunta de un estudio");
                return Response.status(Response.Status.OK).entity(resultado).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha realizado la operacion con éxito: " + ex.getMessage())
                        .add("codigo", 401).build();

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (Exception ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 400).build();

                logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
            }

    }

    /**
     * Este método permite modificar el estatus de la relacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * la relacion modificada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @throws PersistenceException si se modifica una relacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la relacion a modificar.
     * @param preguntaEstudioDto el objeto con los datos correspondientes para ejecutar el modificar.
     */
    @PUT
    @Path("/updatePreguntaEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusPreguntaEstudio(@PathParam("id") long id, PreguntaEstudioDto preguntaEstudioDto){

        logger.debug("Ingresando al método que permite actualizar una pregunta de un estudio");
        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        PreguntaEstudio preguntaEstudio_modificar = daoPreguntaEstudio.find(id, PreguntaEstudio.class);
        JsonObject dataObject;

        try {

            preguntaEstudio_modificar.set_estatus(preguntaEstudioDto.getEstatus());
            daoPreguntaEstudio.delete(preguntaEstudio_modificar);

            logger.debug("Saliendo del método que permite actualizar una pregunta de un estudio");
            return Response.status(Response.Status.OK).entity(preguntaEstudio_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha realizado la operacion con éxito: " + ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }


    }

    /**
     * Este método permite eliminar la relacion.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * la relacion eliminada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @throws PersistenceException si se elimina una relacion duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la relacion a eliminar.
     */
    @DELETE
    @Path("/eliminarPreguntaEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarEstudioPreguntaEstudio(@PathParam("id") long id){

        logger.debug("Ingresando al método que permite eliminar una pregunta de un estudio");
        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        PreguntaEstudio preguntaEstudio_eliminar = daoPreguntaEstudio.find(id, PreguntaEstudio.class);
        JsonObject dataObject;

            try {
                daoPreguntaEstudio.delete(preguntaEstudio_eliminar);

                logger.debug("Saliendo del método que permite eliminar una pregunta de un estudio");
                return Response.status(Response.Status.OK).entity(preguntaEstudio_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha realizado la operacion con éxito: " + ex.getMessage())
                        .add("codigo", 401).build();

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (Exception ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 400).build();

                logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
            }

    }
}
