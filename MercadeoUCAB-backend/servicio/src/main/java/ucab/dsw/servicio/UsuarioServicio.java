package ucab.dsw.servicio;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.response.UsuarioResponse;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonObject;
import javax.mail.MessagingException;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioServicio extends AplicacionBase {

    /**
     * Este método permite obtener todos los usuarios.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de usuarios y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {

        DaoUsuario daoUsuario = new DaoUsuario();
        JsonObject dataObject;

        try {
            List<Usuario> listaUsuarios = daoUsuario.findAll(Usuario.class);
            return Response.status(Response.Status.OK).entity(listaUsuarios).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener todos los analistas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los usuarios analistas y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allAnalistas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAnalistas() {

        DaoUsuario daoUsuario = new DaoUsuario();
        JsonObject dataObject;

        try {

            List<Object[]> listaAnalistas = daoUsuario.listarAnalistas();
            List<UsuarioResponse> listaAnalistaResponse = new ArrayList<>();

            for (Object[] ana: listaAnalistas){

                listaAnalistaResponse.add(new UsuarioResponse((long)ana[0], (String)ana[1], (String)ana[2], (String)ana[3], (String)ana[4]));
            }

            return Response.status(Response.Status.OK).entity(listaAnalistaResponse).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener un usuario cuando le pasas un correo.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * el usuario del correo consultado y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/consultarUsuarioCorreo/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuarioCorreo(@PathParam("email") String email) {

        JsonObject dataObject;
        DaoUsuario daoUsuario = new DaoUsuario();

        try {

            List<Object[]> usuarioCorreo = daoUsuario.usuarioCorreo(email);
            List<UsuarioResponse> listaUsuarioDefinitiva = new ArrayList<>(usuarioCorreo.size());

            for (Object[] us: usuarioCorreo){

                listaUsuarioDefinitiva.add(new UsuarioResponse((long)us[0], (String)us[1], (String)us[2], (String)us[3], (String)us[4]));
            }

            return Response.status(Response.Status.OK).entity(listaUsuarioDefinitiva).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite obtener un usuario.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el usuario consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del usuario que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuario(@PathParam("id") long id) {

        DaoUsuario daoUsuario = new DaoUsuario();
        JsonObject dataObject;

        try {
            Usuario usuario_consultado = daoUsuario.find(id, Usuario.class);

            return Response.status(Response.Status.OK).entity(usuario_consultado).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el usuario: " + ex.getMessage())
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

    @POST
    @Path("/addUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuario(UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
        JsonObject dataObject;
        try {
            DirectorioActivo ldap = new DirectorioActivo();
            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();

            usuario.set_nombre(usuarioDto.getNombreUsuario());
            usuario.set_correoelectronico(usuarioDto.getCorreo());
            usuario.set_estatus(usuarioDto.getEstatus());
            DaoRol daoRol = new DaoRol();
            Rol rol = new Rol();
            rol = daoRol.find(usuarioDto.getRol().getId(), Rol.class);
            RolDto rolDto = new RolDto(rol.get_id());
            rolDto.setNombre(rol.get_nombre());
            rolDto.setEstatus(rol.get_estatus());
            usuarioDto.setRol(rolDto);
            usuario.set_rol(rol);
            usuario.set_codigoRecuperacion(usuarioDto.getCodigoRecuperacion());
            Usuario resul = dao.insert(usuario);
            resultado.setId(resul.get_id());
            ldap.addEntryToLdap(usuarioDto);
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
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                    .add("codigo", 400).build();
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite modificar un usuario
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el usuario modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param usuarioDto el objeto usuario que el sistema desea modificar.
     * @param id el id del usuario a modificar
     */
    @PUT
    @Path("/updateUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateUsuario(@PathParam("id") long id, UsuarioDto usuarioDto){

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_modificar = daoUsuario.find(id, Usuario.class);
        JsonObject dataObject;


        try {

            usuario_modificar.set_nombre(usuarioDto.getNombreUsuario());
            Rol rol = new Rol(usuarioDto.getRol().getId());
            usuario_modificar.set_rol(rol);
            daoUsuario.update(usuario_modificar);
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.updateEntry(usuarioDto);
            return Response.status(Response.Status.OK).entity(usuario_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el usuario a modificar: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }


    }

    /**
     * Este método permite eliminar un usuario
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del usuario a eliminar
     */
    @DELETE
    @Path("/deleteUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteUsuario(@PathParam("id") long id) throws Exception {

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_eliminar = daoUsuario.find(id, Usuario.class);
        JsonObject dataObject;

        try {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setCorreo(usuario_eliminar.get_correoelectronico());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.deleteEntry(usuarioDto);
            daoUsuario.delete(usuario_eliminar);
            return Response.status(Response.Status.OK).entity(usuario_eliminar).build();

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

    }

    /**
     * Este método permite cambiar la contraseña de un usuario.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @param usuarioDto el usuario a cambiar la contraseña
     */
    @PUT
    @Path("/changePassword")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response changePassword(UsuarioDto usuarioDto){

        try {
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.changePassword(usuarioDto);
            return Response.status(Response.Status.OK).entity(usuarioDto).build();

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }


    }

    /**
     * Este método permite modificar el estatus de un usuario
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el usuario modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param usuarioDto el objeto usuario que el sistema desea modificar.
     * @param id el id del usuario a modificar
     */
    @PUT
    @Path("/estatusUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response estatusUsuario(@PathParam("id") long id, UsuarioDto usuarioDto){

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_modificar = daoUsuario.find(id, Usuario.class);
        JsonObject dataObject;

        try {

            if (usuario_modificar.get_estatus().equals("Activo")) {
                usuario_modificar.set_estatus("Inactivo");
                daoUsuario.update(usuario_modificar);
            }
            else if (usuario_modificar.get_estatus().equals("Inactivo")){
                usuario_modificar.set_estatus("Activo");
                daoUsuario.update(usuario_modificar);
            }

            return Response.status(Response.Status.OK).entity(usuario_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el usuario a modificar el estatus: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }

    /**
     * Este método permite solicitar una nueva contraseña en caso de que se haya olvidado
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo JsonObject con la respuesta a la solicitud
     * @param usuarioDto el objeto usuario que el sistema desea modificar.
     */
    @POST
    @Path("/recuperarClave")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public JsonObject recuperarClaveUsuario(UsuarioDto usuarioDto) throws MessagingException {

        Boolean registrado;
        JsonObject respuesta = null;
        String contenido;
        DirectorioActivo directorioActivo = new DirectorioActivo();


            try {

                DirectorioActivo ldap = new DirectorioActivo();
                String correoElectronico = usuarioDto.getCorreo();

                String randomClave = RandomStringUtils.randomAlphanumeric(10);

                usuarioDto.setContrasena(randomClave);
                ldap.changePassword(usuarioDto);

                contenido = "Estimado " + usuarioDto.getNombreUsuario() + " su contraseña ha sido actualizada. Su nueva contraseña es: " + randomClave;
                CorreoServicio.enviarCorreoElectronico(correoElectronico, "Recuperacion de clave", contenido);
                respuesta = Json.createObjectBuilder().add("estatus", "Correo enviado correctamente").build();

            } catch (Exception ex) {

                String mensaje = ex.getMessage();
                System.out.print(mensaje);
            }

        return respuesta;

    }
}