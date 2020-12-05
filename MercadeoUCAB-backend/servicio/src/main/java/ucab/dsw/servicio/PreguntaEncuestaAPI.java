package ucab.dsw.servicio;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoRespuestaPregunta;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.RespuestaPregunta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.entidades.Usuario;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/preguntasEncuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PreguntaEncuestaAPI extends AplicacionBase{

    @GET
    @Path("/allPreguntasEncuesta")
    public List<PreguntaEncuesta> listarPreguntas(){
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        return daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
    }

    @GET
    @Path("/consultarPreguntaEncuesta/{id}")
    public PreguntaEncuesta encontrarPreguntaEncuesta(@PathParam("id") long id){
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        return daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);
    }

    @GET
    @Path("/mostrarPreguntasActivas")
    public List<PreguntaEncuesta> mostrarPreguntasActivas(){
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        List<PreguntaEncuesta> listaPreguntas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
        List<PreguntaEncuesta> listaPreguntasActivas = null;

        for (PreguntaEncuesta preguntaEncuesta: listaPreguntas){

            if(preguntaEncuesta.get_estatus() == "Activo"){
                listaPreguntasActivas.add(preguntaEncuesta);
            }
        }
        return listaPreguntasActivas;
    }

    @POST
    @Path("/addPreguntaEncuesta")
    public PreguntaEncuesta addPreguntaEncuesta(PreguntaEncuestaDto preguntaEncuestaDto){

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta = new PreguntaEncuesta();

            preguntaEncuesta.setDescripcion(preguntaEncuestaDto.get_descripcion());
            preguntaEncuesta.setTipoPregunta(preguntaEncuestaDto.get_tipoPregunta());
            preguntaEncuesta.set_estatus(preguntaEncuestaDto.get_estatus());
            Usuario usuario = new Usuario(preguntaEncuestaDto.get_usuarioDto().getId());
            preguntaEncuesta.setUsuario(usuario);
            Subcategoria subcategoria = new Subcategoria(preguntaEncuestaDto.get_subcategoriaDto().getId());
            preguntaEncuesta.setSubcategoria(subcategoria);
            daoPreguntaEncuesta.insert(preguntaEncuesta);

            return preguntaEncuesta;
    }

    @PUT
    @Path("/updatePreguntaEncuesta/{id}")
    public Response modificarPreguntaEncuesta(@PathParam("id") long id, PreguntaEncuestaDto preguntaEncuestaDto){

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_modificar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        if (preguntaEncuesta_modificar != null){

            preguntaEncuesta_modificar.setDescripcion(preguntaEncuestaDto.get_descripcion());
            preguntaEncuesta_modificar.setTipoPregunta(preguntaEncuestaDto.get_tipoPregunta());
            preguntaEncuesta_modificar.set_estatus(preguntaEncuestaDto.get_estatus());
            daoPreguntaEncuesta.update(preguntaEncuesta_modificar);
            DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();

            if(preguntaEncuesta_modificar.get_estatus() == "Inactivo"){

                List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);

                for(RespuestaPregunta respuestaPregunta: listaRespuesta){

                    if(respuestaPregunta.getPreguntaEncuesta().get_id() == id){
                        respuestaPregunta.set_estatus("Inactivo");
                        daoRespuestaPregunta.update(respuestaPregunta);
                    }
                }
            } else if (preguntaEncuesta_modificar.get_estatus() == "Activo"){

                List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);

                for(RespuestaPregunta respuestaPregunta: listaRespuesta){

                    if(respuestaPregunta.getPreguntaEncuesta().get_id() == id){
                        respuestaPregunta.set_estatus("Activo");
                        daoRespuestaPregunta.update(respuestaPregunta);
                    }
                }
            }
            return Response.ok().entity(preguntaEncuesta_modificar).build();
        } else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deletePreguntaEncuesta/{id}")
    public Response eliminarPreguntaEncuesta(@PathParam("id") long id){

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_eliminar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        if (preguntaEncuesta_eliminar != null){

            DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
            List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);

            for(RespuestaPregunta respuestaPregunta: listaRespuesta) {

                if (respuestaPregunta.getPreguntaEncuesta().get_id() == id) {
                    daoRespuestaPregunta.delete(respuestaPregunta);
                }
            }
            daoPreguntaEncuesta.delete(preguntaEncuesta_eliminar);
            return Response.ok().entity(preguntaEncuesta_eliminar).build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
