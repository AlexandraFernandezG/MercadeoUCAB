package ucab.dsw.servicio;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

class SugerenciasServicioTest {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarPreguntasEstudioRecomendadas
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testListarPreguntasEstudioRecomendadas() {
        long id = 2; // ID del estudio para poder recomendar las preguntas (Verificar).

            SugerenciasServicio servicio = new SugerenciasServicio();
            Response respuesta = servicio.listarPreguntasEstudioRecomendadas(id);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEstudiosRecomendados
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testListarEstudiosRecomendados() {
        long id = 8; // ID de la solicitud para recomendar estudios (Verificar).

        SugerenciasServicio servicio = new SugerenciasServicio();
        Response respuesta = servicio.listarEstudiosRecomendados(id);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEstudiosEncuestado
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testListarEstudiosEncuestado() {

        long id = 1; // ID del encuestado para poder recomendar estudios.

        SugerenciasServicio servicio = new SugerenciasServicio();
        Response respuesta = servicio.listarEstudiosEncuestado(id);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEstudiosCliente
     * @author Emanuel Di Cristofaro
     */
    @Test
    void testListarEstudiosCliente() {

        long id = 1; // ID de un usuario activo con un rol igual a "Cliente".

        SugerenciasServicio servicio = new SugerenciasServicio();
        Response respuesta = servicio.listarEstudiosCliente(id);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }
}