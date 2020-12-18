import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Usuario;

import javax.mail.MessagingException;

public class UsuarioAPI_Test {

    //Prueba insertar usuario
    @Test
    public void pruebaInsertarUsuario(){

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();
        UsuarioDto usuarioDto = new UsuarioDto();

        try {

            usuarioDto.setNombreUsuario("greggspinetti");
            usuarioDto.setCorreo("greggspinetti@gmail.com");
            usuarioDto.setEstatus("Activo");
            usuarioDto.setContrasena("hola123");
            // Recuerden que deben ver los id de los registros en la BD
            RolDto rolDto = new RolDto(1);
            usuarioDto.setRol(rolDto);
            usuarioDto.setCodigoRecuperacion(null);
            UsuarioDto resultado = servicio.addUsuario(usuarioDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba modificar usuario
    @Test
    public void pruebaModificarUsuario(){

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();

        try {

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNombreUsuario("gregg");
            usuarioDto.setCorreo("greggspinetti@gmail.com");
            usuarioDto.setEstatus("Inactivo");
            // Recuerden que deben ver los id de los registros en la BD
            RolDto rolDto = new RolDto(1);
            usuarioDto.setRol(rolDto);
            usuarioDto.setCodigoRecuperacion(null);
            servicio.updateUsuario(1, usuarioDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Cambiar password en usuario
    @Test
    public void pruebaModificarPassword(){

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();

        try {

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setCorreo("greggspinetti@gmail.com");
            usuarioDto.setContrasena("hola");
            servicio.changePassword(usuarioDto);


        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba modificar estatus usuario
    @Test
    public void pruebaModificarEstatusUsuario(){

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();

        try {

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.estatusUsuario(12, usuarioDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Verificar cambio de contrasena
    @Test
    public void pruebaCambioClave() throws MessagingException {

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();

        try {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNombreUsuario("gregg");
            usuarioDto.setCorreo("greggspinetti@gmail.com");
            servicio.recuperarClaveUsuario(usuarioDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba eliminar usuario
    @Test
    public void pruebaEliminarUsuario(){

        ucab.dsw.servicio.UsuarioAPI servicio = new ucab.dsw.servicio.UsuarioAPI();

        try {
            servicio.deleteUsuario(11);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
