
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.UsuarioEstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.UsuarioEstudioServicio;

import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioEstudioServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEstudiosEncuestado
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarEstudiosEncuestado(){

        UsuarioEstudioServicio servicio = new UsuarioEstudioServicio();
        Response respuesta = servicio.listarEstudiosEncuestado(3);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEncuestadosEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarEncuestadosEstudio(){

        UsuarioEstudioServicio servicio = new UsuarioEstudioServicio();
        Response respuesta = servicio.listarEncuestadosEstudio(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarUsuarioEstudiosActivos
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarUsuarioEstudiosActivos(){

        UsuarioEstudioServicio servicio = new UsuarioEstudioServicio();
        Response respuesta = servicio.usuarioEstudioActivos();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());


    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarUsuarioEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarUsuarioEstudio(){

        UsuarioEstudioServicio servicio = new UsuarioEstudioServicio();

        try {

            UsuarioEstudioDto usuarioEstudioDto = new UsuarioEstudioDto();

            usuarioEstudioDto.setEstatus("Activo");
            UsuarioDto usuarioDto = new UsuarioDto(1);
            usuarioEstudioDto.setUsuarioDto(usuarioDto);
            EstudioDto estudioDto = new EstudioDto(1);
            usuarioEstudioDto.setEstudioDto(estudioDto);
            Response resultado = servicio.addUsuarioEstudio(usuarioEstudioDto);
            Assert.assertNotNull(resultado);

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarEstatusUsuarioEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarEstatusUsuarioEstudio(){

        UsuarioEstudioServicio servicio = new UsuarioEstudioServicio();

        try {

            UsuarioEstudioDto usuarioEstudioDto = new UsuarioEstudioDto();
            usuarioEstudioDto.setEstatus("Activo");
            //Estar pendientes con los ids de la base de datos
            servicio.modificarEstatusUsuarioEstudio(1, usuarioEstudioDto);

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarUsuarioEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarUsuarioEstudio(){

        UsuarioEstudioServicio servicio = new UsuarioEstudioServicio();

        try {

            //Estar pendientes con los ids de la base de datos
            servicio.eliminarUsuarioEstudio(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

}
