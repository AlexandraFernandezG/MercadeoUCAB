import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.Hijo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HijoAPI_Test {

    //Listar todos los hijos (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaListarHijos(){

        ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();

        try {
            Assertions.assertTrue(servicio.listarHijos().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un hijo (Esta forma fue realizada por Valentina)
    @Test
    public void pruebaConsultarHijo(){

        ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();
        Hijo hijo_buscar = servicio.consultarHijo(1);

        try {
            Assertions.assertEquals(1, hijo_buscar.get_id());

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }
    }

    // Esta prueba permite insertar un hijo
    @Test
    public void pruebaInsertarHijo() throws Exception {

        ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();

        try {

            HijoDto hijoDto = new HijoDto();

            String date1 = "2013-11-16";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date1);
            hijoDto.setFechaNacimiento(myDate);
            hijoDto.setGenero("masculino");
            hijoDto.setEstatus("Activo");
            //Revisar sus registros en la base de datos
            InformacionDto informacionDto = new InformacionDto(1);
            hijoDto.setInformacionDto(informacionDto);

            //Lista hijo
            List<HijoDto> listaHijoDto = new ArrayList<HijoDto>();

            listaHijoDto.add(hijoDto);

            servicio.addHijo(listaHijoDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    //Esta prueba nos permite actualizar un hijo
    @Test
    public void pruebaModificarHijo() throws ParseException {

        ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();

        try {
            HijoDto hijoDto = new HijoDto();

            String date1 = "2010-12-01";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date1);
            hijoDto.setFechaNacimiento(myDate);
            hijoDto.setGenero("masculino");
            hijoDto.setEstatus("Activo");
            //Revisar sus registros en la base de datos
            servicio.updateHijo(1, hijoDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    // Esta prueba permite eliminar un hijo
    @Test
    public void pruebaEliminarHijo(){

        ucab.dsw.servicio.HijoAPI servicio = new ucab.dsw.servicio.HijoAPI();

        try {
            //Revisar sus registros en la base de datos
            servicio.deleteHijo(4);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

}
