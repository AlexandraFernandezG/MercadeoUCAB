package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoMedioComunicacion;
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
    public List<SolicitudEstudio> listarSolicitudes(){
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        return daoSolicitudEstudio.findAll(SolicitudEstudio.class);
    }

    // Consultar solicitud
    @GET
    @Path("/consultarSolicitudEstudio/{id}")
    public SolicitudEstudio consultarSolicitud(@PathParam("id") long id){
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        return daoSolicitudEstudio.find(id, SolicitudEstudio.class);
    }

    //Mostrar solicitudes activas
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

    // Agregar una solicitud
    @POST
    @Path("/addSolicitudEstudio")
    public SolicitudEstudio addSolicitudEstudio(SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = new SolicitudEstudio();

            solicitudEstudio.set_descripcion(solicitudEstudioDto.get_descripcion());
            solicitudEstudio.set_genero(solicitudEstudioDto.get_genero());
            solicitudEstudio.set_edadMaxima(solicitudEstudioDto.get_edadMaxima());
            solicitudEstudio.set_edadMinima(solicitudEstudioDto.get_edadMinima());
            solicitudEstudio.set_estadoCivil(solicitudEstudioDto.get_estadoCivil());
            solicitudEstudio.set_disponibilidadEnLinea(solicitudEstudioDto.get_disponibilidadEnLinea());
            solicitudEstudio.set_cantidadPersonas(solicitudEstudioDto.get_cantidadPersonas());
            solicitudEstudio.set_cantidadHijos(solicitudEstudioDto.get_cantidadHijos());
            solicitudEstudio.set_generoHijos(solicitudEstudioDto.get_generoHijos());
            solicitudEstudio.set_edadMinimaHijos(solicitudEstudioDto.get_edadMinimaHijos());
            solicitudEstudio.set_edadMaximaHijos(solicitudEstudioDto.get_edadMaximaHijos());
            solicitudEstudio.set_estatus(solicitudEstudioDto.get_estatus());
            NivelEconomico nivelEconomico = new NivelEconomico(solicitudEstudioDto.get_nivelEconomicoDto().getId());
            Usuario usuario = new Usuario(solicitudEstudioDto.get_usuarioDto().getId());
            Producto producto = new Producto(solicitudEstudioDto.get_productoDto().getId());
            Ocupacion ocupacion = new Ocupacion(solicitudEstudioDto.get_ocupacionDto().getId());
            NivelAcademico nivelAcademico = new NivelAcademico(solicitudEstudioDto.get_nivelAcademicoDto().getId());
            solicitudEstudio.set_nivelEconomico(nivelEconomico);
            solicitudEstudio.set_usuario(usuario);
            solicitudEstudio.set_producto(producto);
            solicitudEstudio.set_ocupacion(ocupacion);
            solicitudEstudio.set_nivelAcademico(nivelAcademico);
            daoSolicitudEstudio.insert(solicitudEstudio);

            return solicitudEstudio;
    }

    //Actualizar estatus de solicitud
    @PUT
    @Path("/estatusSolicitudEstudio/{id}")
    public Response modificarEstatusSolicitudEstudios(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if (solicitudEstudio_modificar != null){

            solicitudEstudio_modificar.set_estatus(solicitudEstudioDto.get_estatus());
            daoSolicitudEstudio.update(solicitudEstudio_modificar);
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

    //Actualizar solicitud
    @PUT
    @Path("/updateSolicitudEstudio/{id}")
    public Response modificarSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if (solicitudEstudio_modificar != null){

            solicitudEstudio_modificar.set_descripcion(solicitudEstudioDto.get_descripcion());
            solicitudEstudio_modificar.set_genero(solicitudEstudioDto.get_genero());
            solicitudEstudio_modificar.set_edadMaxima(solicitudEstudioDto.get_edadMaxima());
            solicitudEstudio_modificar.set_edadMinima(solicitudEstudioDto.get_edadMinima());
            solicitudEstudio_modificar.set_estadoCivil(solicitudEstudioDto.get_estadoCivil());
            solicitudEstudio_modificar.set_disponibilidadEnLinea(solicitudEstudioDto.get_disponibilidadEnLinea());
            solicitudEstudio_modificar.set_cantidadPersonas(solicitudEstudioDto.get_cantidadPersonas());
            solicitudEstudio_modificar.set_cantidadHijos(solicitudEstudioDto.get_cantidadHijos());
            solicitudEstudio_modificar.set_generoHijos(solicitudEstudioDto.get_generoHijos());
            solicitudEstudio_modificar.set_edadMinimaHijos(solicitudEstudioDto.get_edadMinimaHijos());
            solicitudEstudio_modificar.set_edadMaximaHijos(solicitudEstudioDto.get_edadMaximaHijos());
            daoSolicitudEstudio.update(solicitudEstudio_modificar);
            return Response.ok().entity(solicitudEstudio_modificar).build();

        }
        else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    //Eliminar solicitud
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

                if(medioComunicacion.getSolicitudEstudio().get_id() == id){
                    daoMedioComunicacion.delete(medioComunicacion);
                }
            }

            daoSolicitudEstudio.delete(solicitudEstudio);
            return Response.ok().entity(solicitudEstudio).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
