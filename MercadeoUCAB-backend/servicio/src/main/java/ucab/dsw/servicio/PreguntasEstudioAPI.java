package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.entidades.Respuesta;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/preguntasEstudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PreguntasEstudioAPI extends AplicacionBase {

    // Este metodo lista todas las preguntas de un estudio
    @GET
    @Path("/listarPreguntasEstudio/{id}")
    public List<PreguntaEncuesta> listarPreguntasEstudio(@PathParam("id") long id){

        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        List<PreguntaEstudio> listaEstudioPregunta = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
        List<PreguntaEncuesta> listaPreguntasEstudio = new ArrayList<PreguntaEncuesta>();

        for(PreguntaEstudio preguntaEstudio: listaEstudioPregunta){

            if(preguntaEstudio.get_estudio().get_id() == id){

                long idFk = preguntaEstudio.get_preguntaEncuesta().get_id();
                DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
                PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idFk, PreguntaEncuesta.class);
                listaPreguntasEstudio.add(preguntaEncuesta);
            }
        }
        return listaPreguntasEstudio;
    }

    // Este metodo agrega preguntas cuando le pasas un solo estudio
    @POST
    @Path("/addPreguntaEstudio/{id}")
    public PreguntaEstudio addPreguntaEstudio(@PathParam("id") long id, PreguntaEstudioDto preguntaEstudioDto){

            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            PreguntaEstudio preguntaEstudio = new PreguntaEstudio();

            preguntaEstudio.set_estatus("Activo/Inactivo");
            Estudio estudio = new Estudio(id);
            preguntaEstudio.set_estudio(estudio);
            PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta(preguntaEstudioDto.getPreguntaEncuestaDto().getId());
            preguntaEstudio.set_preguntaEncuesta(preguntaEncuesta);
            daoPreguntaEstudio.insert(preguntaEstudio);

            return preguntaEstudio;

    }

    @DELETE
    @Path("/eliminarEstudioPregunta/{id}")
    public Response eliminarEstudioPregunta(@PathParam("id") long id){

        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        PreguntaEstudio preguntaEstudio_eliminar = daoPreguntaEstudio.find(id, PreguntaEstudio.class);

        if (preguntaEstudio_eliminar != null){

            DaoRespuesta daoRespuesta = new DaoRespuesta();
            List<Respuesta> listaRespuesta = daoRespuesta.findAll(Respuesta.class);

            for (Respuesta respuesta: listaRespuesta){

                if(respuesta.get_preguntasEstudio().get_id() == id){
                    daoRespuesta.delete(respuesta);
                }
            }

            daoPreguntaEstudio.delete(preguntaEstudio_eliminar);
            return Response.ok().entity(preguntaEstudio_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
