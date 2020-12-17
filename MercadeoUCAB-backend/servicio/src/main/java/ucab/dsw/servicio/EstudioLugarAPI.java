package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoEstudioLugar;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.EstudioLugarDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.EstudioLugar;
import ucab.dsw.entidades.Lugar;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/estudioLugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EstudioLugarAPI extends AplicacionBase {

    //Este método lista los lugares de un estudio
    @GET
    @Path("/listarLugaresEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Lugar> listarLugaresEstudio(@PathParam("id") long id) throws NullPointerException {

        DaoEstudioLugar daoEstudioLugar = new DaoEstudioLugar();
        List<EstudioLugar> listarLugaresEstudio = daoEstudioLugar.findAll(EstudioLugar.class);
        List<Lugar> listaLugares = new ArrayList<Lugar>();

        try {

            for (EstudioLugar estudioLugar : listarLugaresEstudio) {

                if (estudioLugar.get_estudio().get_id() == id) {

                    long idFk = estudioLugar.get_lugar().get_id();
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
    public EstudioLugarDto addEstudioLugar(EstudioLugarDto estudioLugarDto){

        EstudioLugarDto resultado = new EstudioLugarDto();

        try {

            DaoEstudioLugar daoEstudioLugar = new DaoEstudioLugar();
            EstudioLugar estudioLugar = new EstudioLugar();
            DaoLugar daoLugar = new DaoLugar();
            DaoEstudio daoEstudio = new DaoEstudio();

            estudioLugar.set_estatus(estudioLugarDto.getEstatus());
            Estudio estudio = daoEstudio.find(estudioLugarDto.getEstudioDto().getId(), Estudio.class);
            Lugar lugar = daoLugar.find(estudioLugarDto.getLugarDto().getId() , Lugar.class);
            estudioLugar.set_estudio(estudio);
            estudioLugar.set_lugar(lugar);
            EstudioLugar resul = daoEstudioLugar.insert(estudioLugar);
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
    public Response updateEstatusEstudioLugar(@PathParam("id") long id, EstudioLugarDto estudioLugarDto){

        DaoEstudioLugar daoEstudioLugar = new DaoEstudioLugar();
        EstudioLugar estudioLugar_modificar = daoEstudioLugar.find(id, EstudioLugar.class);

        if(estudioLugar_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            estudioLugar_modificar.set_estatus(estudioLugarDto.getEstatus());
            daoEstudioLugar.update(estudioLugar_modificar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(estudioLugar_modificar).build();
    }

    //Eliminar la relacion Estudio Lugar
    @DELETE
    @Path("/deleteEstudioLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteEstudiaLugar(@PathParam("id") long id){

        DaoEstudioLugar daoEstudioLugar = new DaoEstudioLugar();
        EstudioLugar estudioLugar_eliminar = daoEstudioLugar.find(id, EstudioLugar.class);

        if(estudioLugar_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoEstudioLugar.delete(estudioLugar_eliminar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(estudioLugar_eliminar).build();
    }
}
