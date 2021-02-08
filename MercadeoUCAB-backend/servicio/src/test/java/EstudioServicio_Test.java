import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.servicio.EstudioServicio;

import javax.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstudioServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEstudios
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarEstudios(){

        EstudioServicio servicio = new EstudioServicio();
        Response respuesta = servicio.listarEstudios();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarEstudio
     * @author Gregg Spinetti
     */
    @Test
    public void pruebaConsultarEstudio(){

        EstudioServicio servicio = new EstudioServicio();
        Response estudio_buscar = servicio.consultarEstudio(1);

        try {
            Assertions.assertNotNull(estudio_buscar);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarEstudio
     * @author Gregg Spinetti
     */
    @Test
    public void pruebaListarEstudiosActivos(){

        try {
            Assertions.assertNotNull(new EstudioServicio().estudiosActivos());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarEncuestados
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarEncuestados(){

        EstudioServicio servicio = new EstudioServicio();
        Response respuesta = servicio.listarEncuestadosSolicitud(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarEstudio
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarEstudio() throws Exception {

        EstudioServicio servicio = new EstudioServicio();

        try {
            EstudioDto estudioDto = new EstudioDto();

            estudioDto.setNombre("Perros calientes raros parte 2");
            estudioDto.setTipoInstrumento("Encuesta");
            estudioDto.setObservaciones("Buenisimo");
            String date1 = "2020-12-01";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date1);
            estudioDto.setFechaInicio(myDate);
            String date2 = "2021-01-21";
            DateFormat forma2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = forma2.parse(date2);
            estudioDto.setFechaFin(myDate2);
            estudioDto.setEstado("En proceso");
            estudioDto.setEstatus("Activo");
            // Revisar los registros de sus base de datos
            SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto(1);
            estudioDto.setSolicitudEstudioDto(solicitudEstudioDto);
            UsuarioDto usuarioDto = new UsuarioDto(4);
            estudioDto.setUsuarioDto(usuarioDto);
            Response resultado = servicio.addEstudios(estudioDto);
            Assert.assertNotNull(resultado);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarEstudio
     * @author Gregg Spinetti
     */
    @Test
    public void pruebaModificarEstudio() throws ParseException {

        EstudioServicio servicio = new EstudioServicio();

        try {
            EstudioDto estudioDto = new EstudioDto();
            estudioDto.setNombre("Perros calientes raros parte 2");
            estudioDto.setTipoInstrumento("Encuesta");
            estudioDto.setObservaciones("Buenisimo");
            String date1 = "2020-12-01";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date1);
            estudioDto.setFechaInicio(myDate);
            String date2 = "2021-01-21";
            DateFormat forma2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = forma2.parse(date2);
            estudioDto.setFechaFin(myDate2);
            estudioDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            servicio.modificarEstudio(1, estudioDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarEstudio
     * @author Gregg Spinetti
     */
    @Test
    public void pruebaEliminarEstudio(){

        EstudioServicio servicio = new EstudioServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarEstudio(2);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
