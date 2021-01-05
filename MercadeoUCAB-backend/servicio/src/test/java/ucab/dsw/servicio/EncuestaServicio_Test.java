package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.Response.EncuestaResponse;
import ucab.dsw.Response.RespuestaPreguntaResponse;

import java.util.List;


public class EncuestaServicio_Test {

    @Test
    void obtenerRespuestaEncuesta_Test() {
        try {
            EncuestaServicio encuestaServicio = new EncuestaServicio();
            List<RespuestaPreguntaResponse> respuestasPreguntas = encuestaServicio.obtenerRespuestaEncuesta(1);
            Assertions.assertNotNull(respuestasPreguntas);
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
    @Test
    void obtenerPreguntaEncuesta_Test() {
        try {
            EncuestaServicio encuestaServicio = new EncuestaServicio();
            List<EncuestaResponse> respuestasPreguntas = encuestaServicio.obtenerPreguntaEncuesta(1);
            Assertions.assertNotNull(respuestasPreguntas);
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
