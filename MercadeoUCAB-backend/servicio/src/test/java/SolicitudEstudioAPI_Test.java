import org.junit.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.SolicitudEstudio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolicitudEstudioAPI_Test {

    //Esta prueba nos permite insertar una solicitud
    @Test
    public void pruebaInsertarSolicitud() throws Exception {

        ucab.dsw.servicio.SolicitudEstudioAPI servicio = new ucab.dsw.servicio.SolicitudEstudioAPI();
        SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

        solicitudEstudioDto.set_descripcion("Investigacion acerca de los perros calientes de arandanos");
        solicitudEstudioDto.set_genero("");
        solicitudEstudioDto.set_edadMaxima(35);
        solicitudEstudioDto.set_edadMinima(20);
        solicitudEstudioDto.set_estadoCivil("");
        solicitudEstudioDto.set_disponibilidadEnLinea("");
        solicitudEstudioDto.set_cantidadPersonas(0);
        solicitudEstudioDto.set_cantidadHijos(0);
        solicitudEstudioDto.set_generoHijos("");
        solicitudEstudioDto.set_edadMinimaHijos(0);
        solicitudEstudioDto.set_edadMaximaHijos(0);
        solicitudEstudioDto.set_estatus("Activo");

        // Recuerden que deben ver los id de los registros en la BD
        NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto(1L);
        solicitudEstudioDto.set_nivelAcademicoDto(nivelAcademicoDto);

        // Recuerden que deben ver los id de los registros en la BD
        UsuarioDto usuarioDto = new UsuarioDto(1L);
        solicitudEstudioDto.set_usuarioDto(usuarioDto);;

        // Recuerden que deben ver los id de los registros en la BD
        ProductoDto productoDto = new ProductoDto(1L);
        solicitudEstudioDto.set_productoDto(productoDto);

        // Recuerden que deben ver los id de los registros en la BD
        OcupacionDto ocupacionDto = new OcupacionDto(3L);
        solicitudEstudioDto.set_ocupacionDto(ocupacionDto);

        // Recuerden que deben ver los id de los registros en la BD
        NivelEconomicoDto nivelEconomicoDto = new NivelEconomicoDto(1L);
        solicitudEstudioDto.set_nivelEconomicoDto(nivelEconomicoDto);

        SolicitudEstudio resultado = servicio.addSolicitudEstudio(solicitudEstudioDto);
        Assert.assertNotEquals(resultado.get_id(), 0);
    }

    //Prueba actualizar una solicitud
    @Test
    public void pruebaModificarSolicitud(){

        ucab.dsw.servicio.SolicitudEstudioAPI servicio = new ucab.dsw.servicio.SolicitudEstudioAPI();
        SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

        solicitudEstudioDto.set_descripcion("Investigacion acerca de los perros calientes de arandanos");
        solicitudEstudioDto.set_genero("");
        solicitudEstudioDto.set_edadMaxima(35);
        solicitudEstudioDto.set_edadMinima(20);
        solicitudEstudioDto.set_estadoCivil("");
        solicitudEstudioDto.set_disponibilidadEnLinea("");
        solicitudEstudioDto.set_cantidadPersonas(0);
        solicitudEstudioDto.set_cantidadHijos(0);
        solicitudEstudioDto.set_generoHijos("");
        solicitudEstudioDto.set_edadMinimaHijos(0);
        solicitudEstudioDto.set_edadMaximaHijos(0);
        // Recuerden que deben ver los id de los registros en la BD
        servicio.modificarSolicitudEstudio(2L, solicitudEstudioDto);

    }

    @Test
    public void pruebaEliminarSolicitud(){

        ucab.dsw.servicio.SolicitudEstudioAPI servicio = new ucab.dsw.servicio.SolicitudEstudioAPI();
        // Recuerden que deben ver los id de los registros en la BD
        servicio.eliminarSolicitudEstudio(2L);

    }


}
