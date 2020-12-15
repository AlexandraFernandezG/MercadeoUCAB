package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioAPI extends AplicacionBase {

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
    public Usuario consultarUsuario(@PathParam("id") long id) {

        DaoUsuario daoUsuario = new DaoUsuario();

        try {
            return daoUsuario.find(id, Usuario.class);

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

            DaoUsuario dao = new DaoUsuario();
            Usuario usuario = new Usuario();

            usuario.set_nombre(usuarioDto.getNombreUsuario());
            usuario.set_correoelectronico(usuarioDto.getCorreo());
            usuario.set_estatus(usuarioDto.getEstatus());
            Rol rol = new Rol(usuarioDto.getRol().getId());
            usuario.set_rol(rol);
            usuario.set_codigoRecuperacion(usuarioDto.getCodigoRecuperacion());
            Usuario resul = dao.insert(usuario);
            resultado.setId(resul.get_id());
            DirectorioActivo ldap = new DirectorioActivo();
            ldap.addEntryToLdap(usuarioDto);
        } catch (Exception ex) {

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
            usuario_modificar.set_correoelectronico(usuarioDto.getCorreo());
            Rol rol = new Rol(usuarioDto.getRol().getId());
            usuario_modificar.set_rol(rol);
            usuario_modificar.set_estatus(usuarioDto.getEstatus());
            usuario_modificar.set_codigoRecuperacion(usuarioDto.getCodigoRecuperacion());
            daoUsuario.update(usuario_modificar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(usuario_modificar).build();

    }

    //Falta eliminar en el LDAP
    @DELETE
    @Path("/deleteUsuario/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteUsuario(@PathParam("id") long id){

        DaoUsuario daoUsuario = new DaoUsuario();
        Usuario usuario_eliminar = daoUsuario.find(id, Usuario.class);

        if(usuario_eliminar == null) {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoUsuario.delete(usuario_eliminar);

        } catch (Exception ex) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(usuario_eliminar).build();

    }
}