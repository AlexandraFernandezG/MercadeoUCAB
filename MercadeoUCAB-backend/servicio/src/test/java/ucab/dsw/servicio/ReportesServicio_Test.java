package ucab.dsw.servicio;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import javax.ws.rs.core.Response;

public class ReportesServicio_Test {


    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarRespuestasAbiertas
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testListarRespuestasAbiertas(){
        long id = 1; // ID del estudio para poder obtener las respuestas

        ReportesServicio servicio = new ReportesServicio();
        Response respuesta = servicio.listarRespuestasAbiertas(id);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método CantidadesPreguntas
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testCantidadesPreguntas(){

        long id = 3; // ID del estudio para poder la estadistica

        ReportesServicio servicio = new ReportesServicio();
        Response respuesta = servicio.listarCantidadesPreguntas(id);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarPorcentajesGenero
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testListarPorcentajesGenero(){
        long id = 2; // ID del estudio para poder la estadistica

        ReportesServicio servicio = new ReportesServicio();
        Response respuesta = servicio.listarPorcentajesGenero(id);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }
}
