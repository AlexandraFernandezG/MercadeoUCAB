
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.LugarDto;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.servicio.LugarServicio;

import javax.ws.rs.core.Response;

public class LugarServicio_Test {

    //Listar todos los lugares
    @Test
    public void pruebaListarLugares(){

        LugarServicio servicio = new LugarServicio();

        try {
            Assertions.assertNotNull(servicio.listarLugares());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un lugar
    @Test
    public void pruebaConsultarLugar(){

        LugarServicio servicio = new LugarServicio();
        Response lugar_buscar = servicio.consultarLugar(1);

        try {
            Assertions.assertEquals(1, lugar_buscar);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar solamente los paises
    @Test
    public void pruebaListarPaises(){

        LugarServicio servicio = new LugarServicio();

        try {
            Assertions.assertNotNull(servicio.listarPaises());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Listar solamente los estados
    @Test
    public void pruebaListarEstados(){

        LugarServicio servicio = new LugarServicio();

        try {
            Assertions.assertNotNull(servicio.listarEstados());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Listar solamente las urbanizaciones
    @Test
    public void pruebaListarUrbanizaciones(){

        LugarServicio servicio = new LugarServicio();

        try {
            Assertions.assertNotNull(servicio.listarUrbanizaciones());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Jerarquia Pais/Estado
    @Test
    public void pruebaListarEstadosPorPais(){

        LugarServicio servicio = new LugarServicio();
        long idPais = 1;

        try {
            Assertions.assertNotNull(servicio.jerarquiaLugar(idPais));

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Para insertar un lugar
    @Test
    public void pruebaInsertarLugar(){

        LugarServicio servicio = new LugarServicio();

        try {

            LugarDto lugarDto = new LugarDto();
            lugarDto.setNombre("Caracas");
            lugarDto.setTipo("Estado");
            lugarDto.setCategoriaSocioEconomica("Baja");
            lugarDto.setEstatus("Activo");
            //Estar pendientes con los ids de la base de datos
            LugarDto lugar = new LugarDto(1);
            lugarDto.setLugar(lugar);
            Response resultado = servicio.addLugar(lugarDto);
            Assert.assertNotNull(resultado);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Para modificar un lugar
    @Test
    public void pruebaModificarLugar(){

        LugarServicio servicio = new LugarServicio();

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

        LugarServicio servicio = new LugarServicio();

        try {

            //Estar pendientes con los ids de la base de datos
            servicio.deleteLugar(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
