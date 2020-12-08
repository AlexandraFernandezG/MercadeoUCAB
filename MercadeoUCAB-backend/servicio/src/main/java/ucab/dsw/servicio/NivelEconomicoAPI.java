package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoNivelEconomico;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.NivelEconomicoDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.NivelAcademico;
import ucab.dsw.entidades.NivelEconomico;
import ucab.dsw.entidades.SolicitudEstudio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/nivelEconomico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class NivelEconomicoAPI extends AplicacionBase{

    // Listar Niveles economicos
    @GET
    @Path("/allNivelEconomico")
    public List<NivelEconomico> listarNivelEconomico(){
        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        return daoNivelEconomico.findAll(NivelEconomico.class);
    }

    // Consultar un nivel economico
    @GET
    @Path("/consultarNivelEconomico/{id}")
    public NivelEconomico consultarNivelEconomico(@PathParam("id") long id){
        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        return daoNivelEconomico.find(id, NivelEconomico.class);
    }

    // Agregar un nivel economico
    @POST
    @Path("/addNivelEconomico")
    public NivelEconomico addNivelEconomico(NivelEconomicoDto nivelEconomicoDto){

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico = new NivelEconomico();

            nivelEconomico.setDescripcion(nivelEconomicoDto.getDescripcion());
            nivelEconomico.set_estatus(nivelEconomicoDto.get_estatus());
            daoNivelEconomico.insert(nivelEconomico);

            return nivelEconomico;
    }

    //Actualizar un nivel economico
    @PUT
    @Path("/updateNivelEconomico/{id}")
    public Response updateNivelEconomico(@PathParam("id") long id, NivelEconomicoDto nivelEconomicoDto){

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico_modificar = daoNivelEconomico.find(id, NivelEconomico.class);

        if(nivelEconomico_modificar != null){

            nivelEconomico_modificar.setDescripcion(nivelEconomicoDto.getDescripcion());
            nivelEconomico_modificar.set_estatus(nivelEconomicoDto.get_estatus());
            daoNivelEconomico.update(nivelEconomico_modificar);
            return Response.ok().entity(nivelEconomico_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    //Eliminar un nivel economico
    @DELETE
    @Path("/deleteNivelEconomico/{id}")
    public Response eliminarNivelEconomico(@PathParam("id") long id){

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico_eliminar = daoNivelEconomico.find(id, NivelEconomico.class);

        if(nivelEconomico_eliminar != null){

            daoNivelEconomico.delete(nivelEconomico_eliminar);
            return Response.ok().entity(nivelEconomico_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
