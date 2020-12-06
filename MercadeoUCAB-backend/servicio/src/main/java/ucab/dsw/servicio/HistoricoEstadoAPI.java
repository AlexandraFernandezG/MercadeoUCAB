package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoHistoricoEstado;
import ucab.dsw.dtos.HistoricoEstadoDto;
import ucab.dsw.entidades.HistoricoEstado;
import ucab.dsw.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/historicoEstado" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HistoricoEstadoAPI extends AplicacionBase{

    @GET
    @Path("/allHistoricoEstado")
    public List<HistoricoEstado> listarHistoricos(){
        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        return daoHistoricoEstado.findAll(HistoricoEstado.class);
    }

    @GET
    @Path("/consultarHistorioEstado/{id}")
    public HistoricoEstado consultarHistorico(@PathParam("id") long id){
        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        return daoHistoricoEstado.find(id, HistoricoEstado.class);
    }

    @GET
    @Path("/mostrarHistoricosActivos")
    public List<HistoricoEstado> historicosActivos(){

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        List<HistoricoEstado> listaHistorico = daoHistoricoEstado.findAll(HistoricoEstado.class);
        List<HistoricoEstado> listaHistoricoActivos = new ArrayList<HistoricoEstado>();

        for(HistoricoEstado historicoEstado: listaHistorico){

            if(historicoEstado.get_estatus().equals("Activos")){
                listaHistoricoActivos.add(historicoEstado);
            }
        }
        return listaHistoricoActivos;
    }

    @POST
    @Path("/addHistorioEstado")
    public HistoricoEstado addHistoricoEstado(HistoricoEstadoDto historicoEstadoDto){

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        HistoricoEstado historicoEstado = new HistoricoEstado();

            historicoEstado.setFechaInicio(historicoEstadoDto.get_fechaInicio());
            historicoEstado.setFechaFin(historicoEstado.getFechaFin());
            historicoEstado.set_estatus(historicoEstadoDto.get_estatus());
            Usuario usuario = new Usuario(historicoEstado.getUsuario().get_id());
            historicoEstado.setUsuario(usuario);

            return historicoEstado;
    }

    @DELETE
    @Path("/deleteHistoricoEstado/{id}")
    public Response eliminarHistoricoEstado(@PathParam("id") long id){

        DaoHistoricoEstado daoHistoricoEstado = new DaoHistoricoEstado();
        HistoricoEstado historicoEstado_eliminar = daoHistoricoEstado.find(id, HistoricoEstado.class);

        if(historicoEstado_eliminar != null){

            daoHistoricoEstado.delete(historicoEstado_eliminar);
            return Response.ok().entity(historicoEstado_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }
}
