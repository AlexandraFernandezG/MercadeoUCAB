import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.servicio.RespuestaServicio;

import javax.ws.rs.core.Response;

public class RespuestaServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarRespuestas
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarRespuestas(){

        RespuestaServicio servicio = new RespuestaServicio();
        Response respuesta = servicio.listarRespuestas();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarRespuesta
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarRespuesta(){

        RespuestaServicio servicio = new RespuestaServicio();
        Response respuesta = servicio.consultarRespuesta(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarRespuestasActivas
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarRespuestasActivas(){

        RespuestaServicio servicio = new RespuestaServicio();
        Response respuesta = servicio.respuestasActivas();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarRespuesta
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarRespuesta() {

        RespuestaServicio servicio = new RespuestaServicio();

        try {
            RespuestaDto respuestaDto = new RespuestaDto();

            respuestaDto.setRespuestaAbierta("");
            respuestaDto.setEscala("");
            respuestaDto.setVerdaderoFalso("");
            respuestaDto.setRespuestaSimple("");
            respuestaDto.setRespuestaMultiple("");
            respuestaDto.setEstatus("Activo");
            //Estar mosca con los ID de la BD
            PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto(1);
            UsuarioDto usuarioDto = new UsuarioDto(1);
            respuestaDto.setPreguntaEstudioDto(preguntaEstudioDto);
            respuestaDto.setUsuarioDto(usuarioDto);
            Response respuesta = servicio.addRespuesta(respuestaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarRespuesta
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarRespuesta(){

        RespuestaServicio servicio = new RespuestaServicio();

        try {
            RespuestaDto respuestaDto = new RespuestaDto();

            respuestaDto.setRespuestaAbierta("");
            respuestaDto.setEscala("");
            respuestaDto.setVerdaderoFalso("");
            respuestaDto.setRespuestaSimple("");
            respuestaDto.setRespuestaMultiple("");
            respuestaDto.setEstatus("Activo");
            //Estar mosca con los ID de la BD
            Response respuesta = servicio.updateRespuesta(1, respuestaDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarRespuesta
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarRespuesta(){

        RespuestaServicio servicio = new RespuestaServicio();

        try {

            //Estar mosca con los ID de la BD
            Response respuesta = servicio.eliminarRespuesta(1);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
