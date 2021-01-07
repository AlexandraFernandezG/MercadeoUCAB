import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.servicio.PreguntasEstudioServicio;

public class PreguntasEstudioServicio_Test {

    //Listar preguntas de un estudio
    @Test
    public void pruebaListarPreguntasEstudio(){

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {
            Assertions.assertTrue(servicio.listarPreguntasEstudio(1).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    // Esta prueba permite insertar la relacion Pregunta estudios
    @Test
    public void pruebaInsertarPreguntasEstudio() throws Exception {

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {
            PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

            preguntaEstudioDto.setEstatus("Activo");
            //Estar mosca con los Id de la base de datos
            EstudioDto estudioDto = new EstudioDto(1);
            PreguntaEncuestaDto preguntaEncuestaDto = new PreguntaEncuestaDto(1);
            preguntaEstudioDto.setEstudioDto(estudioDto);
            preguntaEstudioDto.setPreguntaEncuestaDto(preguntaEncuestaDto);
            PreguntaEstudioDto resultado = servicio.addPreguntaEstudio(preguntaEstudioDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Esta prueba permite actualizar el estatus de Pregunta estudio
    @Test
    public void pruebaModificarEstatusPreguntaEstudio(){

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {
            PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

            preguntaEstudioDto.setEstatus("Inactivo");
            // Estas mosca con los ID de la base de datos
            servicio.modificarEstatusPreguntaEstudio(1, preguntaEstudioDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite eliminar una preguntaEstudio
    @Test
    public void pruebaEliminarPreguntaEstudio(){

        PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

        try {
            // Estas mosca con los ID de la base de datos
            servicio.eliminarEstudioPreguntaEstudio(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
