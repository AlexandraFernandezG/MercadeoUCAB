import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;

public class OcupacionAPI_Test {

    //Listar todos las ocupaciones
    @Test
    public void pruebaListarOcupaciones(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();

        try {
            Assertions.assertTrue(servicio.listarOcupacion().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una ocupacion
    @Test
    public void pruebaConsultarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();
        Ocupacion ocupacion_buscar = servicio.consultarOcupacion(1);

        try {
            Assertions.assertEquals(1, ocupacion_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Prueba insertar una ocupacion
    @Test
    public void pruebaInsertarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();

        try {
            OcupacionDto ocupacionDto = new OcupacionDto();

            ocupacionDto.setNombre("Medico");
            ocupacionDto.setEstatus("Activo");
            OcupacionDto resultado = servicio.addOcupacion(ocupacionDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Prueba modificar una ocupacion
    @Test
    public void pruebaModificarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();

        try {
            OcupacionDto ocupacionDto = new OcupacionDto();

            ocupacionDto.setNombre("");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.updateOcupacion(1, ocupacionDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Prueba eliminar una ocupacion
    @Test
    public void pruebaEliminarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarOcupacion(2);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
