package ucab.dsw.servicio;

import org.apache.commons.lang3.RandomStringUtils;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioServicio extends AplicacionBase {

    @GET
    @Path("/allUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listarUsuarios() throws NullPointerException {

        DaoUsuario daoUsuario = new DaoUsuario();

        try {
            return daoUsuario.findAll(Usuario.class);

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    @GET
    @Path("/consultarUsuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario consultarUsuario(@PathParam("id") long id) throws NullPointerException{

        DaoUsuario daoUsuario = new DaoUsuario();

        try {
            return daoUsuario.find(id, Usuario.class);

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    @GET
    @Path("/consultarUsuarioCorreo/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioResponse> consultarUsuarioCorreo(@PathParam("email") String email) throws NullPointerException{

        /**
        * Este metodo permite obtener un usuario cuando le pasas un correo
        *
        *
        * */

        String SQL = null;

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            SQL = "SELECT u._id as idUsuario, u._nombre as nombre, u._codigoRecuperacion as codigoRecuperacion, u._correoelectronico as correo, u._estatus as estatus " +
                    "FROM Usuario as u " +
                    "WHERE u._correoelectronico = :email";

            Query query = entitymanager.createQuery(SQL);
            query.setParameter("email", email);

            List<Object[]> listaUsuario = query.getResultList();

            List<UsuarioResponse> listaUsuarioDefinitiva = new ArrayList<>(listaUsuario.size());

            for (Object[] us: listaUsuario){

                listaUsuarioDefinitiva.add(new UsuarioResponse((long)us[0], (String)us[1], (String)us[2], (String)us[3], (String)us[4]));
            }

            return listaUsuarioDefinitiva;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }


    }


    @POST
    @Path("/addUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UsuarioDto addUsuario(UsuarioDto usuarioDto) {
        UsuarioDto resultado = new UsuarioDto();
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

        }catch (javax.naming.NameAlreadyBoundException ex){
            String problema = ex.getMessage();
            System.out.print(problema);
        }catch (javax.persistence.PersistenceException ex) {
            String problema = ex.getMessage();
            System.out.print(problema);
        }
        catch (Exception ex) {

            String problema = ex.getMessage();
            System.out.print(problema);
        }
        return resultado;
    }

    @PUT
    @Path("/updateUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateUsuario(@PathParam("id") long id, UsuarioDto usuarioDto){

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_modificar = daoUsuario.find(id, Usuario.class);

        if(usuario_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            usuario_modificar.set_nombre(usuarioDto.getNombreUsuario());
            Rol rol = new Rol(usuarioDto.getRol().getId());
            usuario_modificar.set_rol(rol);
            daoUsuario.update(usuario_modificar);
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.updateEntry(usuarioDto);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(usuario_modificar).build();

    }

    //Falta eliminar en el LDAP
    @DELETE
    @Path("/deleteUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteUsuario(@PathParam("id") long id) throws Exception {

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_eliminar = daoUsuario.find(id, Usuario.class);

        if(usuario_eliminar == null) {


            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setCorreo(usuario_eliminar.get_correoelectronico());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.deleteEntry(usuarioDto);
            daoUsuario.delete(usuario_eliminar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(usuario_eliminar).build();

    }

    @PUT
    @Path("/changePassword")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response changePassword(UsuarioDto usuarioDto){

        try {
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.changePassword(usuarioDto);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(usuarioDto).build();

    }

    //Cambiar el estado a inactivo
    @PUT
    @Path("/estatusUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response estatusUsuario(@PathParam("id") long id, UsuarioDto usuarioDto){

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_modificar = daoUsuario.find(id, Usuario.class);

        if(usuario_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            if (usuario_modificar.get_estatus().equals("Activo")) {
                usuario_modificar.set_estatus("Inactivo");
                daoUsuario.update(usuario_modificar);
            }
            else if (usuario_modificar.get_estatus().equals("Inactivo")){
                usuario_modificar.set_estatus("Activo");
                daoUsuario.update(usuario_modificar);
            }

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(usuario_modificar).build();

    }

    //Recuperar clave de usuario por correo
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