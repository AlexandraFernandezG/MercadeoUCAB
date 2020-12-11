package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoNivelEconomico;
import ucab.dsw.dtos.NivelEconomicoDto;
import ucab.dsw.entidades.NivelEconomico;


import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/nivelEconomico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class NivelEconomicoAPI {
    @GET
    @Path("/allNivelEconomico")
    public List<NivelEconomico> listarNivelEconomico() {

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        return daoNivelEconomico.findAll(NivelEconomico.class);
    }

    @GET
    @Path("/consultarNivelEconomico/{id}")
    public NivelEconomico consultarNivelEconomico(@PathParam("id") long id) {
        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        return daoNivelEconomico.find(id, NivelEconomico.class);
    }

    @POST
    @Path("/addNivelEconomico")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public NivelEconomicoDto addCategoria(NivelEconomicoDto nivelEconomicoDto) {
        NivelEconomicoDto resultado = new NivelEconomicoDto();
        try {

            DaoNivelEconomico dao = new DaoNivelEconomico();
            NivelEconomico nivelEconomico = new NivelEconomico();

            nivelEconomico.set_descripcion(nivelEconomicoDto.getDescripcion());
            NivelEconomico resul = dao.insert(nivelEconomico);
            resultado.setId(resul.get_id());
        } catch (Exception ex) {
            String problema = ex.getMessage();
        }
        return resultado;
    }

    @PUT
    @Path("/updateNivelEconomico/{id}")
    public Response updateNivelEconomico(@PathParam("id") long id, NivelEconomicoDto nivelEconomicoDto) {

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico_modificar = daoNivelEconomico.find(id, NivelEconomico.class);

        if (nivelEconomico_modificar != null) {

            nivelEconomico_modificar.set_descripcion(nivelEconomicoDto.getDescripcion());
            nivelEconomico_modificar.set_estatus(nivelEconomicoDto.getEstatus());
            return Response.ok().entity(nivelEconomico_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteNivelEconomico/{id}")
    public Response deleteNivelEconomico(@PathParam("id") long id) {

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico_eliminar = daoNivelEconomico.find(id, NivelEconomico.class);

        if (nivelEconomico_eliminar != null) {

            daoNivelEconomico.delete(nivelEconomico_eliminar);
            return Response.ok().entity(nivelEconomico_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}