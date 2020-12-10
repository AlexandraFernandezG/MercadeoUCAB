package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.TipoDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Tipo;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path( "/tipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TipoAPI extends AplicacionBase{

    @GET
    @Path("/allTipo")
    public List<Tipo> listarTipos(){
        DaoTipo daoTipo = new DaoTipo();
        return daoTipo.findAll(Tipo.class);
    }

    @GET
    @Path("/consultarTipo/{id}")
    public Tipo consultarTipo(@PathParam("id") long id){
        DaoTipo daoTipo = new DaoTipo();
        return daoTipo.find(id, Tipo.class);
    }

    @GET
    @Path("/mostrarTiposActivos")
    public List<Tipo> tiposActivos(){

        DaoTipo daoTipo = new DaoTipo();
        List<Tipo> listaTipo = daoTipo.findAll(Tipo.class);
        List<Tipo> listaTipoActivo = new ArrayList<Tipo>();

        for(Tipo tipo: listaTipo){

            if(tipo.get_estatus().equals("Activo")){
                listaTipoActivo.add(tipo);
            }
        }
        return listaTipoActivo;
    }

    @POST
    @Path("/addTipo")
    public Tipo addTipo(TipoDto tipoDto){

        DaoTipo daoTipo = new DaoTipo();
        Tipo tipo = new Tipo();

            tipo.set_nombre(tipoDto.getNombre());
            tipo.set_descripcion(tipoDto.getDescripcion());
            tipo.set_estatus(tipoDto.getEstatus());
            Producto producto = new Producto(tipoDto.getProductoDto().getId());
            tipo.set_producto(producto);
            daoTipo.insert(tipo);

            return tipo;
    }

    @PUT
    @Path("/updateTipo/{id}")
    public Response updateTipo(@PathParam("id") long id, TipoDto tipoDto){

        DaoTipo daoTipo = new DaoTipo();
        Tipo tipo_modificar = daoTipo.find(id, Tipo.class);

        if(tipo_modificar != null){

            tipo_modificar.set_nombre(tipoDto.getNombre());
            tipo_modificar.set_descripcion(tipoDto.getDescripcion());
            tipo_modificar.set_estatus(tipoDto.getEstatus());
            daoTipo.update(tipo_modificar);
            return Response.ok().entity(tipo_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteTipo/{id}")
    public Response eliminarTipo(@PathParam("id") long id){

        DaoTipo daoTipo = new DaoTipo();
        Tipo tipo_eliminar = daoTipo.find(id, Tipo.class);

        if(tipo_eliminar != null){

            daoTipo.delete(tipo_eliminar);
            return Response.ok().entity(tipo_eliminar).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
