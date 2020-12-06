package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/respuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaAPI extends AplicacionBase{

    @GET
    @Path("/allRespuesta")
    public List<Respuesta> listarRespuestas(){
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        return daoRespuesta.findAll(Respuesta.class);

    }

    @GET
    @Path("/consultarRespuesta/{id}")
    public Respuesta consultarRespuesta(@PathParam("id") long id){
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        return daoRespuesta.find(id, Respuesta.class);

    }

    @GET
    @Path("/mostrarRespuestasActivas")
    public List<Respuesta> respuestasActivas(){

        DaoRespuesta daoRespuesta = new DaoRespuesta();
        List<Respuesta> listaRespuesta = daoRespuesta.findAll(Respuesta.class);
        List<Respuesta> listaRespuestaActivas = new ArrayList<Respuesta>();

        for(Respuesta respuesta: listaRespuesta){

            if(respuesta.get_estatus().equals("Activo")){
                listaRespuestaActivas.add(respuesta);
            }
        }

        return listaRespuestaActivas;
    }

    @POST
    @Path("/addRespuesta")
    public Respuesta addRespuesta(RespuestaDto respuestaDto){

        DaoRespuesta daoRespuesta = new DaoRespuesta();
        Respuesta respuesta = new Respuesta();

            respuesta.setRespuestaAbierta(respuestaDto.get_respuestaAbierta());
            respuesta.setEscala(respuestaDto.get_escala());
            respuesta.setVerdaderoFalso(respuestaDto.get_verdaderoFalso());
            respuesta.setRespuestaSimple(respuestaDto.get_respuestaSimple());
            respuesta.setRespuestaMultiple(respuestaDto.get_respuestaMultiple());
            respuesta.set_estatus(respuestaDto.get_estatus());
            PreguntaEstudio preguntaEstudio = new PreguntaEstudio(respuestaDto.get_preguntaEstudioDto().getId());
            Usuario usuario = new Usuario(respuestaDto.get_usuarioDto().getId());
            respuesta.setPreguntasEstudio(preguntaEstudio);
            respuesta.setUsuario(usuario);

            return respuesta;
    }

    @PUT
    @Path("/updateRespuesta/{id}")
    public Response updateRespuesta(@PathParam("id") long id, RespuestaDto respuestaDto){

        DaoRespuesta daoRespuesta = new DaoRespuesta();
        Respuesta respuesta_modificar = daoRespuesta.find(id, Respuesta.class);

        if(respuesta_modificar != null){

            respuesta_modificar.setRespuestaAbierta(respuestaDto.get_respuestaAbierta());
            respuesta_modificar.setEscala(respuestaDto.get_escala());
            respuesta_modificar.setVerdaderoFalso(respuestaDto.get_verdaderoFalso());
            respuesta_modificar.setRespuestaSimple(respuestaDto.get_respuestaSimple());
            respuesta_modificar.setRespuestaMultiple(respuestaDto.get_respuestaMultiple());
            respuesta_modificar.set_estatus(respuestaDto.get_estatus());
            daoRespuesta.update(respuesta_modificar);
            return Response.ok().entity(respuesta_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteRespuesta/{id}")
    public Response eliminarRespuesta(@PathParam("id") long id){

        DaoRespuesta daoRespuesta = new DaoRespuesta();
        Respuesta respuesta_eliminar = daoRespuesta.find(id, Respuesta.class);

        if (respuesta_eliminar != null) {

            daoRespuesta.delete(respuesta_eliminar);
            return Response.ok().entity(respuesta_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }


}
