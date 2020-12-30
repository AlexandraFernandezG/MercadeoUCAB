package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
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

    //Listar posibles respuestas de pregunta
    @GET
    @Path("/allRespuestaPregunta")
    @Produces( MediaType.APPLICATION_JSON )
    public List<RespuestaPregunta> listarRespuestas() throws NullPointerException{
        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();

        try {
            return daoRespuestaPregunta.findAll(RespuestaPregunta.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Consultar una respuesta de pregunta
    @GET
    @Path("/consultarRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public RespuestaPregunta encontrarRespuestaPregunta(@PathParam("id") long id) throws NullPointerException{
        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();

        try {
            return daoRespuestaPregunta.find(id, RespuestaPregunta.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Mostrar respuestas activas
    @GET
    @Path("/mostrarRespuestasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public List<RespuestaPregunta> respuestasActivas() throws NullPointerException{
        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        List<RespuestaPregunta> listaRespuesta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);
        List<RespuestaPregunta> listaRespuestaActivas = new ArrayList<RespuestaPregunta>();

        try {
            for (RespuestaPregunta respuestaPregunta : listaRespuesta) {

                if (respuestaPregunta.get_estatus().equals("Activo")) {
                    listaRespuestaActivas.add(respuestaPregunta);
                }
            }
            return listaRespuestaActivas;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }
    
    //Agregar una respuesta de pregunta
    @POST
    @Path("/addRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public RespuestaPreguntaDto addRespuestaPregunta(@PathParam("id") long id, List<RespuestaPreguntaDto> respuestas){
        
        RespuestaPreguntaDto resultado = new RespuestaPreguntaDto();
        
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
            
        } catch (Exception ex){
            
            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }
        
        return resultado; // Devuelve la última respuesta de la lista.
    }

    //Actualizar respuesta pregunta
    @PUT
    @Path("/updateRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarRespuestaPregunta(@PathParam("id") long id, RespuestaPreguntaDto respuestaPreguntaDto){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta_modificar = daoRespuestaPregunta.find(id, RespuestaPregunta.class);

        if (respuestaPregunta_modificar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {

                respuestaPregunta_modificar.set_nombre(respuestaPreguntaDto.getNombre());
                respuestaPregunta_modificar.set_estatus(respuestaPreguntaDto.getEstatus());
                daoRespuestaPregunta.update(respuestaPregunta_modificar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(respuestaPregunta_modificar).build();

    }

    //Eliminar respuesta pregunta
    @DELETE
    @Path("/deleteRespuestaPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarRespuestaPregunta(@PathParam("id") long id){

        DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
        RespuestaPregunta respuestaPregunta_eliminar = daoRespuestaPregunta.find(id, RespuestaPregunta.class);

        if(respuestaPregunta_eliminar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {

                daoRespuestaPregunta.delete(respuestaPregunta_eliminar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(respuestaPregunta_eliminar).build();

    }

}
