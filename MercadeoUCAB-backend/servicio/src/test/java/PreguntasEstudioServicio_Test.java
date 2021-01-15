import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.servicio.PreguntasEstudioServicio;

import javax.ws.rs.core.Response;

public class PreguntasEstudioServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarPreguntasEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarPreguntasEstudio(){

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();
        Response respuesta = servicio.listarPreguntasEstudio(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarPreguntasEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarPreguntasEstudio()  {

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {
            PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

            preguntaEstudioDto.setEstatus("Activo");
            //Estar mosca con los Id de la base de datos
            EstudioDto estudioDto = new EstudioDto(1);
            PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto(1);
            preguntaEstudioDto.setEstudioDto(estudioDto);
            preguntaEstudioDto.setPreguntaEncuestaDto(preguntaEncuestaDto);
            Response respuesta = servicio.addPreguntaEstudio(preguntaEstudioDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarEstatusPreguntaEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarEstatusPreguntaEstudio(){

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {

            PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

            preguntaEstudioDto.setEstatus("Inactivo");
            // Estas mosca con los ID de la base de datos
            Response respuesta = servicio.modificarEstatusPreguntaEstudio(1, preguntaEstudioDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarPreguntaEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarPreguntaEstudio(){

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {
            // Estas mosca con los ID de la base de datos
            Response respuesta = servicio.eliminarEstudioPreguntaEstudio(1);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
