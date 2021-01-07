import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Tipo;
import ucab.dsw.servicio.TipoServicio;

public class TipoServicio_Test {

    //Listar todos los tipos
    @Test
    public void pruebaListarTipos(){

        TipoServicio servicio = new TipoServicio();

        try {
            Assertions.assertTrue(servicio.listarTipos().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un Tipo
    @Test
    public void pruebaConsultarTipo(){

        TipoServicio servicio = new TipoServicio();
        Tipo tipo_buscar = servicio.consultarTipo(1);

        try {
            Assertions.assertEquals(1, tipo_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar tipos activos
    @Test
    public void pruebaListarTiposActivos(){

        try {
            Assertions.assertNotNull(new TipoServicio().tiposActivos());

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Agregar un tipo
    @Test
    public void pruebaInsertarTipo() throws Exception {

        TipoServicio servicio = new TipoServicio();

        try {
            TipoDto tipoDto = new TipoDto();

            tipoDto.setNombre("");
            tipoDto.setDescripcion("");
            tipoDto.setEstatus("Activo");
            TipoDto resultado = servicio.addTipo(tipoDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Modificar tipo
    @Test
    public void pruebaModificarTipo(){

        TipoServicio servicio = new TipoServicio();

        try {
            TipoDto tipoDto = new TipoDto();

            tipoDto.setNombre("");
            tipoDto.setDescripcion("");
            tipoDto.setEstatus("Activo");
            // Estar pendiente con los ID registrados en la BD
            servicio.updateTipo(1, tipoDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Eliminar tipo
    @Test
    public void pruebaEliminarTipo(){

        TipoServicio servicio = new TipoServicio();

        try {
            // Estar pendiente con los ID registrados en la BD
            servicio.eliminarTipo(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
