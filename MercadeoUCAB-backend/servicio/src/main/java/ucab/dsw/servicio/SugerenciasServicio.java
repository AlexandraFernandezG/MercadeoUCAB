package ucab.dsw.servicio;

import ucab.dsw.comando.Sugerencias.*;
import ucab.dsw.fabrica.Fabrica;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/sugerencias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SugerenciasServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(SugerenciasServicio.class);
    /**
     * Este método permite obtener las preguntas recomendadas en base a un estudio seleccionado
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para recomendar las preguntas.
     */
    @GET
    @Path("/preguntasEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPreguntasEstudioRecomendadas(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista preguntas recomendadas");
        JsonObject dataObject;

        try {

            ListarPreguntasEstudioRecomendadasComando comando = Fabrica.crearComandoConId(ListarPreguntasEstudioRecomendadasComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que lista preguntas recomendadas");
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
     * Este método permite obtener una lista de estudios recomendados acorde a una solicitud de estudio
     * @author Emanuel Di Cristofaro
     * @param id id de la solicitud de estudio para recomendar estudios.
     * @throws NullPointerException si no se encuentra la solicitud de estudio
     */
    @GET
    @Path("/solicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosRecomendados(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista estudios recomendados a partir de una solicitud");
        JsonObject dataObject;

        try {

            ListarEstudiosRecomendadosComando comando = Fabrica.crearComandoConId(ListarEstudiosRecomendadosComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que lista estudios recomendados a partir de una solicitud");
                return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
     * Este método permite obtener los estudios recomendados partiendo de un encuestado
     * @author Emanuel Di Cristofaro
     * @param id id del usuario Encuestado que permite obtener los estudios recomendados
     */
    @GET
    @Path("/estudiosEncuestado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosEncuestado(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos lo estudios recomendados para un encuestado");
        JsonObject dataObject;

        try {

            ListarEstudiosEncuestadoComando comando = Fabrica.crearComandoConId(ListarEstudiosEncuestadoComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que lista todos lo estudios recomendados para un encuestado");
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
     * Este método permite insertar preguntas de un estudio a otro.
     * @author Emanuel Di Cristofaro
     * @param idER: Es el id del estudio recomendado
     * @param idE: Es el id del estudio creado
     * @throws NullPointerException si no se encuentra el estudio recomendado
     */
    @POST
    @Path("/insertarEstudioRecomendado/{idER}/{idE}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response insertarEstudioRecomendado(@PathParam("idER") long idER, @PathParam("idE") long idE) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que inserta preguntas de un estudio recomendado a otro");
        JsonObject dataObject;

        try {

            InsertarEstudioRecomendadoComando comando = Fabrica.crearComandoDosId(InsertarEstudioRecomendadoComando.class,idER, idE);
            comando.execute();
            logger.debug("Saliendo del método que inserta preguntas de un estudio recomendado a otro");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
     * Este método permite obtener los estudios partiendo de un cliente
     * @author Emanuel Di Cristofaro
     * @param id id del usuario Cliente que permite obtener los estudios
     */
    @GET
    @Path("/estudiosCliente/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosCliente(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los estudios de un cliente");
        JsonObject dataObject;

        try {

            ListarEstudiosClienteComando comando = Fabrica.crearComandoConId(ListarEstudiosClienteComando.class, id);
            comando.execute();
            logger.debug("Ingresando al método que lista todos los estudios de un cliente");
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
     * Este método permite obtener los estudios partiendo de un analista
     * @author Emanuel Di Cristofaro
     * @param id id del usuario Analista que permite obtener los estudios
     */
    @GET
    @Path("/estudiosAnalista/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosAnalista(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los estudios de un analista");
        JsonObject dataObject;

        try {

            ListarEstudiosAnalistaComando comando = Fabrica.crearComandoConId(ListarEstudiosAnalistaComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que lista todos los estudios de un analista");
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

}

