import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.servicio.InformacionServicio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InformacionServicio_Test {

    //Listar encuestados
    @Test
    public void pruebaListarInformacionEncuestado(){

        InformacionServicio servicio = new InformacionServicio();

        try {
            Assertions.assertTrue(servicio.listarInformacionEncuestados().size() > 0);

        } catch (Exception e) {

            Assertions.fail(e.getMessage());
        }

    }

    //Consultar un encuestado
    @Test
    public void pruebaConsultarEncuestado(){

        InformacionServicio servicio = new InformacionServicio();
        Informacion informacion_buscar = servicio.consultarInformacion(1);

        // Recuerden estar pendientes de los ids de la base de datos
        try {
            Assertions.assertEquals(1, informacion_buscar.get_id());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }

    }

    //Insertar una encuestado

    @Test
    public void pruebaInsertarEncuestado() throws Exception {

        InformacionServicio servicio = new InformacionServicio();
        InformacionDto informacionDto = new InformacionDto();

        try {

            informacionDto.setCedula(24464772);
            informacionDto.setPrimerNombre("Emanuel");
            informacionDto.setSegundoNombre("");
            informacionDto.setPrimerApellido("Di Cristofaro");
            informacionDto.setSegundoApellido("Esposito");
            informacionDto.setGenero("Masculino");
            //Parse para la fecha de nacimiento
            String date = "1996-08-03";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date);
            informacionDto.setFechaNacimiento(myDate);
            informacionDto.setEstadoCivil("Soltero");
            informacionDto.setDisponibilidadEnLinea("Disponible");
            informacionDto.setCantidadPersonas(2);
            informacionDto.setEstatus("Activo");

            //Revisar los registros de la base de datos
            LugarDto lugarDto = new LugarDto(1);
            informacionDto.setLugarDto(lugarDto);

            NivelEconomicoDto nivelEconomicoDto = new NivelEconomicoDto(1);
            informacionDto.setNivelEconomicoDto(nivelEconomicoDto);

            NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto(1);
            informacionDto.setNivelAcademicoDto(nivelAcademicoDto);

            OcupacionDto ocupacionDto = new OcupacionDto(1);
            informacionDto.setOcupacionDto(ocupacionDto);

            UsuarioDto usuarioDto = new UsuarioDto(6);
            informacionDto.setUsuarioDto(usuarioDto);

            InformacionDto resultado = servicio.addInformacion(informacionDto);
            Assert.assertNotEquals(resultado.getId(), 0);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba modificar datos del encuestado

    @Test
    public void pruebaModificarEncuestado(){

        InformacionServicio servicio = new InformacionServicio();
        InformacionDto informacionDto = new InformacionDto();

        try {

            informacionDto.setCedula(24464772);
            informacionDto.setPrimerNombre("Emanuel");
            informacionDto.setSegundoNombre("");
            informacionDto.setPrimerApellido("Di Cristofaro");
            informacionDto.setSegundoApellido("Esposito");
            informacionDto.setGenero("Masculino");
            //Parse para la fecha de nacimiento
            String date = "1996-08-03";
            DateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = forma.parse(date);
            informacionDto.setFechaNacimiento(myDate);
            informacionDto.setEstadoCivil("Soltero");
            informacionDto.setDisponibilidadEnLinea("No Disponible");
            informacionDto.setCantidadPersonas(2);
            informacionDto.setEstatus("Activo");

            //Revisar los registros de la base de datos
            LugarDto lugarDto = new LugarDto(1);
            informacionDto.setLugarDto(lugarDto);

            NivelEconomicoDto nivelEconomicoDto = new NivelEconomicoDto(1);
            informacionDto.setNivelEconomicoDto(nivelEconomicoDto);

            NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto(1);
            informacionDto.setNivelAcademicoDto(nivelAcademicoDto);

            OcupacionDto ocupacionDto = new OcupacionDto(1);
            informacionDto.setOcupacionDto(ocupacionDto);

            UsuarioDto usuarioDto = new UsuarioDto(6);
            informacionDto.setUsuarioDto(usuarioDto);

            servicio.modificarInformacion(1, informacionDto);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    //Prueba para eliminar un encuestado
    @Test
    public void pruebaEliminarEncuestado(){

        InformacionServicio servicio = new InformacionServicio();

        try {

            servicio.deleteInformacion(1);

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }
}
