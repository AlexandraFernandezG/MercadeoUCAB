
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.servicio.RolServicio;

import javax.ws.rs.core.Response;

public class RolServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarRoles
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarRoles(){

        RolServicio servicio = new RolServicio();
        Response respuesta = servicio.listarRoles();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarRol
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarRol(){

        RolServicio servicio = new RolServicio();
        Response respuesta = servicio.consultarRol(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarRol
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarRol(){

        RolServicio servicio = new RolServicio();

        try {

            RolDto rolDto = new RolDto();
            rolDto.setNombre("Administrador");
            rolDto.setEstatus("Activo");
            Response respuesta = servicio.addRol(rolDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarRol
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarRol(){

        RolServicio servicio = new RolServicio();

        try {

            long id = 1;
            RolDto rolDto = new RolDto();
            rolDto.setNombre("Administrador");
            rolDto.setEstatus("Activo");
            Response respuesta = servicio.updateRol(1, rolDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarRol
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarRol(){

        RolServicio servicio = new RolServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.deleteRol(1);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }


}
