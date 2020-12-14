package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/solicitudEstudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SolicitudEstudioAPI extends AplicacionBase{

    // Listar solicitudes
    @GET
    @Path("/allSolicitudEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    public List<SolicitudEstudio> listarSolicitudes() throws NullPointerException{
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

        try {
            return daoSolicitudEstudio.findAll(SolicitudEstudio.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    // Consultar solicitud
    @GET
    @Path("/consultarSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public SolicitudEstudio consultarSolicitud(@PathParam("id") long id) throws NullPointerException{
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

        try {
            return daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Mostrar solicitudes activas
    @GET
    @Path("/mostrarSolicitudesActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public List<SolicitudEstudio> mostrarSolicitudesActivas() throws NullPointerException{
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        List<SolicitudEstudio> listaSolicitud = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
        List<SolicitudEstudio> listaSolicitudesActivas = new ArrayList<SolicitudEstudio>();

        try {

            for (SolicitudEstudio solicitudEstudio : listaSolicitud) {

                if (solicitudEstudio.get_estatus().equals("Activo")) {
                    listaSolicitudesActivas.add(solicitudEstudio);
                }
            }
            return listaSolicitudesActivas;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    // Agregar una solicitud
    @POST
    @Path("/addSolicitudEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public SolicitudEstudioDto addSolicitudEstudio(SolicitudEstudioDto solicitudEstudioDto){

        SolicitudEstudioDto resultado = new SolicitudEstudioDto();

        try {

            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
            SolicitudEstudio solicitudEstudio = new SolicitudEstudio();

            solicitudEstudio.set_descripcion(solicitudEstudioDto.getDescripcion());
            solicitudEstudio.set_genero(solicitudEstudioDto.getGenero());
            solicitudEstudio.set_edadMaxima(solicitudEstudioDto.getEdadMaxima());
            solicitudEstudio.set_edadMinima(solicitudEstudioDto.getEdadMinima());
            solicitudEstudio.set_estadoCivil(solicitudEstudioDto.getEstadoCivil());
            solicitudEstudio.set_disponibilidadEnLinea(solicitudEstudioDto.getDisponibilidadEnLinea());
            solicitudEstudio.set_cantidadPersonas(solicitudEstudioDto.getCantidadPersonas());
            solicitudEstudio.set_cantidadHijos(solicitudEstudioDto.getCantidadHijos());
            solicitudEstudio.set_generoHijos(solicitudEstudioDto.getGeneroHijos());
            solicitudEstudio.set_edadMinimaHijos(solicitudEstudioDto.getEdadMinimaHijos());
            solicitudEstudio.set_edadMaximaHijos(solicitudEstudioDto.getEdadMaximaHijos());
            solicitudEstudio.set_estatus(solicitudEstudioDto.getEstatus());
            NivelEconomico nivelEconomico = new NivelEconomico(solicitudEstudioDto.getNivelEconomicoDto().getId());
            Usuario usuario = new Usuario(solicitudEstudioDto.getUsuarioDto().getId());
            Producto producto = new Producto(solicitudEstudioDto.getProductoDto().getId());
            Ocupacion ocupacion = new Ocupacion(solicitudEstudioDto.getOcupacionDto().getId());
            NivelAcademico nivelAcademico = new NivelAcademico(solicitudEstudioDto.getNivelAcademicoDto().getId());
            solicitudEstudio.set_nivelEconomico(nivelEconomico);
            solicitudEstudio.set_usuario(usuario);
            solicitudEstudio.set_producto(producto);
            solicitudEstudio.set_ocupacion(ocupacion);
            solicitudEstudio.set_nivelAcademico(nivelAcademico);
            SolicitudEstudio resul = daoSolicitudEstudio.insert(solicitudEstudio);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

            return resultado;
    }

    //Actualizar estatus de solicitud
    @PUT
    @Path("/estatusSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if (solicitudEstudio_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {

                solicitudEstudio_modificar.set_estatus(solicitudEstudioDto.getEstatus());
                daoSolicitudEstudio.update(solicitudEstudio_modificar);
                DaoEstudio daoEstudio = new DaoEstudio();

                if (solicitudEstudio_modificar.get_estatus() == "Inactivo") {

                    List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

                    for (Estudio estudio : listaEstudio) {

                        if (estudio.get_solicitudEstudio().get_id() == id) {
                            estudio.set_estatus("Inactivo");
                            daoEstudio.update(estudio);
                        }
                    }
                } else if (solicitudEstudio_modificar.get_estatus() == "Activo") {

                    List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

                    for (Estudio estudio : listaEstudio) {

                        if (estudio.get_solicitudEstudio().get_id() == id) {
                            estudio.set_estatus("Activo");
                            daoEstudio.update(estudio);
                        }
                    }
                }

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(solicitudEstudio_modificar).build();

    }

    //Actualizar solicitud
    @PUT
    @Path("/updateSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if (solicitudEstudio_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {

                solicitudEstudio_modificar.set_descripcion(solicitudEstudioDto.getDescripcion());
                solicitudEstudio_modificar.set_genero(solicitudEstudioDto.getGenero());
                solicitudEstudio_modificar.set_edadMaxima(solicitudEstudioDto.getEdadMaxima());
                solicitudEstudio_modificar.set_edadMinima(solicitudEstudioDto.getEdadMinima());
                solicitudEstudio_modificar.set_estadoCivil(solicitudEstudioDto.getEstadoCivil());
                solicitudEstudio_modificar.set_disponibilidadEnLinea(solicitudEstudioDto.getDisponibilidadEnLinea());
                solicitudEstudio_modificar.set_cantidadPersonas(solicitudEstudioDto.getCantidadPersonas());
                solicitudEstudio_modificar.set_cantidadHijos(solicitudEstudioDto.getCantidadHijos());
                solicitudEstudio_modificar.set_generoHijos(solicitudEstudioDto.getGeneroHijos());
                solicitudEstudio_modificar.set_edadMinimaHijos(solicitudEstudioDto.getEdadMinimaHijos());
                solicitudEstudio_modificar.set_edadMaximaHijos(solicitudEstudioDto.getEdadMaximaHijos());
                daoSolicitudEstudio.update(solicitudEstudio_modificar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(solicitudEstudio_modificar).build();

    }

    //Eliminar solicitud
    @DELETE
    @Path("/deleteSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarSolicitudEstudio(@PathParam("id") long id){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if(solicitudEstudio == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {
                daoSolicitudEstudio.delete(solicitudEstudio);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

        return Response.ok().entity(solicitudEstudio).build();
    }
}
