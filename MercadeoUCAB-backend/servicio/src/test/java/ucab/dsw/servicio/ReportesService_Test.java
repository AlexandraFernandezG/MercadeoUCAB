package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.Response.ReporteMFResponse;
import ucab.dsw.Response.ReporteVFResponse;
import ucab.dsw.Response.RespuestasAbiertasResponse;

import java.util.List;

public class ReportesService_Test {


    /**
     * Prueba que permite obtener las respuestas de las preguntas abiertas
     * en base a un estudio.
     * */
    @Test
    void testListarRespuestasAbiertas(){
        long id = 1; // ID del estudio para poder obtener las respuestas

        try {
            //Búsqueda de preguntas/respuestas abiertas partiendo de un estudio
            ReportesService servicio = new ReportesService();
            List<RespuestasAbiertasResponse> listaRespuestasAbiertas = servicio.listarRespuestasAbiertas(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaRespuestasAbiertas.isEmpty());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba que permite obtener los porcentajes de respuestas verdaderos y falsos
     * de un estudio.
     * */
    @Test
    void testPorcentajeVeraderoFalso(){
        long id = 2; // ID del estudio para poder la estadistica

        try {
            //Búsqueda de preguntas/respuestas abiertas partiendo de un estudio
            ReportesService servicio = new ReportesService();
            List<ReporteVFResponse> listaResultadoPorcentual = servicio.porcentajeVeraderoFalso(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaResultadoPorcentual.isEmpty());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba que permite obtener los porcentajes de hombres y mujeres que respondieron
     * en un estudio.
     * */
    @Test
    void testListarPorcentajesGenero(){
        long id = 2; // ID del estudio para poder la estadistica

        try {
            //Búsqueda de preguntas/respuestas abiertas partiendo de un estudio
            ReportesService servicio = new ReportesService();
            List<ReporteMFResponse> listaResultadoPorcentual = servicio.listarPorcentajesGenero(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaResultadoPorcentual.isEmpty());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
