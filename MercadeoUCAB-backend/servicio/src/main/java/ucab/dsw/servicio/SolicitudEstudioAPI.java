package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoMedioComunicacion;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/solicitudEstudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SolicitudEstudioAPI extends AplicacionBase{

    @GET
    @Path("/allSolicitudEstudio")
    public List<SolicitudEstudio> listarSolicitudes(){
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        return daoSolicitudEstudio.findAll(SolicitudEstudio.class);
    }

    @GET
    @Path("/consultarSolicitudEstudio/{id}")
    public SolicitudEstudio consultarSolicitud(@PathParam("id") long id){
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        return daoSolicitudEstudio.find(id, SolicitudEstudio.class);
    }

    @GET
    @Path("/mostrarSolicitudesActivas")
    public List<SolicitudEstudio> mostrarSolicitudesActivas(){
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        List<SolicitudEstudio> listaSolicitud = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
        List<SolicitudEstudio> listaSolicitudesActivas = new ArrayList<SolicitudEstudio>();

        for (SolicitudEstudio solicitudEstudio: listaSolicitud){

            if(solicitudEstudio.get_estatus().equals("Activo")){
                listaSolicitudesActivas.add(solicitudEstudio);
            }
        }
        return  listaSolicitudesActivas;
    }

    @POST
    @Path("/addSolicitudEstudio")
    public SolicitudEstudio addSolicitudEstudio(SolicitudEstudioDto solicitudEstudioDto){

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
            daoSolicitudEstudio.insert(solicitudEstudio);

            return solicitudEstudio;
    }

    @PUT
    @Path("/updateSolicitudEstudio/{id}")
    public Response modificarSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if (solicitudEstudio_modificar != null){

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
            solicitudEstudio_modificar.set_estatus(solicitudEstudioDto.getEstatus());
            DaoEstudio daoEstudio = new DaoEstudio();

            if (solicitudEstudio_modificar.get_estatus() == "Inactivo") {

                List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

                for (Estudio estudio : listaEstudio) {

                    if (estudio.getSolicitudEstudio().get_id() == id) {
                        estudio.set_estatus("Inactivo");
                        daoEstudio.update(estudio);
                    }
                }
            } else if (solicitudEstudio_modificar.get_estatus() == "Activo"){

                List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

                for (Estudio estudio : listaEstudio) {

                    if (estudio.getSolicitudEstudio().get_id() == id) {
                        estudio.set_estatus("Activo");
                        daoEstudio.update(estudio);
                    }
                }
            }

            return Response.ok().entity(solicitudEstudio_modificar).build();
        }
        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @DELETE
    @Path("/deleteSolicitudEstudio/{id}")
    public Response eliminarSolicitudEstudio(@PathParam("id") long id){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if(solicitudEstudio != null){

            DaoEstudio daoEstudio = new DaoEstudio();
            DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
            List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);
            List<MedioComunicacion> listaMedioComunicacion = daoMedioComunicacion.findAll(MedioComunicacion.class);

            for(Estudio estudio: listaEstudio){

                if(estudio.getSolicitudEstudio().get_id() == id){
                    daoEstudio.delete(estudio);

                }
            }

            for(MedioComunicacion medioComunicacion: listaMedioComunicacion){

                if(medioComunicacion.get_solicitudEstudio().get_id() == id){
                    daoMedioComunicacion.delete(medioComunicacion);
                }
            }

            daoSolicitudEstudio.delete(solicitudEstudio);
            return Response.ok().entity(solicitudEstudio).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
