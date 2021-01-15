import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.servicio.PresentacionServicio;

import javax.ws.rs.core.Response;

public class PresentacionServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarPresentaciones
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarPresentaciones(){

        PresentacionServicio servicio = new PresentacionServicio();
        Response respuesta = servicio.listarPresentaciones();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarPresentacion
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarPresentacion(){

        PresentacionServicio servicio = new PresentacionServicio();
        Response respuesta = servicio.consultarPresentacion(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarPresentacionesActivas
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarPresentacionesActivas(){

        PresentacionServicio servicio = new PresentacionServicio();
        Response respuesta = servicio.presentacionesActivas();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarPresentacion
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarPresentacion() {

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            PresentacionDto presentacionDto = new PresentacionDto();

            presentacionDto.setNombre("");
            presentacionDto.setDescripcion("");
            presentacionDto.setEstatus("Activo");
            Response respuesta = servicio.addPresentacion(presentacionDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarPresentacion
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarPresentacion(){

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            PresentacionDto presentacionDto = new PresentacionDto();

            presentacionDto.setNombre("");
            presentacionDto.setDescripcion("");
            presentacionDto.setEstatus("Activo");
            // Estar mosca con los id de la bd
            Response respuesta = servicio.updatePresentacion(1, presentacionDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarPresentacion
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarPresentacion(){

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            // Estar mosca con los id de la bd
            Response respuesta = servicio.eliminarPresentacion(1);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
