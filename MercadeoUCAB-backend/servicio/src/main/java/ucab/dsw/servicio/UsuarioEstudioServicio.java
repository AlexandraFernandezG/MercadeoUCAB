package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoUsuarioEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.comando.UsuarioEstudio.EstudiosEncuestadoComando;
import ucab.dsw.dtos.UsuarioEstudioDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.UsuarioEstudio;
import ucab.dsw.entidades.Usuario;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.Response.EstudiosResponse;
import ucab.dsw.Response.UsuarioResponse;
import ucab.dsw.fabrica.Fabrica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/usuarioEstudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioEstudioServicio extends AplicacionBase{

    /**
     * Este método permite obtener los estudios de un encuestado
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de estudios de un encuestado y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     */
    @GET
    @Path("/estudiosEncuestado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosEncuestado(@PathParam("id") long id) {

        JsonObject dataObject;

        try {

            EstudiosEncuestadoComando comando = Fabrica.crearComandoConId(EstudiosEncuestadoComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

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
     * Este método permite obtener los encuestados de un estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los encuestados de un estudio y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     */
    @GET
    @Path("/encuestadosEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEncuestadosEstudio(@PathParam("id") long id) {

        DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
        JsonObject dataObject;

        try {

            List<Object[]> listaUsuarioEstudios = daoUsuarioEstudio.listarEncuestadosEstudio(id);

            List<UsuarioResponse> listaEncuestadosEstudio = new ArrayList<>(listaUsuarioEstudios.size());

            for (Object[] user : listaUsuarioEstudios) {

                listaEncuestadosEstudio.add(new UsuarioResponse((long)user[0], (String)user[1], (String)user[2], (String)user[3], (String)user[4]));
            }

            return Response.status(Response.Status.OK).entity(listaEncuestadosEstudio).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

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
     * Este método permite insertar un estudio con un encuestado
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el usuarioEstudio insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un usuarioEstudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param usuarioEstudioDto el objeto usuarioEstudio que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addUsuarioEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addUsuarioEstudio(UsuarioEstudioDto usuarioEstudioDto){

        UsuarioEstudioDto resultado = new UsuarioEstudioDto();
        JsonObject dataObject;

        try {

            DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
            UsuarioEstudio usuarioEstudio = new UsuarioEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoEstudio daoEstudio = new DaoEstudio();

            usuarioEstudio.set_estatus(usuarioEstudioDto.getEstatus());
            Usuario usuario = daoUsuario.find(usuarioEstudioDto.getUsuarioDto().getId(), Usuario.class);
            usuarioEstudio.set_usuario(usuario);
            Estudio estudio = daoEstudio.find(usuarioEstudioDto.getEstudioDto().getId(), Estudio.class);
            usuarioEstudio.set_estudio(estudio);
            UsuarioEstudio resul = daoUsuarioEstudio.insert(usuarioEstudio);
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
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

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
     * Este método permite modificar el estatus de usuarioEstudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el usuarioEstudio modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un usuarioEstudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param idE el id del estudio
     * @patam idU el id del usuario
     */
    @PUT
    @Path("/estatusUsuarioProgreso/{idE}/{idU}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusProgreso(@PathParam("idE") long idE, @PathParam("idU") long idU){

        DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
        JsonObject dataObject;

            try {

                List<UsuarioEstudio> listaUsuarioEstudio = daoUsuarioEstudio.findAll(UsuarioEstudio.class);

                for(UsuarioEstudio user: listaUsuarioEstudio){

                    if(user.get_estudio().get_id() == idE && user.get_usuario().get_id() == idU){

                        UsuarioEstudio usuarioEstudio_modificar = daoUsuarioEstudio.find(user.get_id(), UsuarioEstudio.class);
                        usuarioEstudio_modificar.set_estatus("En progreso");
                        daoUsuarioEstudio.update(usuarioEstudio_modificar);
                    }
                }

                dataObject = Json.createObjectBuilder()
                        .add("estado", 200)
                        .add("Mensaje", "Operacion realizada con exito").build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }

    /**
     * Este método permite eliminar un usuarioEstudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe..
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del usuarioEstudio a eliminar
     */
    @DELETE
    @Path("/deleteHistoricoEstado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarUsuarioEstudio(@PathParam("id") long id){

        DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
        UsuarioEstudio usuarioEstudio_eliminar = daoUsuarioEstudio.find(id, UsuarioEstudio.class);
        JsonObject dataObject;

            try {
                daoUsuarioEstudio.delete(usuarioEstudio_eliminar);

                return Response.status(Response.Status.OK).entity(usuarioEstudio_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }
}
