package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.comando.Reportes.ListarCantidadesPreguntasComando;
import ucab.dsw.comando.Reportes.ListarPorcentajesGeneroComando;
import ucab.dsw.comando.Reportes.ListarRespuestasAbiertasComando;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.fabrica.Fabrica;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/reportes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ReportesServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(ReportesServicio.class);

    /**
     * Este reporte permite obtener solo las respuestas de las preguntas abiertas.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las preguntas.
     */
    @GET
    @Path("/respuestasPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarRespuestasAbiertas(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las respuestas abiertas");
        JsonObject dataObject;

        try {

            ListarRespuestasAbiertasComando comando = Fabrica.crearComandoConId(ListarRespuestasAbiertasComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que lista todas las respuestas abiertas");
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
     * Este método permite obtener las cantidades de respuestas de V/F, escala, simple y múltiple por pregunta
     * para un estudio.
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las estadisticas.
     * @throws NullPointerException si hay algun problema en el analisis
     */
     @GET
     @Path("/cantidadesPregunta/{id}")
     @Produces( MediaType.APPLICATION_JSON )
     public Response listarCantidadesPreguntas(@PathParam("id") long id){

         BasicConfigurator.configure();
         logger.debug("Ingresando al método que lista todas las cantidades de respuestas");
         JsonObject dataObject;

         try {

             ListarCantidadesPreguntasComando comando = Fabrica.crearComandoConId(ListarCantidadesPreguntasComando.class, id);
             comando.execute();

             logger.debug("Saliendo del método que lista todas las cantidades de respuestas");
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
     * Este método permite obtener la cantidad de hombres y mujeres que respondieron un estudio
     * para un estudio.
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las estadisticas.
     * @throws NullPointerException si hay algun problema en el analisis
     */
    @GET
    @Path("/porcentajeGenero/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPorcentajesGenero(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las cantidades de genero");
        JsonObject dataObject;

        try {

            ListarPorcentajesGeneroComando comando = Fabrica.crearComandoConId(ListarPorcentajesGeneroComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que lista todas las cantidades de genero");
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
     * Este método permite agregar una observación al estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el objeto modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param estudioDto el objeto estudio que el sistema desea modificar.
     * @param id el id del estudio a modificar
     */
    @PUT
    @Path("/agregarObservacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response agregarObservacion(@PathParam("id") long id, EstudioDto estudioDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que agrega una observacion");
        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;

        try {

            Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);
            estudio_modificar.set_observaciones(estudioDto.getObservaciones());
            daoEstudio.update(estudio_modificar);

            logger.debug("Saliendo del método que agrega una observacion");
            return Response.status(Response.Status.OK).entity(estudio_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
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
