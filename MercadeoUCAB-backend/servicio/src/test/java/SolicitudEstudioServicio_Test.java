import org.junit.*;
import org.junit.jupiter.api.Assertions;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.servicio.SolicitudEstudioServicio;

import javax.ws.rs.core.Response;

public class SolicitudEstudioServicio_Test {

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarSolicitudess
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarSolicitudes(){

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();
        Response respuesta = servicio.listarSolicitudes();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ConsultarSolicitud
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaConsultarSolicitud(){

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();
        Response respuesta = servicio.consultarSolicitud(1);
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ListarSolicitudesActivas
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaListarSolicitudesActivas(){

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();
        Response respuesta = servicio.mostrarSolicitudesActivas();
        Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método InsertarSolicitud
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaInsertarSolicitud() throws Exception {

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();

        try {
            SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

            solicitudEstudioDto.setDescripcion("Investigacion acerca de los perros calientes de arandanos");
            solicitudEstudioDto.setGenero("");
            solicitudEstudioDto.setEdadMaxima(35);
            solicitudEstudioDto.setEdadMinima(20);
            solicitudEstudioDto.setEstadoCivil("");
            solicitudEstudioDto.setDisponibilidadEnLinea("");
            solicitudEstudioDto.setCantidadPersonas(0);
            solicitudEstudioDto.setCantidadHijos(0);
            solicitudEstudioDto.setGeneroHijos("");
            solicitudEstudioDto.setEdadMinimaHijos(0);
            solicitudEstudioDto.setEdadMaximaHijos(0);
            solicitudEstudioDto.setEstado("En proceso");
            solicitudEstudioDto.setEstatus("Activo");

            // Recuerden que deben ver los id de los registros en la BD
            NivelAcademicoDto nivelAcademicoDto = new NivelAcademicoDto(1);
            solicitudEstudioDto.setNivelAcademicoDto(nivelAcademicoDto);

            // Recuerden que deben ver los id de los registros en la BD
            UsuarioDto usuarioDto = new UsuarioDto(1);
            solicitudEstudioDto.setUsuarioDto(usuarioDto);
            ;

            // Recuerden que deben ver los id de los registros en la BD
            ProductoDto productoDto = new ProductoDto(1);
            solicitudEstudioDto.setProductoDto(productoDto);

            // Recuerden que deben ver los id de los registros en la BD
            OcupacionDto ocupacionDto = new OcupacionDto(1);
            solicitudEstudioDto.setOcupacionDto(ocupacionDto);

            // Recuerden que deben ver los id de los registros en la BD
            NivelEconomicoDto nivelEconomicoDto = new NivelEconomicoDto(1);
            solicitudEstudioDto.setNivelEconomicoDto(nivelEconomicoDto);

            Response respuesta = servicio.addSolicitudEstudio(solicitudEstudioDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarEstatusSolicitud
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarEstatusSolicitud(){

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();

        try {
            SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

            solicitudEstudioDto.setEstatus("Inactivo");
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.modificarEstatusSolicitudEstudio(2, solicitudEstudioDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }
    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método ModificarSolicitud
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaModificarSolicitud(){

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();

        try {
            SolicitudEstudioDto solicitudEstudioDto = new SolicitudEstudioDto();

            solicitudEstudioDto.setDescripcion("Investigacion acerca de los perros calientes de arandanos");
            solicitudEstudioDto.setGenero("");
            solicitudEstudioDto.setEdadMaxima(35);
            solicitudEstudioDto.setEdadMinima(20);
            solicitudEstudioDto.setEstadoCivil("");
            solicitudEstudioDto.setDisponibilidadEnLinea("");
            solicitudEstudioDto.setCantidadPersonas(0);
            solicitudEstudioDto.setCantidadHijos(0);
            solicitudEstudioDto.setGeneroHijos("");
            solicitudEstudioDto.setEdadMinimaHijos(0);
            solicitudEstudioDto.setEdadMaximaHijos(0);
            solicitudEstudioDto.setEstatus("Activo");
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.modificarSolicitudEstudio(2, solicitudEstudioDto);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }

    /**
     * Prueba unitaria para verificar el funcionamiento del método EliminarSolicitud
     * @author Emanuel Di Cristofaro
     */
    @Test
    public void pruebaEliminarSolicitud(){

        SolicitudEstudioServicio servicio = new SolicitudEstudioServicio();

        try {
            // Recuerden que deben ver los id de los registros en la BD
            Response respuesta = servicio.eliminarSolicitudEstudio(2);
            Assert.assertEquals(respuesta.getStatus(),Response.Status.OK.getStatusCode());

        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e.getCause());
        }

    }


}
