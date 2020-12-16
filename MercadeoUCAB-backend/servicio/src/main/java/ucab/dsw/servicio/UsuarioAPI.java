package ucab.dsw.servicio;

import org.apache.commons.lang3.RandomStringUtils;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.mail.*;
import ucab.dsw.servicio.EmailUtility;


@Path( "/usuario" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class UsuarioAPI extends AplicacionBase{

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
            //String correoElectronico = ldap.getCorreo();

            //Este vendría siendo el correo que se optiene del get
            String correoElectronico = "emanuelesposito3@gmail.com";

            String randomClave = RandomStringUtils.randomAlphanumeric(10);

            //UsuarioDto usuario = new UsuarioDto();
            //usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
            //usuario.setContrasena(randomClave);
            //directorioActivo.changePassword(usuario);

            contenido = "Estimado " + usuarioDto.getNombreUsuario() + " su contraseña ha sido actualizada. Su nueva contraseña es: "+randomClave;
            ucab.dsw.servicio.EmailUtility.enviarCorreoElectronico(correoElectronico, "Recuperacion de clave", contenido);
            respuesta = Json.createObjectBuilder().add("estatus", "Correo enviado correctamente").build();

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

        return respuesta;

    }

}
