
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;

public class LugarAPI_Test {

    //Listar todos los lugares
    @Test
    public void pruebaListarLugares(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {
            Assertions.assertTrue(servicio.listarLugares().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un lugar
    @Test
    public void pruebaConsultarLugar(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();
        Lugar lugar_buscar = servicio.consultarLugar(1);

        try {
            Assertions.assertEquals(1, lugar_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar solamente los paises
    @Test
    public void pruebaListarPaises(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {
            Assertions.assertTrue(servicio.listarPaises().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Listar solamente los estados
    @Test
    public void pruebaListarEstados(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {
            Assertions.assertTrue(servicio.listarEstados().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Listar solamente las urbanizaciones
    @Test
    public void pruebaListarUrbanizaciones(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {
            Assertions.assertTrue(servicio.listarUrbanizaciones().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Jerarquia Pais/Estado
    @Test
    public void pruebaListarEstadosPorPais(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();
        long idPais = 1;

        try {
            Assertions.assertTrue(servicio.jerarquiaLugar(idPais).size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Para insertar un lugar
    @Test
    public void pruebaInsertarLugar(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {

            LugarDto lugarDto = new LugarDto();
            lugarDto.setNombre("Caracas");
            lugarDto.setTipo("Estado");
            lugarDto.setCategoriaSocioEconomica("Baja");
            lugarDto.setEstatus("Activo");
            //Estar pendientes con los ids de la base de datos
            LugarDto lugar = new LugarDto(1);
            lugarDto.setLugar(lugar);
            LugarDto resultado = servicio.addLugar(lugarDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Para modificar un lugar
    @Test
    public void pruebaModificarLugar(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {

            LugarDto lugarDto = new LugarDto();
            lugarDto.setNombre("Italia");
            lugarDto.setTipo("Pais");
            lugarDto.setCategoriaSocioEconomica("Alta");
            lugarDto.setEstatus("Activo");
            //Estar pendientes con los ids de la base de datos
            LugarDto lugar = new LugarDto(1);
            servicio.updateLugar(1, lugarDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Eliminar un lugar
    @Test
    public void pruebaEliminarLugar(){

        ucab.dsw.servicio.LugarAPI servicio = new ucab.dsw.servicio.LugarAPI();

        try {

            //Estar pendientes con los ids de la base de datos
            servicio.deleteLugar(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
