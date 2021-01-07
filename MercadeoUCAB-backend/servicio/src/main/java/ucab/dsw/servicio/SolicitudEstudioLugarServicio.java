package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudioLugar;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.SolicitudEstudioLugarDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.SolicitudEstudioLugar;
import ucab.dsw.entidades.Lugar;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/SolicitudestudioLugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SolicitudEstudioLugarServicio extends AplicacionBase {

    //Este método lista los lugares de un estudio
    @GET
    @Path("/listarLugaresSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> listarLugaresEstudio(@PathParam("id") long id) throws NullPointerException {

        DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
        List<SolicitudEstudioLugar> listarLugaresEstudio = daoSolicitudEstudioLugar.findAll(SolicitudEstudioLugar.class);
        List<Lugar> listaLugares = new ArrayList<Lugar>();

        try {

            for (SolicitudEstudioLugar solicitudEstudioLugar : listarLugaresEstudio) {

                if (solicitudEstudioLugar.get_SolicitudEstudio().get_id() == id) {

                    long idFk = solicitudEstudioLugar.get_lugar().get_id();
                    DaoLugar daoLugar = new DaoLugar();
                    Lugar lugar = daoLugar.find(idFk, Lugar.class);
                    listaLugares.add(lugar);
                }

            }
            return listaLugares;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Agregar la relacion Estudio Lugar
    @POST
    @Path("/addEstudioLugar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public SolicitudEstudioLugarDto addEstudioLugar(SolicitudEstudioLugarDto solicitudEstudioLugarDto){

        SolicitudEstudioLugarDto resultado = new SolicitudEstudioLugarDto();

        try {

            DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
            SolicitudEstudioLugar solicitudEstudioLugar = new SolicitudEstudioLugar();
            DaoLugar daoLugar = new DaoLugar();
            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

            solicitudEstudioLugar.set_estatus(solicitudEstudioLugarDto.getEstatus());
            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudEstudioLugarDto.getSolicitudestudioDto().getId(), SolicitudEstudio.class);
            Lugar lugar = daoLugar.find(solicitudEstudioLugarDto.getLugarDto().getId() , Lugar.class);
            solicitudEstudioLugar.set_SolicitudEstudio(solicitudEstudio);
            solicitudEstudioLugar.set_lugar(lugar);
            SolicitudEstudioLugar resul = daoSolicitudEstudioLugar.insert(solicitudEstudioLugar);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

        return resultado;
    }

    //Actualizar estatus de la relacion
    @PUT
    @Path("/updateEstatusEstudioLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateEstatusEstudioLugar(@PathParam("id") long id, SolicitudEstudioLugarDto solicitudEstudioLugarDto){

        DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
        SolicitudEstudioLugar solicitudEstudioLugar_modificar = daoSolicitudEstudioLugar.find(id, SolicitudEstudioLugar.class);

        if(solicitudEstudioLugar_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            solicitudEstudioLugar_modificar.set_estatus(solicitudEstudioLugarDto.getEstatus());
            daoSolicitudEstudioLugar.update(solicitudEstudioLugar_modificar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(solicitudEstudioLugar_modificar).build();
    }

    //Eliminar la relacion Estudio Lugar
    @DELETE
    @Path("/deleteEstudioLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteEstudiaLugar(@PathParam("id") long id){

        DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
        SolicitudEstudioLugar solicitudEstudioLugar_eliminar = daoSolicitudEstudioLugar.find(id, SolicitudEstudioLugar.class);

        if(solicitudEstudioLugar_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoSolicitudEstudioLugar.delete(solicitudEstudioLugar_eliminar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(solicitudEstudioLugar_eliminar).build();
    }
}
