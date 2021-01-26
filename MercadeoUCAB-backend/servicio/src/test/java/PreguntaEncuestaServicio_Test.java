import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.servicio.PreguntaEncuestaServicio;

import javax.ws.rs.core.Response;

public class PreguntaEncuestaServicio_Test {

    //Listar todos las preguntas
    @Test
    public void pruebaListarPreguntaEncuesta(){

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();

        try {
            Assertions.assertNotNull(servicio.listarPreguntas());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una pregunta
    @Test
    public void pruebaConsultarPreguntaEncuesta(){

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();
        Response preguntaEncuesta_buscar = servicio.encontrarPreguntaEncuesta(1);

        try {
            Assertions.assertNotNull( preguntaEncuesta_buscar);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar Preguntas activas
    @Test
    public void pruebaListarPreguntasEncuestaActivas(){

        try {
            Assertions.assertNotNull(new PreguntaEncuestaServicio().preguntasActivas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarPregunta
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarPregunta() throws Exception{

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();

        try {
            PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();

            preguntaEncuestaDto.setDescripcion("Que te parece los perros calientes de arandanos?");
            preguntaEncuestaDto.setTipoPregunta("Desarrollo");
            preguntaEncuestaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            UsuarioDto usuarioDto = new UsuarioDto(1);
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);
            preguntaEncuestaDto.setUsuarioDto(usuarioDto);
            preguntaEncuestaDto.setSubcategoriaDto(subcategoriaDto);
            Response respuesta = servicio.addPreguntaEncuesta(preguntaEncuestaDto);
            Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarPreguntaEstudio
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarPreguntaEstudio() {

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();

        try {
            PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();

            preguntaEncuestaDto.setDescripcion("Que te parece los perros calientes de frutillas?");
            preguntaEncuestaDto.setTipoPregunta("Abierta");
            preguntaEncuestaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            UsuarioDto usuarioDto = new UsuarioDto(3);
            SubcategoriaDto subcategoriaDto = new SubcategoriaDto(1);
            preguntaEncuestaDto.setUsuarioDto(usuarioDto);
            preguntaEncuestaDto.setSubcategoriaDto(subcategoriaDto);
            Response respuesta = servicio.addPreguntaEncuestaEstudio(1, preguntaEncuestaDto);
            Assert.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite actualizar el estatus de Pregunta
    @Test
    public void pruebaModificarEstatusPregunta(){

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();

        try {
            PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();
            preguntaEncuestaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.modificarPreguntaEncuesta(1, preguntaEncuestaDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite modificar una pregunta
    @Test
    public void pruebaModificarPregunta(){

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();

        try {
            PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();
            preguntaEncuestaDto.setDescripcion("Que te parece los perros calientes de arandanos?");
            preguntaEncuestaDto.setTipoPregunta("Desarrollo");
            preguntaEncuestaDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.modificarPreguntaEncuesta(1, preguntaEncuestaDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Esta prueba permite eliminar una pregunta
    @Test
    public void pruebaEliminarPregunta(){

        PreguntaEncuestaServicio servicio = new PreguntaEncuestaServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarPreguntaEncuesta(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
