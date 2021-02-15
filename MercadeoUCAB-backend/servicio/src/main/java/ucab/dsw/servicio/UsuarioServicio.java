package ucab.dsw.servicio;

import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.comando.Usuario.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Usuario;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.mail.MessagingException;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(UsuarioServicio.class);
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los usuarios");
        JsonObject dataObject;

        try {

            ListarUsuariosComando comando = Fabrica.crear(ListarUsuariosComando.class);
            comando.execute();
            logger.debug("Saliendo del método que lista todos los usuarios");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los analistas");
        JsonObject dataObject;

        try {

            ListarAnalistasComando comando = Fabrica.crear(ListarAnalistasComando.class);
            comando.execute();
            logger.debug("Saliendo del método que lista todos los analistas");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener todos los encuestados.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de los usuarios encuestados y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allEncuestados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEncuestadosInformacion(){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todos los encuestados");
        JsonObject dataObject;

        try {

            ListarEncuestadosComando comando = Fabrica.crear(ListarEncuestadosComando.class);
            comando.execute();
            logger.debug("Saliendo del método que lista todos los encuestados");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista un usuario por el correo");
        JsonObject dataObject;

        try {

            ConsultarUsuarioCorreoComando comando = Fabrica.crearComandoCorreo(ConsultarUsuarioCorreoComando.class, email);
            comando.execute();
            logger.debug("Saliendo del método que lista un usuario por el correo");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que consulta un usuario");
        JsonObject dataObject;

        try {

            ConsultarUsuarioComando comando = Fabrica.crearComandoConId(ConsultarUsuarioComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que consulta un usuario");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener los detalles de un usuario encuestado
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con los detalles del usuario consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del usuario que se quiere consultar.
     */
    @GET
    @Path("/detallesEncuestados/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response detallesEncuestados(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que devuelve los detalles de un encuestado");
        JsonObject dataObject;

        try {

            DetallesEncuestadosComando comando = Fabrica.crearComandoConId(DetallesEncuestadosComando.class, id);
            comando.execute();
            logger.debug("Saliendo del método que devuelve los detalles de un encuestado");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que añade un usuario");
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

            logger.debug("Saliendo del método que añade un usuario");
            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualiza un usuario");
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

            logger.debug("Saliendo del método que actualiza un usuario");
            return Response.status(Response.Status.OK).entity(usuario_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina un usuario");
        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_eliminar = daoUsuario.find(id, Usuario.class);
        JsonObject dataObject;

        try {

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setCorreo(usuario_eliminar.get_correoelectronico());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.deleteEntry(usuarioDto);
            daoUsuario.delete(usuario_eliminar);

            logger.debug("Saliendo del método que elimina un usuario");
            return Response.status(Response.Status.OK).entity(usuario_eliminar).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que cambia una contraseña");
        try {
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.changePassword(usuarioDto);

            logger.debug("Saliendo del método que cambia una contraseña");
            return Response.status(Response.Status.OK).entity(usuarioDto).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualiza un usuario");
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

            logger.debug("Saliendo del método que actualiza un usuario");
            return Response.status(Response.Status.OK).entity(usuario_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

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

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que recupera una clave");
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

                contenido = "Estimado usuario su contraseña ha sido actualizada. Su nueva contraseña es: " + randomClave;
                CorreoServicio.enviarCorreoElectronico(correoElectronico, "Recuperacion de clave", contenido);
                respuesta = Json.createObjectBuilder().add("estatus", "Correo enviado correctamente").build();

            } catch (Exception ex) {

                logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
                String mensaje = ex.getMessage();
                System.out.print(mensaje);
            }

        logger.debug("Saliendo del método que recupera una clave");
        return respuesta;

    }
}