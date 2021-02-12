package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.EstudioEncuestadoResponse;
import ucab.dsw.Response.RespuestaPreguntaResponse;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EncuestaServicio {

    /**
     * Este método permite obtener las preguntas de una encuesta
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @param id
     */
    @GET
    @Path("/preguntas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerPreguntaEncuesta(@PathParam("id") long id) {

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        JsonObject dataObject;

        try {

            List<Object[]> preguntasRespuestas = daoPreguntaEncuesta.listarPreguntas(id);

            List<EncuestaResponse> ResponseListUpdate = new ArrayList<>(preguntasRespuestas.size());

            for (Object[] r : preguntasRespuestas) {

                ResponseListUpdate.add(new EncuestaResponse((long)r[0], (String)r[1], (String)r[2], (long)r[3]));
            }

            return Response.status(Response.Status.OK).entity(ResponseListUpdate).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite obtener las respuestas de las preguntas de una encuesta
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @param id
     */
    @GET
    @Path("/respuestas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response obtenerRespuestaEncuesta(@PathParam("id") long id) {

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        JsonObject dataObject;

        try {

            List<Object[]> respuestas = daoRespuestaPregunta.listaRespuestaEncuesta(id);

            List<RespuestaPreguntaResponse> ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {

                ResponseListUpdate.add(new RespuestaPreguntaResponse((Long)r[0], (String)r[1]));
            }

            return Response.status(Response.Status.OK).entity(ResponseListUpdate).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener los encuestados
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @param id
     */
    @GET
    @Path("/listar/encuestados/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response getAllByRespuesta(@PathParam("id") long id) {

        DaoUsuario daoUsuario = new DaoUsuario();
        JsonObject dataObject;

        try {

            List<Object[]> estudioUsuarioResponseList = daoUsuario.listarEncuestados(id);

            List<EstudioEncuestadoResponse> estudioUsuarioResponseListUpdate = new ArrayList<>(estudioUsuarioResponseList.size());

            for (Object[] r : estudioUsuarioResponseList) {
                estudioUsuarioResponseListUpdate.add(new EstudioEncuestadoResponse((Long)r[1], (String)r[2], (String)r[3]));
            }

            return Response.status(Response.Status.OK).entity(estudioUsuarioResponseListUpdate).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite registrar una lista de respuestas en una encuesta
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @param respuestas la lista de respuestas seleccionada en la encuesta
     */
    @POST
    @Path( "/responder" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRespuesta(List<RespuestaDto> respuestas) {

        JsonObject dataObject;
        RespuestaDto resultado = new RespuestaDto();

        try
        {
            DaoRespuesta dao = new DaoRespuesta();
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

            for (RespuestaDto respuestaDto: respuestas) {
                Respuesta respuesta = new Respuesta();
                respuesta.set_estatus(respuestaDto.getEstatus());

                respuesta.set_escala(respuestaDto.getEscala());
                respuesta.set_respuestaAbierta(respuestaDto.getRespuestaAbierta());
                respuesta.set_respuestaMultiple(respuestaDto.getRespuestaMultiple());
                respuesta.set_respuestaSimple(respuestaDto.getRespuestaSimple());
                respuesta.set_verdaderoFalso(respuestaDto.getVerdaderoFalso());

                PreguntaEstudio preguntaEstudio = daoPreguntaEstudio.find(respuestaDto.getPreguntaEstudioDto().getId(), PreguntaEstudio.class);
                Usuario usuario = daoUsuario.find(respuestaDto.getUsuarioDto().getId(), Usuario.class);
                PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(preguntaEstudio.get_preguntaEncuesta().get_id(), PreguntaEncuesta.class);
                respuesta.set_usuario(usuario);
                preguntaEstudio.set_preguntaEncuesta(preguntaEncuesta);
                respuesta.set_preguntaEstudio(preguntaEstudio);

                Respuesta resul = dao.insert(respuesta);
                resultado.setId(resul.get_id());

            }

            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite validar el estatus de la encuesta
     * @author Emanuel Di Cristofaro
     */
     @GET
     @Path("/validarEstatusEncuesta/{idE}/{idU}")
     public Response validarEstatusEncuestas(@PathParam("idE") long idE, @PathParam("idU") long idU) {

         JsonObject dataObject;
         Object validar;

         try {
                DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();

                DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
                //Obtener las preguntas del estudio
                List<PreguntaEstudio> listaPreguntas = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
                List<PreguntaEstudio> listaPreguntasEstudio = new ArrayList<>();

                for (PreguntaEstudio pregunta : listaPreguntas) {

                    if (pregunta.get_estudio().get_id() == idE)
                        listaPreguntasEstudio.add(pregunta);
                 }

                int cantidadPreguntas = listaPreguntasEstudio.size();
                List<Long> cantidadRespuestas = daoUsuarioEstudio.cantidadRespuestas(idU,idE);
                int cantidadRespuestaTotal = cantidadRespuestas.size();

                if(cantidadRespuestaTotal == 0) {
                    validar = "En espera";

                } else if (cantidadPreguntas == cantidadRespuestaTotal) {

                    validar = "Finalizado";

                } else {

                    validar = "En proceso";

                }

             return Response.status(Response.Status.OK).entity(validar).build();

         } catch (Exception ex) {

             dataObject = Json.createObjectBuilder()
                     .add("estado", "Error")
                     .add("excepcion", ex.getMessage())
                     .add("codigo", 400).build();

             return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

         }
     }
}

