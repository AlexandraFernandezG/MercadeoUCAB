package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEstudio;

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
    @Produces( MediaType.APPLICATION_JSON )
    public List<PreguntaEncuesta> listarPreguntasEstudio(@PathParam("id") long id) throws NullPointerException{

        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        List<PreguntaEstudio> listaEstudioPregunta = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
        List<PreguntaEncuesta> listaPreguntasEstudio = new ArrayList<PreguntaEncuesta>();

        try {

            for (PreguntaEstudio preguntaEstudio : listaEstudioPregunta) {

                if (preguntaEstudio.get_estudio().get_id() == id) {

                    long idFk = preguntaEstudio.get_preguntaEncuesta().get_id();
                    DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
                    PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idFk, PreguntaEncuesta.class);
                    listaPreguntasEstudio.add(preguntaEncuesta);
                }
            }
            return listaPreguntasEstudio;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    // Agregar la relacion Pregunta Estudio
    @POST
    @Path("/addPreguntaEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public PreguntaEstudioDto addPreguntaEstudio(PreguntaEstudioDto preguntaEstudioDto){

        PreguntaEstudioDto resultado = new PreguntaEstudioDto();

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

            } catch (Exception ex){

                String mensaje = ex.getMessage();
                System.out.print(mensaje);
            }

            return resultado;

    }

    //Actualizar el estatus de Preguntas estudio
    @PUT
    @Path("/updatePreguntaEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusPreguntaEstudio(@PathParam("id") long id, PreguntaEstudioDto preguntaEstudioDto){

        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        PreguntaEstudio preguntaEstudio_modificar = daoPreguntaEstudio.find(id, PreguntaEstudio.class);

        if (preguntaEstudio_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            preguntaEstudio_modificar.set_estatus(preguntaEstudioDto.getEstatus());
            daoPreguntaEstudio.delete(preguntaEstudio_modificar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(preguntaEstudio_modificar).build();

    }

    //Eliminar estudio pregunta
    @DELETE
    @Path("/eliminarPreguntaEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarEstudioPreguntaEstudio(@PathParam("id") long id){

        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        PreguntaEstudio preguntaEstudio_eliminar = daoPreguntaEstudio.find(id, PreguntaEstudio.class);

        if (preguntaEstudio_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {
                daoPreguntaEstudio.delete(preguntaEstudio_eliminar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(preguntaEstudio_eliminar).build();

    }
}
