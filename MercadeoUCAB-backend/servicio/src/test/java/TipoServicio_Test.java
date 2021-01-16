import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.servicio.TipoServicio;

import javax.ws.rs.core.Response;

public class TipoServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarTipos
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarTipos(){

        TipoServicio servicio = new TipoServicio();
        Response respuesta = servicio.listarTipos();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarTipo
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarTipo(){

        TipoServicio servicio = new TipoServicio();
        Response respuesta = servicio.consultarTipo(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarTiposActivos
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarTiposActivos(){

        TipoServicio servicio = new TipoServicio();
        Response respuesta = servicio.tiposActivos();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarTipo
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarTipo() {

        TipoServicio servicio = new TipoServicio();

        try {
            TipoDto tipoDto = new TipoDto();

            tipoDto.setNombre("");
            tipoDto.setDescripcion("");
            tipoDto.setEstatus("Activo");
            Response respuesta = servicio.addTipo(tipoDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarTipo
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarTipo(){

        TipoServicio servicio = new TipoServicio();

        try {
            TipoDto tipoDto = new TipoDto();

            tipoDto.setNombre("");
            tipoDto.setDescripcion("");
            tipoDto.setEstatus("Activo");
            // Estar pendiente con los ID registrados en la BD
            Response respuesta = servicio.updateTipo(1, tipoDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarTipo
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarTipo(){

        TipoServicio servicio = new TipoServicio();

        try {
            // Estar pendiente con los ID registrados en la BD
            Response respuesta = servicio.eliminarTipo(1);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
