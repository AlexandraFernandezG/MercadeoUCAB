import org.junit.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.entidades.Estudio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstudioAPI_Test {

    // Esta prueba permite insertar un estudio a la BD
    @Test
    public void pruebaInsertarEstudio() throws Exception {

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();
        EstudioDto estudioDto = new EstudioDto();

        estudioDto.set_nombre("Perros calientes raros parte 2");
        estudioDto.set_tipoInstrumento("Encuesta");
        String date1 = "2020-12-01";
        DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = forma.parse(date1);
        estudioDto.set_fechaInicio(myDate);
        String date2 = "2021-01-21";
        DateFormat forma2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate2 = forma2.parse(date2);
        estudioDto.set_fechaFin(myDate2);
        estudioDto.set_estatus("Activo");
        Estudio resultado = servicio.addEstudios(estudioDto);


        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    @Test
    public void pruebaModificarEstudio() throws ParseException {

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();
        EstudioDto estudioDto = new EstudioDto();
        estudioDto.set_nombre("Perros calientes raros parte 2");
        estudioDto.set_tipoInstrumento("Encuesta");
        String date1 = "2020-12-01";
        DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = forma.parse(date1);
        estudioDto.set_fechaInicio(myDate);
        String date2 = "2021-01-21";
        DateFormat forma2 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate2 = forma2.parse(date2);
        estudioDto.set_fechaFin(myDate2);
        estudioDto.set_estatus("Activo");
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarEstudio(1L,estudioDto);
    }

    @Test
    public void pruebaEliminarEstudio(){

        ucab.dsw.servicio.EstudioAPI servicio = new ucab.dsw.servicio.EstudioAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarEstudio(1L);

    }
}
