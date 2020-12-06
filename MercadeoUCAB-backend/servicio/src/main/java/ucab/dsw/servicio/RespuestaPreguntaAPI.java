package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoRespuestaPregunta;
import ucab.dsw.dtos.RespuestaPreguntaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.RespuestaPregunta;
import ucab.dsw.entidades.Subcategoria;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/respuestaPregunta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RespuestaPreguntaAPI extends AplicacionBase{

    @GET
    @Path("/allRespuestaPregunta")
    public List<RespuestaPregunta> listarRespuestas(){
        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        return daoRespuestaPregunta.findAll(RespuestaPregunta.class);
    }

    @GET
    @Path("/consultarRespuestaPregunta/{id}")
    public RespuestaPregunta encontrarRespuestaPregunta(@PathParam("id") long id){
        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        return daoRespuestaPregunta.find(id, RespuestaPregunta.class);
    }

    @GET
    @Path("/mostrarRespuestasActivas")
    public List<RespuestaPregunta> respuestasActivas(){
        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);
        List<RespuestaPregunta> listaRespuestaActivas = new ArrayList<RespuestaPregunta>();

        for (RespuestaPregunta respuestaPregunta: listaRespuesta){

            if (respuestaPregunta.get_estatus().equals("Activo")){
                listaRespuestaActivas.add(respuestaPregunta);
            }
        }
        return listaRespuestaActivas;
    }

    @POST
    @Path("/addRespuestaPregunta")
    public RespuestaPregunta addRespuestaPregunta(RespuestaPreguntaDto respuestaPreguntaDto){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta = new RespuestaPregunta();

            respuestaPregunta.setNombre(respuestaPreguntaDto.get_nombre());
            respuestaPregunta.set_estatus(respuestaPreguntaDto.get_estatus());
            PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta(respuestaPreguntaDto.get_preguntaEncuestaDto().getId());
            respuestaPregunta.setPreguntaEncuesta(preguntaEncuesta);
            daoRespuestaPregunta.insert(respuestaPregunta);

            return respuestaPregunta;
    }

    @PUT
    @Path("/updateRespuestaPregunta/{id}")
    public Response modificarRespuestaPregunta(@PathParam("id") long id, RespuestaPreguntaDto respuestaPreguntaDto){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta_modificar = daoRespuestaPregunta.find(id, RespuestaPregunta.class);

        if (respuestaPregunta_modificar != null) {

            respuestaPregunta_modificar.setNombre(respuestaPreguntaDto.get_nombre());
            respuestaPregunta_modificar.set_estatus(respuestaPreguntaDto.get_estatus());
            daoRespuestaPregunta.update(respuestaPregunta_modificar);
            return Response.ok().entity(respuestaPregunta_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteRespuestaPregunta/{id}")
    public Response eliminarRespuestaPregunta(@PathParam("id") long id){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta_eliminar = daoRespuestaPregunta.find(id, RespuestaPregunta.class);

        if(respuestaPregunta_eliminar != null) {

            daoRespuestaPregunta.delete(respuestaPregunta_eliminar);
            return Response.ok().entity(respuestaPregunta_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
