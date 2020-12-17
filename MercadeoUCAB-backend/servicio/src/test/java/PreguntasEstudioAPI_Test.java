import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;

public class PreguntasEstudioAPI_Test {

    //Listar preguntas de un estudio (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarPreguntasEstudio(){

        ucab.dsw.servicio.PreguntasEstudioAPI servicio = new ucab.dsw.servicio.PreguntasEstudioAPI();

        try {
            Assertions.assertTrue(servicio.listarPreguntasEstudio(1).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    // Esta prueba permite insertar la relacion Pregunta estudios
    @Test
    public void pruebaInsertarPreguntasEstudio() throws Exception {

        ucab.dsw.servicio.PreguntasEstudioAPI servicio = new ucab.dsw.servicio.PreguntasEstudioAPI();
        PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

        preguntaEstudioDto.setEstatus("Activo");
        //Estar mosca con los Id de la base de datos
        EstudioDto estudioDto = new EstudioDto(1);
        PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto(1);
        preguntaEstudioDto.setEstudioDto(estudioDto);
        preguntaEstudioDto.setPreguntaEncuestaDto(preguntaEncuestaDto);
        PreguntaEstudioDto resultado = servicio.addPreguntaEstudio(preguntaEstudioDto);
        Assert.assertNotEquals( resultado.getId(), 0 );

    }

    // Esta prueba permite actualizar el estatus de Pregunta estudio
    @Test
    public void pruebaModificarEstatusPreguntaEstudio(){

        ucab.dsw.servicio.PreguntasEstudioAPI servicio = new ucab.dsw.servicio.PreguntasEstudioAPI();
        PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

        preguntaEstudioDto.setEstatus("Inactivo");
        // Estas mosca con los ID de la base de datos
        servicio.modificarEstatusPreguntaEstudio(1L, preguntaEstudioDto);
    }

    // Esta prueba permite eliminar una preguntaEstudio
    @Test
    public void pruebaEliminarPreguntaEstudio(){

        ucab.dsw.servicio.PreguntasEstudioAPI servicio = new ucab.dsw.servicio.PreguntasEstudioAPI();
        // Estas mosca con los ID de la base de datos
        servicio.eliminarEstudioPreguntaEstudio(1);

    }

}
