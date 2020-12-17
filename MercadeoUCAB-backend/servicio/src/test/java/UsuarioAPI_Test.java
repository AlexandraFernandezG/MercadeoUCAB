import org.junit.*;
import ucab.dsw.dtos.UsuarioDto;

import javax.mail.MessagingException;

public class UsuarioAPI_Test {

    //Verificar cambio de contrasena
    @Test
    public void pruebaCambioClave() throws MessagingException {

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setNombreUsuario("EmanuelDriver");
        servicio.recuperarClaveUsuario(usuarioDto);
    }
}
