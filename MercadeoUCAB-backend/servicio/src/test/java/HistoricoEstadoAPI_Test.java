
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.HistoricoEstadoDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.HistoricoEstado;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricoEstadoAPI_Test {

    //Listar todos los historicos
    @Test
    public void pruebaListarHistoricos(){

        ucab.dsw.servicio.HistoricoEstadoAPI servicio = new ucab.dsw.servicio.HistoricoEstadoAPI();

        try {
            Assertions.assertTrue(servicio.listarHistoricos().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un historico
    @Test
    public void pruebaConsultarHistorico(){

        ucab.dsw.servicio.HistoricoEstadoAPI servicio = new ucab.dsw.servicio.HistoricoEstadoAPI();
        HistoricoEstado historicoEstado = servicio.consultarHistorico(1);

        try {
            Assertions.assertEquals(1, historicoEstado.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar todos los historicos activos
    @Test
    public void pruebaListarHistoricosActivos(){

        ucab.dsw.servicio.HistoricoEstadoAPI servicio = new ucab.dsw.servicio.HistoricoEstadoAPI();

        try {
            Assertions.assertTrue(servicio.historicosActivos().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Para insertar un historico
    @Test
    public void pruebaInsertarHistorico(){

        ucab.dsw.servicio.HistoricoEstadoAPI servicio = new ucab.dsw.servicio.HistoricoEstadoAPI();

        try {

            HistoricoEstadoDto historicoEstadoDto = new HistoricoEstadoDto();

            String date1 = "2020-12-01";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date1);
            historicoEstadoDto.setFechaInicio(myDate);
            String date2 = "2021-01-21";
            DateFormat forma2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = forma2.parse(date2);
            historicoEstadoDto.setFechaFin(myDate2);
            historicoEstadoDto.setEstatus("Activo");
            UsuarioDto usuarioDto = new UsuarioDto(1);
            historicoEstadoDto.setUsuarioDto(usuarioDto);
            HistoricoEstadoDto resultado = servicio.addHistoricoEstado(historicoEstadoDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba modificar estatus historico
    @Test
    public void pruebaModificarEstatusHistorico(){

        ucab.dsw.servicio.HistoricoEstadoAPI servicio = new ucab.dsw.servicio.HistoricoEstadoAPI();

        try {

            HistoricoEstadoDto historicoEstadoDto = new HistoricoEstadoDto();
            historicoEstadoDto.setEstatus("Activo");
            //Estar pendientes con los ids de la base de datos
            servicio.modificarEstatusHistorico(1, historicoEstadoDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba eliminar historico
    @Test
    public void pruebaEliminarHistorico(){

        ucab.dsw.servicio.HistoricoEstadoAPI servicio = new ucab.dsw.servicio.HistoricoEstadoAPI();

        try {

            //Estar pendientes con los ids de la base de datos
            servicio.eliminarHistoricoEstado(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

}
