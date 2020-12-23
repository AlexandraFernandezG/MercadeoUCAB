import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.dtos.UsuarioDto;
import ucab.dsw.entidades.Estudio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstudioAPI_Test {

    //Listar todos los estudios (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarEstudios(){

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();

        try {
            Assertions.assertTrue(servicio.listarEstudios().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un Estudio (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaConsultarEstudio(){

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();
        Estudio estudio_buscar = servicio.consultarEstudio(1);

        try {
            Assertions.assertEquals(1, estudio_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Listar estudios activos (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarEstudiosActivos(){

        try {
            Assertions.assertNotNull(new ucab.dsw.servicio.EstudioAPI().estudiosActivos());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    // Esta prueba permite insertar un estudio a la BD
    @Test
    public void pruebaInsertarEstudio() throws Exception {

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();

        try {
            EstudioDto estudioDto = new EstudioDto();

            estudioDto.setNombre("Perros calientes raros parte 2");
            estudioDto.setTipoInstrumento("Encuesta");
            String date1 = "2020-12-01";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date1);
            estudioDto.setFechaInicio(myDate);
            String date2 = "2021-01-21";
            DateFormat forma2 = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate2 = forma2.parse(date2);
            estudioDto.setFechaFin(myDate2);
            estudioDto.setEstatus("Activo");
            // Revisar los registros de sus base de datos
            SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto(1);
            estudioDto.setSolicitudEstudioDto(solicitudEstudioDto);
            UsuarioDto usuarioDto = new UsuarioDto(6);
            estudioDto.setUsuarioDto(usuarioDto);
            EstudioDto resultado = servicio.addEstudios(estudioDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Esta prueba permite modificar un estudio
    @Test
    public void pruebaModificarEstudio() throws ParseException {

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();

        try {
            EstudioDto estudioDto = new EstudioDto();
            estudioDto.setNombre("Perros calientes raros parte 2");
            estudioDto.setTipoInstrumento("Encuesta");
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

    // Esta prueba permite eliminar un estudio
    @Test
    public void pruebaEliminarEstudio(){

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            servicio.eliminarEstudio(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }
}
