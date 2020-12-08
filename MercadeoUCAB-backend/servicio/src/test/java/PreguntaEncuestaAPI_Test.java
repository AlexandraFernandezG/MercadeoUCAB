import org.junit.*;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.PreguntaEncuesta;

public class PreguntaEncuestaAPI_Test {

    // Esta prueba permite insertar una Pregunta a la BD
    @Test
    public void pruebaInsertarPregunta() throws Exception{

        ucab.dsw.servicio.PreguntaEncuestaAPI servicio = new ucab.dsw.servicio.PreguntaEncuestaAPI();
        PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();

        preguntaEncuestaDto.set_descripcion("Que te parece los perros calientes de arandanos?");
        preguntaEncuestaDto.set_tipoPregunta("Desarrollo");
        preguntaEncuestaDto.set_estatus("Activo");
        // Recuerden que deben ver los id de los registros en la BD
        UsuarioDto usuarioDto = new UsuarioDto(1L);
        SubcategoriaDto subcategoriaDto = new SubcategoriaDto(5L);
        preguntaEncuestaDto.set_usuarioDto(usuarioDto);
        preguntaEncuestaDto.set_subcategoriaDto(subcategoriaDto);
        PreguntaEncuesta resultado = servicio.addPreguntaEncuesta(preguntaEncuestaDto);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    // Esta prueba permite modificar una subcategoria
    @Test
    public void pruebaModificarPregunta(){

        ucab.dsw.servicio.PreguntaEncuestaAPI servicio = new ucab.dsw.servicio.PreguntaEncuestaAPI();
        PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto();
        preguntaEncuestaDto.set_descripcion("Que te parece los perros calientes de arandanos?");
        preguntaEncuestaDto.set_tipoPregunta("Desarrollo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarPreguntaEncuesta(1L, preguntaEncuestaDto);

    }

    //Esta prueba permite eliminar una subcategoria
    @Test
    public void pruebaEliminarPregunta(){

        ucab.dsw.servicio.PreguntaEncuestaAPI servicio = new ucab.dsw.servicio.PreguntaEncuestaAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarPreguntaEncuesta(1L);
    }
}
