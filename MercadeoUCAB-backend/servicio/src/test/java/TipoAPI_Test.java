import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Tipo;

public class TipoAPI_Test {

    //Listar todos los tipos (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarTipos(){

        ucab.dsw.servicio.TipoAPI servicio = new ucab.dsw.servicio.TipoAPI();

        try {
            Assertions.assertTrue(servicio.listarTipos().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un Tipo (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaConsultarTipo(){

        ucab.dsw.servicio.TipoAPI servicio = new ucab.dsw.servicio.TipoAPI();
        Tipo tipo_buscar = servicio.consultarTipo(1);

        try {
            Assertions.assertEquals(1, tipo_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar tipos activos (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarTiposActivos(){

        try {
            Assertions.assertNotNull(new ucab.dsw.servicio.TipoAPI().tiposActivos());

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Agregar un tipo
    @Test
    public void pruebaInsertarTipo() throws Exception {

        ucab.dsw.servicio.TipoAPI servicio = new ucab.dsw.servicio.TipoAPI();

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

        ucab.dsw.servicio.TipoAPI servicio = new ucab.dsw.servicio.TipoAPI();

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

        ucab.dsw.servicio.TipoAPI servicio = new ucab.dsw.servicio.TipoAPI();

        try {
            // Estar pendiente con los ID registrados en la BD
            servicio.eliminarTipo(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
