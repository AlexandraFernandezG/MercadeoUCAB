
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.HistoricoEstadoDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.HistoricoEstado;
import ucab.dsw.servicio.HistoricoEstadoServicio;

import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricoEstadoServicio_Test {

    //Listar todos los historicos
    @Test
    public void pruebaListarHistoricos(){

        HistoricoEstadoServicio servicio = new HistoricoEstadoServicio();

        try {
            Assertions.assertNotNull(servicio.listarHistoricos());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un historico
    @Test
    public void pruebaConsultarHistorico(){

        HistoricoEstadoServicio servicio = new HistoricoEstadoServicio();
        Response historicoEstado = servicio.consultarHistorico(1);

        try {
            Assertions.assertNotNull(historicoEstado);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar todos los historicos activos
    @Test
    public void pruebaListarHistoricosActivos(){

        HistoricoEstadoServicio servicio = new HistoricoEstadoServicio();

        try {
            Assertions.assertNotNull(servicio.historicosActivos());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Para insertar un historico
    @Test
    public void pruebaInsertarHistorico(){

        HistoricoEstadoServicio servicio = new HistoricoEstadoServicio();

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
            Response resultado = servicio.addHistoricoEstado(historicoEstadoDto);
            Assert.assertNotNull(resultado);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba modificar estatus historico
    @Test
    public void pruebaModificarEstatusHistorico(){

        HistoricoEstadoServicio servicio = new HistoricoEstadoServicio();

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

        HistoricoEstadoServicio servicio = new HistoricoEstadoServicio();

        try {

            //Estar pendientes con los ids de la base de datos
            servicio.eliminarHistoricoEstado(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

}
