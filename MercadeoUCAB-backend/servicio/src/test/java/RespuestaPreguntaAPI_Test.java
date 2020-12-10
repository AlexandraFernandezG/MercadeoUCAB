import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.*;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.RespuestaPreguntaDto;
import ucab.dsw.entidades.RespuestaPregunta;
import java.util.List;

public class RespuestaPreguntaAPI_Test {

    //Esta prueba permite obtener una lista de Respuestas solamente activas.
    @Test
    public void pruebaListarActivos(){

        ucab.dsw.servicio.RespuestaPreguntaAPI servicio = new ucab.dsw.servicio.RespuestaPreguntaAPI();
        List<RespuestaPregunta> listaRespuesta = servicio.listarRespuestas();
        List<RespuestaPregunta> listaRespuestaActivas = servicio.respuestasActivas();
        Assert.assertThat(listaRespuesta, IsNot.not(IsEqual.equalTo(listaRespuestaActivas)));
    }

    // Esta prueba permite insertar una Respuesta a la BD
    @Test
    public void pruebaInsertarRespuesta() throws Exception{

        ucab.dsw.servicio.RespuestaPreguntaAPI servicio = new ucab.dsw.servicio.RespuestaPreguntaAPI();
        RespuestaPreguntaDto respuestaPreguntaDto = new RespuestaPreguntaDto();

        respuestaPreguntaDto.setNombre("No muy buena la verdad");
        respuestaPreguntaDto.setEstatus("Inactivo");
        // Recuerden que deben ver los id de los registros en la BD
        PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto(1L);
        respuestaPreguntaDto.setPreguntaEncuestaDto(preguntaEncuestaDto);
        RespuestaPregunta resultado = servicio.addRespuestaPregunta(respuestaPreguntaDto);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    // Esta prueba permite modificar una Respuesta
    @Test
    public void pruebaModificarRespuesta(){

        ucab.dsw.servicio.RespuestaPreguntaAPI servicio = new ucab.dsw.servicio.RespuestaPreguntaAPI();
        RespuestaPreguntaDto respuestaPreguntaDto = new RespuestaPreguntaDto();
        respuestaPreguntaDto.setNombre("No muy buena la verdad");
        respuestaPreguntaDto.setEstatus("Activo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarRespuestaPregunta(1L, respuestaPreguntaDto);
    }

    // Esta prueba permite eliminar una Respuesta
    @Test
    public void pruebaEliminarRespuesta(){

        ucab.dsw.servicio.RespuestaPreguntaAPI servicio = new ucab.dsw.servicio.RespuestaPreguntaAPI();
        servicio.eliminarRespuestaPregunta(1L);
    }
}
