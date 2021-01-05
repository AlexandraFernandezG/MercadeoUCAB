package ucab.dsw.servicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucab.dsw.Response.EstudiosEncuestadoResponse;
import ucab.dsw.Response.EstudiosResponse;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Usuario;

import java.util.List;

class SuggestionsServiceTest {

    /**
     * Prueba que verifica el resultado esperado del método listarPreguntasEstudioRecomendadas.
     */
    @Test
    void testListarPreguntasEstudioRecomendadas() {
        long id = 2; // ID del estudio para poder recomendar las preguntas (Verificar).

        try {
            //Búsqueda de preguntas recomendadas partiendo de un estudio
            SuggestionsService servicio = new SuggestionsService();
            List<PreguntasResponse> listaPreguntasRecomendadas = servicio.listarPreguntasEstudioRecomendadas(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaPreguntasRecomendadas.isEmpty());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba que verifica el resultado esperado del método listarEstudiosRecomendados.
     */
    @Test
    void testListarEstudiosRecomendados() {
        long id = 8; // ID de la solicitud para recomendar estudios (Verificar).

        try {
            //Búsqueda de estudios partiendo de una solicitud
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosResponse> listaEstudiosRecomendados = servicio.listarEstudiosRecomendados(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaEstudiosRecomendados.isEmpty());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba que verifica el resultado esperado del método listarEstudiosEncuestado.
     */
    @Test
    void testListarEstudiosEncuestado() {
        long id = 1; // ID del encuestado para poder recomendar estudios.

        try {
            //Búsqueda de estudios partiendo de la informacion de un encuestado
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosEncuestadoResponse> listaEstudiosRecomendados = servicio.listarEstudiosEncuestado(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaEstudiosRecomendados.isEmpty());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba que verifica el resultado esperado del método listasEstudiosCliente,
     * siendo el usuario un cliente activo.
     */
    @Test
    void testListarEstudiosCliente() {
        long id = 1; // ID de un usuario activo con un rol igual a "Cliente".
        try {
            // Búsqueda de estudios recomendados para el usuario.
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosResponse> listaEstudiosRecomendados =
                    servicio.listarEstudiosClientes(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaEstudiosRecomendados.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}