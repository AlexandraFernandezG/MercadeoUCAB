import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.servicio.OcupacionServicio;

public class OcupacionServicio_Test {

    //Listar todos las ocupaciones
    @Test
    public void pruebaListarOcupaciones(){

        OcupacionServicio servicio = new OcupacionServicio();

        try {
            Assertions.assertTrue(servicio.listarOcupacion().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una ocupacion
    @Test
    public void pruebaConsultarOcupacion(){

        OcupacionServicio servicio = new OcupacionServicio();
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

        OcupacionServicio servicio = new OcupacionServicio();

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

        OcupacionServicio servicio = new OcupacionServicio();

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

        OcupacionServicio servicio = new OcupacionServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarOcupacion(2);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
