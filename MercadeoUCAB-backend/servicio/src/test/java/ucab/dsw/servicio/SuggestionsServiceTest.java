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
     * */
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
     * */
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
     * */
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
     * */
    @Test
    void testListarEstudiosCliente_RolCliente() {
        long id = 1; // ID de un usuario activo con un rol igual a "Cliente".
        try {
            // Búsqueda de estudios recomendados para el usuario.
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosResponse> listaEstudiosRecomendados =
                    servicio.listarEstudiosCliente(id);

            // Verificación del resultado.
            Assertions.assertFalse(listaEstudiosRecomendados.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba que verifica el resultado esperado del método
     * listarEstudiosCliente, en caso que el ID enviado por
     * parámetros no corresponda a un usuario con rol de
     * cliente.
     * */
    @Test
    void testListarEstudiosCliente_RolNoCliente() {
        long id = 2; // ID de un usuario con rol diferente a "Cliente".
        try {
            // Búsqueda de estudios recomendados para el usuario.
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosResponse> listaEstudiosRecomendados =
                    servicio.listarEstudiosCliente(id);

            // Verificación del resultado.
            Assertions.assertTrue(listaEstudiosRecomendados.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba que verifica el resultado esperado del método
     * listarEstudiosCliente, en caso que el ID enviado por
     * parámetros no corresponda a un usuario con estatus
     * diferente a "Activo".
     * */
    @Test
    void testListarEstudiosCliente_ClienteNoActivo() {
        long id = 3; // ID de un usuario con estatus diferente a "Activo".
        try {
            // Búsqueda de estudios recomendados para el usuario.
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosResponse> listaEstudiosRecomendados =
                    servicio.listarEstudiosCliente(id);

            // Verificación del resultado.
            Assertions.assertTrue(listaEstudiosRecomendados.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba que verifica el resultado esperado del método
     * listarEstudiosCliente, en caso que no existe ningún
     * usuario con el ID enviado por parámetros.
     * */
    @Test
    void testListarEstudiosCliente_UsuarioNoExiste() {
        long id = 0; // ID de un usuario que no existe.
        try {
            // Búsqueda de estudios recomendados para el usuario.
            SuggestionsService servicio = new SuggestionsService();
            List<EstudiosResponse> listaEstudiosRecomendados =
                    servicio.listarEstudiosCliente(id);

            // Verificación del resultado.
            Assertions.assertTrue(listaEstudiosRecomendados.isEmpty());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}