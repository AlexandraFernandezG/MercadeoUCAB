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

        solicitudEstudio.setDescripcionSolicitud(solicitudEstudio.getDescripcionSolicitud());
        solicitudEstudio.setGeneroSolicitud(solicitudEstudioDto.getGeneroSolicitud());
        solicitudEstudio.setFechaSolicitud(solicitudEstudioDto.getFechaSolicitud());
        solicitudEstudio.setRegionSolicitud(solicitudEstudioDto.getRegionSolicitud());
        solicitudEstudio.setEdadMinimaPoblacion(solicitudEstudioDto.getEdadMinimaPoblacion());
        solicitudEstudio.setEdadMaximaPoblacion(solicitudEstudioDto.getEdadMaximaPoblacion());
        solicitudEstudio.set_estatus(solicitudEstudioDto.get_estatus());
        NivelEconomico nivelEconomico = new NivelEconomico(solicitudEstudioDto.getNivelEconomicoDto().getId());
        Usuario usuario = new Usuario(solicitudEstudioDto.getUsuarioDto().getId());
        Producto producto = new Producto(solicitudEstudioDto.getProductoDto().getId());
        solicitudEstudio.setNivelEconomico(nivelEconomico);
        solicitudEstudio.setUsuario(usuario);
        solicitudEstudio.setProducto(producto);
        daoSolicitudEstudio.insert(solicitudEstudio);

        return solicitudEstudio;

    }

    @PUT
    @Path("/updateSolicitudEstudio/{id}")
    public Response modificarSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        if (solicitudEstudio_modificar != null){

            solicitudEstudio_modificar.setDescripcionSolicitud(solicitudEstudioDto.getDescripcionSolicitud());
            solicitudEstudio_modificar.setGeneroSolicitud(solicitudEstudioDto.getGeneroSolicitud());
            solicitudEstudio_modificar.setFechaSolicitud(solicitudEstudioDto.getFechaSolicitud());
            solicitudEstudio_modificar.setRegionSolicitud(solicitudEstudioDto.getRegionSolicitud());
            solicitudEstudio_modificar.setEdadMinimaPoblacion(solicitudEstudioDto.getEdadMinimaPoblacion());
            solicitudEstudio_modificar.setEdadMaximaPoblacion(solicitudEstudioDto.getEdadMaximaPoblacion());
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
