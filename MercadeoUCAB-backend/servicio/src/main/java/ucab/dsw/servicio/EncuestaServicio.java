package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.*;
import ucab.dsw.response.EncuestaResponse;
import ucab.dsw.response.EstudioEncuestadoResponse;
import ucab.dsw.response.RespuestaPreguntaResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Log
@Path( "/encuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EncuestaServicio {

    @GET
    @Path("/preguntas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EncuestaResponse> obtenerPreguntaEncuesta(@PathParam("id") long id) throws Exception {

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();


            String sqlQuery = "SELECT PE._id AS idPreguntaEncuesta, PE._descripcion AS descripcion , PE._tipoPregunta AS tipoPregunta," +
                    " PES._id AS idPreguntaEstudio FROM PreguntaEncuesta AS PE, PreguntaEstudio AS PES WHERE " +
                    "PE._id = PES._preguntaEncuesta._id AND PES._estudio._id =: id " +
                    "ORDER BY PE._id ";
            Query query = entitymanager.createQuery( sqlQuery);
            query.setParameter("id", id);
            List<Object[]> preguntas_respuestas = query.getResultList();

            List<EncuestaResponse> ResponseListUpdate = new ArrayList<>(preguntas_respuestas.size());

            for (Object[] r : preguntas_respuestas) {
                ResponseListUpdate.add(new EncuestaResponse((long)r[0], (String)r[1], (String)r[2], (long)r[3]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @GET
    @Path("/respuestas/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<RespuestaPreguntaResponse> obtenerRespuestaEncuesta(@PathParam("id") long id) throws Exception {

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();


            String sqlQuery = "SELECT RP._preguntaEncuesta._id AS id, RP._nombre AS pregunta" +
                    " FROM PreguntaEncuesta AS PE, PreguntaEstudio AS PES, RespuestaPregunta AS RP WHERE " +
                    "PE._id = PES._preguntaEncuesta._id AND PE._id = RP._preguntaEncuesta._id AND " +
                    "PES._estudio._id =: id " +
                    "ORDER BY PE._id";
            Query query = entitymanager.createQuery( sqlQuery );
            query.setParameter("id", id);
            List<Object[]> respuestas = query.getResultList();

            List<RespuestaPreguntaResponse> ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {
                ResponseListUpdate.add(new RespuestaPreguntaResponse((Long)r[0], (String)r[1]));
            }

            return ResponseListUpdate;
        }catch (Exception e){

            throw  new Exception(e);

        }

    }

    @GET
    @Path("/listar/encuestados/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<EstudioEncuestadoResponse> getAllByRespuesta(@PathParam("id") long id) throws Exception {

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            String sqlQuery = "SELECT DISTINCT R._id AS idRespuesta, U._id AS idUsuario, U._correoelectronico AS correo," +
                    " U._nombre AS nombreUsuario FROM PreguntaEstudio AS PE, Respuesta AS R, Usuario U WHERE" +
                    " PE._estudio._id = R._preguntaEstudio._id AND R._usuario._id = U._id AND " +
                    "U._rol._id = 4 AND PE._estudio._id =: id ";
            Query query = entitymanager.createQuery( sqlQuery);
            query.setParameter("id", id);

            List<Object[]> estudioUsuarioResponseList = query.getResultList();
            List<EstudioEncuestadoResponse> estudioUsuarioResponseListUpdate = new ArrayList<>(estudioUsuarioResponseList.size());

            for (Object[] r : estudioUsuarioResponseList) {
                estudioUsuarioResponseListUpdate.add(new EstudioEncuestadoResponse((Long)r[1], (String)r[2], (String)r[3]));
            }

            return estudioUsuarioResponseListUpdate;

        }
        catch (Exception e){

            throw  new Exception(e);
        }

    }

    @POST
    @Path( "/responder" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public RespuestaDto addRespuesta(List<RespuestaDto> respuestas) {
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
        }
        catch ( Exception ex )
        {
            String problema = ex.getMessage();
        }
        return  resultado;
    }


}

