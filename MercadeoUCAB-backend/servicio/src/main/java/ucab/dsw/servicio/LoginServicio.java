package ucab.dsw.servicio;

import ucab.dsw.comando.Login.LoginComando;
import ucab.dsw.directorioactivo.DirectorioActivo;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.jwt.Jwt;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path( "/login" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LoginServicio extends AplicacionBase {
    @POST
    @Path( "/ldap" )
    public Response login(UsuarioDto usuarioDto) {
        try {
            LoginComando comando = Fabrica.crearComandoConDto(LoginComando.class, usuarioDto);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();
        } catch ( Exception ex ) {
            System.out.println("Excepcion");

            return Response.status(Response.Status.BAD_REQUEST).build();


        }

    }
}