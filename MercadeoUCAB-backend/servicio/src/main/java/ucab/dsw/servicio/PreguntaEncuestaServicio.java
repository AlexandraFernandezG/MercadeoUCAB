package ucab.dsw.servicio;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/preguntasEncuesta" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PreguntaEncuestaServicio extends AplicacionBase{

    //Listar preguntas
    @GET
    @Path("/allPreguntasEncuesta")
    @Produces( MediaType.APPLICATION_JSON )
    public List<PreguntaEncuesta> listarPreguntas() throws NullPointerException{
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

        try {
            return daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Consultar una pregunta
    @GET
    @Path("/consultarPreguntaEncuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public PreguntaEncuesta encontrarPreguntaEncuesta(@PathParam("id") long id) throws NullPointerException{
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();

        try {
            return daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Mostrar las preguntas activas
    @GET
    @Path("/mostrarPreguntasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public List<PreguntaEncuesta> preguntasActivas() throws NullPointerException{
        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        List<PreguntaEncuesta> listaPreguntas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
        List<PreguntaEncuesta> listaPreguntasActivas = new ArrayList<PreguntaEncuesta>();

        try {

            for (PreguntaEncuesta preguntaEncuesta : listaPreguntas) {

                if (preguntaEncuesta.get_estatus().equals("Activo")) {
                    listaPreguntasActivas.add(preguntaEncuesta);
                }
            }
            return listaPreguntasActivas;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
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
                    .add("excepcion", "No se ha realizado la operacion con éxito: " + ex.getMessage())
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
                    .add("excepcion", "No se ha realizado la operacion con éxito: " + ex.getMessage())
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

    //Actualizar estatus de pregunta
    @PUT
    @Path("/estatusPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusPregunta(@PathParam("id") long id, PreguntaEncuestaDto preguntaEncuestaDto){

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_modificar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        if (preguntaEncuesta_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

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

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(preguntaEncuesta_modificar).build();

    }

    //Actualizar pregunta
    @PUT
    @Path("/updatePreguntaEncuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarPreguntaEncuesta(@PathParam("id") long id, PreguntaEncuestaDto preguntaEncuestaDto){

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_modificar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        if (preguntaEncuesta_modificar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {
                preguntaEncuesta_modificar.set_descripcion(preguntaEncuestaDto.getDescripcion());
                preguntaEncuesta_modificar.set_tipoPregunta(preguntaEncuestaDto.getTipoPregunta());
                preguntaEncuesta_modificar.set_estatus(preguntaEncuestaDto.getEstatus());
                daoPreguntaEncuesta.update(preguntaEncuesta_modificar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(preguntaEncuesta_modificar).build();

    }

    //Eliminar una pregunta
    @DELETE
    @Path("/deletePreguntaEncuesta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarPreguntaEncuesta(@PathParam("id") long id){

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta_eliminar = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        if (preguntaEncuesta_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {
                daoPreguntaEncuesta.delete(preguntaEncuesta_eliminar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(preguntaEncuesta_eliminar).build();

    }
}
