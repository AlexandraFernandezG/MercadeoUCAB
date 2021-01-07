import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.servicio.PresentacionServicio;

public class PresentacionServicio_Test {

    //Listar todas las presentaciones
    @Test
    public void pruebaListarPresentaciones(){

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            Assertions.assertTrue(servicio.listarPresentaciones().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar una presentacion
    @Test
    public void pruebaConsultarPresentacion(){

        PresentacionServicio servicio = new PresentacionServicio();
        Presentacion presentacion_buscar = servicio.consultarPresentacion(1);

        try {
            Assertions.assertEquals(1, presentacion_buscar.get_id());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    // Listar presentaciones activas
    @Test
    public void pruebaListarPresentacionesActivas(){

        try {
            Assertions.assertNotNull(new PresentacionServicio().presentacionesActivas());

        } catch (Exception e) {

            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Prueba insertar una presentacion
    @Test
    public void pruebaInsertarPresentacion() throws Exception {

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            PresentacionDto presentacionDto = new PresentacionDto();

            presentacionDto.setNombre("");
            presentacionDto.setDescripcion("");
            presentacionDto.setEstatus("Activo");
            PresentacionDto resultado = servicio.addPresentacion(presentacionDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Prueba para modificar presentacion
    @Test
    public void pruebaModificarPresentacion(){

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            PresentacionDto presentacionDto = new PresentacionDto();

            presentacionDto.setNombre("");
            presentacionDto.setDescripcion("");
            presentacionDto.setEstatus("Activo");
            // Estar mosca con los id de la bd
            servicio.updatePresentacion(1, presentacionDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Prueba para eliminar presentacion
    @Test
    public void pruebaEliminarPresentacion(){

        PresentacionServicio servicio = new PresentacionServicio();

        try {
            // Estar mosca con los id de la bd
            servicio.eliminarPresentacion(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

}
