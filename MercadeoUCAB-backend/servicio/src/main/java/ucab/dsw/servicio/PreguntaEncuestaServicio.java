package ucab.dsw.servicio;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.PreguntasEncuesta.AddPreguntaEncuestaComando;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperPreguntaEncuesta;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/preguntasEncuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PreguntaEncuestaServicio extends AplicacionBase{
    private static Logger logger = LoggerFactory.getLogger(PreguntaEncuestaServicio.class);
    /**
     * Este método permite obtener todas las preguntas.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de PreguntaEncuesta y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allPreguntasEncuesta")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPreguntas() {
        logger.debug("Ingresando al método que consulta todas las preguntas");
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        JsonObject dataObject;

        try {
            List<PreguntaEncuesta> listaPreguntaEncuesta = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

            logger.debug("Saliendo del método que consulta todas las preguntas");
            return Response.status(Response.Status.OK).entity(listaPreguntaEncuesta).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una pregunta
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la pregunta consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la pregunta que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarPreguntaEncuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response encontrarPreguntaEncuesta(@PathParam("id") long id) throws NullPointerException{
        logger.debug("Ingresando al método que consulta una pregunta");
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        JsonObject dataObject;

        try {
            PreguntaEncuesta preguntaEncuesta_consultada = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

            logger.debug("Saliendo del método que consulta una pregunta");
            return Response.status(Response.Status.OK).entity(preguntaEncuesta_consultada).build();
        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener todas las preguntas activas.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de PreguntaEncuesta y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/mostrarPreguntasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response preguntasActivas() {
        logger.debug("Ingresando al método que muestra todas las preguntas activas");
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        List<PreguntaEncuesta> listaPreguntas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
        List<PreguntaEncuesta> listaPreguntasActivas = new ArrayList<PreguntaEncuesta>();
        JsonObject dataObject;

        try {

            for (PreguntaEncuesta preguntaEncuesta : listaPreguntas) {

                if (preguntaEncuesta.get_estatus().equals("Activo")) {
                    listaPreguntasActivas.add(preguntaEncuesta);
                }
            }

            logger.debug("Saliendo del método que muestra todas las preguntas activas");
            return Response.status(Response.Status.OK).entity(listaPreguntasActivas).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite insertar una pregunta.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * la pregunta insertada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @throws PersistenceException si se inserta una pregunta duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param preguntaEncuestaDto el objeto pregunta con los datos correspondientes para ejecutar el insert.
     */
    @POST
    @Path("/addPreguntaEncuesta")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPreguntaEncuesta(PreguntaEncuestaDto preguntaEncuestaDto){

        logger.debug("Ingresando al método que permite añadir una pregunta");
        JsonObject dataObject;

        try {

            PreguntaEncuesta preguntaEncuesta = MapperPreguntaEncuesta.mapDtoToEntityInsert(preguntaEncuestaDto);
            AddPreguntaEncuestaComando comando = Fabrica.crearComandoConEntity(AddPreguntaEncuestaComando.class, preguntaEncuesta);
            comando.execute();

            logger.debug("Saliendo del método que permite añadir una pregunta");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite insertar una pregunta en un estudio.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * la pregunta insertada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @throws PersistenceException si se inserta una pregunta duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param preguntaEncuestaDto el objeto pregunta con los datos correspondientes para ejecutar el insert.
     */
    @POST
    @Path("/addPreguntaEncuestaEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addPreguntaEncuestaEstudio(@PathParam("id") long id, PreguntaEncuestaDto preguntaEncuestaDto){

        logger.debug("Ingresando al método que permite añadir una pregunta a un estudio");
        PreguntaEncuestaDto resultado = new PreguntaEncuestaDto();
        JsonObject dataObject;

        try {

            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
            PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

            preguntaEncuesta.set_descripcion(preguntaEncuestaDto.getDescripcion());
            preguntaEncuesta.set_tipoPregunta(preguntaEncuestaDto.getTipoPregunta());
            preguntaEncuesta.set_estatus(preguntaEncuestaDto.getEstatus());
            Usuario usuario = daoUsuario.find(preguntaEncuestaDto.getUsuarioDto().getId(), Usuario.class);
            preguntaEncuesta.set_usuario(usuario);
            Subcategoria subcategoria = daoSubcategoria.find(preguntaEncuestaDto.getSubcategoriaDto().getId(), Subcategoria.class);
            preguntaEncuesta.set_subcategoria(subcategoria);
            PreguntaEncuesta resul = daoPreguntaEncuesta.insert(preguntaEncuesta);
            resultado.setId(resul.get_id());

            //Insertar la pregunta en el estudio
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            PreguntaEstudio preguntaEstudio = new PreguntaEstudio();

            preguntaEstudio.set_estatus("Activo");
            PreguntaEncuesta idPregunta = new PreguntaEncuesta(resul.get_id());
            preguntaEstudio.set_preguntaEncuesta(idPregunta);
            Estudio idEstudio = new Estudio(id);
            preguntaEstudio.set_estudio(idEstudio);
            daoPreguntaEstudio.insert(preguntaEstudio);

            logger.debug("Saliendo del método que permite añadir una pregunta a un estudio");
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
                    .add("excepcion", ex.getMessage())
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
     * Este método permite modificar el estatus de una pregunta
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la  pregunta modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param preguntaEncuestaDto el objeto pregunta que el sistema desea modificar.
     * @param id el id de la pregunta a modificar
     */
    @PUT
    @Path("/estatusPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusPregunta(@PathParam("id") long id, PreguntaEncuestaDto preguntaEncuestaDto){

        logger.debug("Ingresando al método que permite ver el estatus de  una pregunta");
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_modificar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);
        JsonObject dataObject;

            try {

                preguntaEncuesta_modificar.set_estatus(preguntaEncuestaDto.getEstatus());
                daoPreguntaEncuesta.update(preguntaEncuesta_modificar);
                DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();

                if (preguntaEncuesta_modificar.get_estatus() == "Inactivo") {

                    List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);

                    for (RespuestaPregunta respuestaPregunta : listaRespuesta) {

                        if (respuestaPregunta.get_preguntaEncuesta().get_id() == id) {
                            respuestaPregunta.set_estatus("Inactivo");
                            daoRespuestaPregunta.update(respuestaPregunta);
                        }
                    }
                } else if (preguntaEncuesta_modificar.get_estatus() == "Activo") {

                    List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);

                    for (RespuestaPregunta respuestaPregunta : listaRespuesta) {

                        if (respuestaPregunta.get_preguntaEncuesta().get_id() == id) {
                            respuestaPregunta.set_estatus("Activo");
                            daoRespuestaPregunta.update(respuestaPregunta);
                        }
                    }
                }

                logger.debug("Saliendo del método que permite ver el estatus de  una pregunta");
                return Response.status(Response.Status.OK).entity(preguntaEncuesta_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }


    }

    /**
     * Este método permite modificar una pregunta
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la  pregunta modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param preguntaEncuestaDto el objeto pregunta que el sistema desea modificar.
     * @param id el id de la pregunta a modificar
     */
    @PUT
    @Path("/updatePreguntaEncuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarPreguntaEncuesta(@PathParam("id") long id, PreguntaEncuestaDto preguntaEncuestaDto){

        logger.debug("Ingresando al método que permite actualizar  una pregunta");
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_modificar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);
        JsonObject dataObject;

            try {
                preguntaEncuesta_modificar.set_descripcion(preguntaEncuestaDto.getDescripcion());
                preguntaEncuesta_modificar.set_tipoPregunta(preguntaEncuestaDto.getTipoPregunta());
                preguntaEncuesta_modificar.set_estatus(preguntaEncuestaDto.getEstatus());
                daoPreguntaEncuesta.update(preguntaEncuesta_modificar);

                logger.debug("Saliendo del método que permite actualizar  una pregunta");
                return Response.status(Response.Status.OK).entity(preguntaEncuesta_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar una pregunta
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la pregunta a eliminar
     */
    @DELETE
    @Path("/deletePreguntaEncuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarPreguntaEncuesta(@PathParam("id") long id){

        logger.debug("Ingresando al método que permite eliminar  una pregunta");
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_eliminar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);
        JsonObject dataObject;

            try {
                daoPreguntaEncuesta.delete(preguntaEncuesta_eliminar);

                logger.debug("Saliendo del método que permite eliminar  una pregunta");
                return Response.status(Response.Status.OK).entity(preguntaEncuesta_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }
}
