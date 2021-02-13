package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/respuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaServicio extends AplicacionBase{

    private static Logger logger = LoggerFactory.getLogger(RespuestaServicio.class);

    /**
     * Este método permite obtener todas las respuestas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de respuestas y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allRespuesta")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarRespuestas() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las respuestas");
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        JsonObject dataObject;

        try {

            List<Respuesta> listaRespuestas = daoRespuesta.findAll(Respuesta.class);
            logger.debug("Saliendo del método que lista todas las respuestas");
            return Response.status(Response.Status.OK).entity(listaRespuestas).build();

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
     * Este método permite obtener una respuesta.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la respuesta consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la respuesta que se quiere consultar.
     */
    @GET
    @Path("/consultarRespuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarRespuesta(@PathParam("id") long id)  {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que consulta una respuesta");
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        JsonObject dataObject;

        try {

            Respuesta respuesta = daoRespuesta.find(id, Respuesta.class);
            logger.debug("Saliendo del método que consulta una respuesta");
            return Response.status(Response.Status.OK).entity(respuesta).build();

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
     * Este método permite obtener todas las categorias activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de categorias activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarRespuestasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response respuestasActivas() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las respuestas activas");
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        List<Respuesta> listaRespuesta = daoRespuesta.findAll(Respuesta.class);
        List<Respuesta> listaRespuestaActivas = new ArrayList<Respuesta>();
        JsonObject dataObject;

        try {

            for (Respuesta respuesta : listaRespuesta) {

                if (respuesta.get_estatus().equals("Activo")) {
                    listaRespuestaActivas.add(respuesta);
                }
            }

            logger.debug("Saliendo del método que lista todas las respuestas activas");
            return Response.status(Response.Status.OK).entity(listaRespuestaActivas).build();

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
     * Este método permite insertar una respuesta
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la respuesta insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una respuesta duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param respuestaDto el objeto respuesta que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addRespuesta")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRespuesta(RespuestaDto respuestaDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que añade una respuesta");
        RespuestaDto resultado = new RespuestaDto();
        JsonObject dataObject;

        try {

            DaoRespuesta daoRespuesta = new DaoRespuesta();
            Respuesta respuesta = new Respuesta();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();

            respuesta.set_respuestaAbierta(respuestaDto.getRespuestaAbierta());
            respuesta.set_escala(respuestaDto.getEscala());
            respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());
            respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
            respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
            respuesta.set_estatus(respuestaDto.getEstatus());
            PreguntaEstudio preguntaEstudio = daoPreguntaEstudio.find(respuestaDto.getPreguntaEstudioDto().getId(), PreguntaEstudio.class);
            Usuario usuario = daoUsuario.find(respuestaDto.getUsuarioDto().getId(), Usuario.class);
            respuesta.set_preguntaEstudio(preguntaEstudio);
            respuesta.set_usuario(usuario);
            Respuesta resul = daoRespuesta.insert(respuesta);
            resultado.setId(resul.get_id());

            logger.debug("Saliendo del método que añade una respuesta");
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
     * Este método permite modificar una respuesta
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la respuesta modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param respuestaDto el objeto respuesta que el sistema desea modificar.
     * @param id el id de la respuesta a modificar
     */
    @PUT
    @Path("/updateRespuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateRespuesta(@PathParam("id") long id, RespuestaDto respuestaDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método para actualizar una respuesta");
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        Respuesta respuesta_modificar = daoRespuesta.find(id, Respuesta.class);
        JsonObject dataObject;

            try {

                respuesta_modificar.set_respuestaAbierta(respuestaDto.getRespuestaAbierta());
                respuesta_modificar.set_escala(respuestaDto.getEscala());
                respuesta_modificar.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());
                respuesta_modificar.set_respuestaSimple(respuestaDto.getRespuestaSimple());
                respuesta_modificar.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
                respuesta_modificar.set_estatus(respuestaDto.getEstatus());
                daoRespuesta.update(respuesta_modificar);

                logger.debug("Saliendo del método para actualizar una respuesta");
                return Response.status(Response.Status.OK).entity(respuesta_modificar).build();

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
     * Este método permite eliminar una respuesta
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la respuesta a eliminar
     */
    @DELETE
    @Path("/deleteRespuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarRespuesta(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina una respuesta");
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        Respuesta respuesta_eliminar = daoRespuesta.find(id, Respuesta.class);
        JsonObject dataObject;

            try {

                daoRespuesta.delete(respuesta_eliminar);
                logger.debug("Saliendo del método que elimina una respuesta");
                return Response.status(Response.Status.OK).entity(respuesta_eliminar).build();

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
