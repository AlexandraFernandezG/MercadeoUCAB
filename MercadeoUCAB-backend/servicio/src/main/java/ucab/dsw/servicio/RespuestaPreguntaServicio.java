package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoRespuestaPregunta;
import ucab.dsw.dtos.RespuestaPreguntaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.RespuestaPregunta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/respuestaPregunta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaPreguntaServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas las RespuestasPregunta.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de RespuestasPregunta y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allRespuestaPregunta")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarRespuestas() {

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        JsonObject dataObject;

        try {
            List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);

            return Response.status(Response.Status.OK).entity(listaRespuesta).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una RespuestaPregunta.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la respuestaPregunta consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la respuestaPregunta que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response encontrarRespuestaPregunta(@PathParam("id") long id) {

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        JsonObject dataObject;

        try {

            RespuestaPregunta respuestaPregunta = daoRespuestaPregunta.find(id, RespuestaPregunta.class);

            return Response.status(Response.Status.OK).entity(respuestaPregunta).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la respuestaPregunta: " + ex.getMessage())
                    .add("codigo", 400).build();

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
     * Este método permite obtener todas las respuestasPreguntas activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de respuestasPreguntas activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarRespuestasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response respuestasActivas() throws NullPointerException{

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);
        List<RespuestaPregunta> listaRespuestaActivas = new ArrayList<RespuestaPregunta>();
        JsonObject dataObject;

        try {
            for (RespuestaPregunta respuestaPregunta : listaRespuesta) {

                if (respuestaPregunta.get_estatus().equals("Activo")) {
                    listaRespuestaActivas.add(respuestaPregunta);
                }
            }

            return Response.status(Response.Status.OK).entity(listaRespuestaActivas).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite insertar respuestasPregunta
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la respuestasPregunta insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una respuestasPregunta duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param respuestas el objeto list de respuestas que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRespuestaPregunta(@PathParam("id") long id, List<RespuestaPreguntaDto> respuestas) {

        RespuestaPreguntaDto resultado = new RespuestaPreguntaDto();
        JsonObject dataObject;

        try {

            DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

            for (RespuestaPreguntaDto respuestaPreguntaDto : respuestas) {

                RespuestaPregunta respuestaPregunta = new RespuestaPregunta();
                respuestaPregunta.set_nombre(respuestaPreguntaDto.getNombre());
                respuestaPregunta.set_estatus(respuestaPreguntaDto.getEstatus());
                PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);
                respuestaPregunta.set_preguntaEncuesta(preguntaEncuesta);
                RespuestaPregunta resul = daoRespuestaPregunta.insert(respuestaPregunta);
                resultado.setId(resul.get_id());
            }

            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la respuestaPregunta: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite modificar la respuestaPregunta
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la respuestaPregunta modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param respuestaPreguntaDto el objeto respuestaPregunta que el sistema desea modificar.
     * @param id el id de la respuestaPregunta a modificar.
     */
    @PUT
    @Path("/updateRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarRespuestaPregunta(@PathParam("id") long id, RespuestaPreguntaDto respuestaPreguntaDto){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta_modificar = daoRespuestaPregunta.find(id, RespuestaPregunta.class);
        JsonObject dataObject;

            try {

                respuestaPregunta_modificar.set_nombre(respuestaPreguntaDto.getNombre());
                respuestaPregunta_modificar.set_estatus(respuestaPreguntaDto.getEstatus());
                daoRespuestaPregunta.update(respuestaPregunta_modificar);

                return Response.status(Response.Status.OK).entity(respuestaPregunta_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la respuestaPregunta: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar una respuestaPregunta
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la respuestaPregunta a eliminar
     */
    @DELETE
    @Path("/deleteRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarRespuestaPregunta(@PathParam("id") long id){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta_eliminar = daoRespuestaPregunta.find(id, RespuestaPregunta.class);
        JsonObject dataObject;

            try {

                daoRespuestaPregunta.delete(respuestaPregunta_eliminar);

                return Response.status(Response.Status.OK).entity(respuestaPregunta_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la respuestaPregunta: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

}
