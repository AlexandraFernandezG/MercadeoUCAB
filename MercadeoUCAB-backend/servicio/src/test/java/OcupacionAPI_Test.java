import org.junit.*;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;

public class OcupacionAPI_Test {

    //Prueba insertar una ocupacion
    @Test
    public void pruebaInsertarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();
        OcupacionDto ocupacionDto = new OcupacionDto();

        ocupacionDto.set_nombre("Medico");
        ocupacionDto.set_estatus("Activo");
        Ocupacion resultado = servicio.addOcupacion(ocupacionDto);
        Assert.assertNotEquals( resultado.get_id(), 0 );

    }

    //Prueba modificar una ocupacion
    @Test
    public void pruebaModificarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();
        OcupacionDto ocupacionDto = new OcupacionDto();

        ocupacionDto.set_nombre("");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.updateOcupacion(1L, ocupacionDto);

    }

    //Prueba eliminar una ocupacion
    @Test
    public void pruebaEliminarOcupacion(){

        ucab.dsw.servicio.OcupacionAPI servicio = new ucab.dsw.servicio.OcupacionAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarOcupacion(2L);

    }
}
