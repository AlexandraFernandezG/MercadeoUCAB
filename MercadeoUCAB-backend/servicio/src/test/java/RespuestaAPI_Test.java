import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.dtos.RespuestaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Respuesta;
import ucab.dsw.entidades.Tipo;

public class RespuestaAPI_Test {

    //Listar todas las respuestas
    @Test
    public void pruebaListarRespuestas(){

        ucab.dsw.servicio.RespuestaAPI servicio = new ucab.dsw.servicio.RespuestaAPI();

        try {
            Assertions.assertTrue(servicio.listarRespuestas().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una respuesta
    @Test
    public void pruebaConsultarRespuesta(){

        ucab.dsw.servicio.RespuestaAPI servicio = new ucab.dsw.servicio.RespuestaAPI();
        Respuesta respuesta_buscar = servicio.consultarRespuesta(1);

        try {
            Assertions.assertEquals(1, respuesta_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar respuestas activas
    @Test
    public void pruebaListarRespuestasActivas(){

        try {
            Assertions.assertNotNull(new ucab.dsw.servicio.RespuestaAPI().respuestasActivas());

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Prueba insertar una respuesta
    @Test
    public void pruebaInsertarRespuesta() throws Exception {

        ucab.dsw.servicio.RespuestaAPI servicio = new ucab.dsw.servicio.RespuestaAPI();

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
            RespuestaDto resultado = servicio.addRespuesta(respuestaDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Prueba actualizar estatus
    @Test
    public void pruebaModificarRespuesta(){

        ucab.dsw.servicio.RespuestaAPI servicio = new ucab.dsw.servicio.RespuestaAPI();

        try {
            RespuestaDto respuestaDto = new RespuestaDto();

            respuestaDto.setRespuestaAbierta("");
            respuestaDto.setEscala("");
            respuestaDto.setVerdaderoFalso("");
            respuestaDto.setRespuestaSimple("");
            respuestaDto.setRespuestaMultiple("");
            respuestaDto.setEstatus("Activo");
            //Estar mosca con los ID de la BD
            servicio.updateRespuesta(1, respuestaDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Prueba eliminar una respuesta
    @Test
    public void pruebaEliminarRespuesta(){

        ucab.dsw.servicio.RespuestaAPI servicio = new ucab.dsw.servicio.RespuestaAPI();

        try {
            //Estar mosca con los ID de la BD
            servicio.eliminarRespuesta(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
