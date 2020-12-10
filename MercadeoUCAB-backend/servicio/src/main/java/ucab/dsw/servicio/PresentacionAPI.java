package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.dtos.PresentacionDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/presentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class PresentacionAPI extends AplicacionBase{

    @GET
    @Path("/allPresentacion")
    public List<Presentacion> listarPresentaciones(){
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        return daoPresentacion.findAll(Presentacion.class);
    }

    @GET
    @Path("/consultarPresentacion/{id}")
    public Presentacion consultarPresentacion(@PathParam("id") long id){
        DaoPresentacion daoPresentacion = new DaoPresentacion();
        return daoPresentacion.find(id, Presentacion.class);
    }

    @GET
    @Path("/mostrarPresentacionesActivas")
    public List<Presentacion> presentacionesActivas(){

        DaoPresentacion daoPresentacion = new DaoPresentacion();
        List<Presentacion> listaPresentacion = daoPresentacion.findAll(Presentacion.class);
        List<Presentacion> listaPresentacionActivas = new ArrayList<Presentacion>();

        for(Presentacion presentacion: listaPresentacion){

            if(presentacion.get_estatus().equals("Activo")){
                listaPresentacionActivas.add(presentacion);
            }
        }
        return listaPresentacionActivas;
    }

    @POST
    @Path("/addPresentacion")
    public Presentacion addPresentacion(PresentacionDto presentacionDto){

        DaoPresentacion daoPresentacion = new DaoPresentacion();
        Presentacion presentacion = new Presentacion();

            presentacion.set_nombre(presentacionDto.getNombre());
            presentacion.set_caracteristicas(presentacionDto.getCaracteristicas());
            presentacion.set_estatus(presentacionDto.getEstatus());
            Producto producto = new Producto(presentacionDto.getProductoDto().getId());
            presentacion.set_producto(producto);
            daoPresentacion.insert(presentacion);

            return presentacion;
    }

    @PUT
    @Path("/updatePresentacion/{id}")
    public Response updatePresentacion(@PathParam("id") long id, PresentacionDto presentacionDto){

        DaoPresentacion daoPresentacion = new DaoPresentacion();
        Presentacion presentacion_modificar = daoPresentacion.find(id, Presentacion.class);

        if(presentacion_modificar != null){

            presentacion_modificar.set_nombre(presentacionDto.getNombre());
            presentacion_modificar.set_caracteristicas(presentacionDto.getCaracteristicas());
            presentacion_modificar.set_estatus(presentacionDto.getEstatus());
            daoPresentacion.update(presentacion_modificar);
            return Response.ok().entity(presentacion_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deletePresentacion/{id}")
    public Response eliminarPresentacion(@PathParam("id") long id){

        DaoPresentacion daoPresentacion = new DaoPresentacion();
        Presentacion presentacion_eliminar = daoPresentacion.find(id, Presentacion.class);

        if(presentacion_eliminar != null){

            daoPresentacion.delete(presentacion_eliminar);
            return Response.ok().entity(presentacion_eliminar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
