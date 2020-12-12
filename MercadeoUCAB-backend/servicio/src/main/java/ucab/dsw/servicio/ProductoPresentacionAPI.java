package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoProductoPresentacion;
import ucab.dsw.dtos.ProductoPresentacionDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/productoPresentacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoPresentacionAPI extends AplicacionBase{

    //Este metodo lista todas las presentaciones de un producto
    @GET
    @Path("/listarPresentacionesProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Presentacion> listarProductoPresentacion(@PathParam("id") long id) throws NullPointerException {

        DaoProductoPresentacion daoProductoPresentacion = new DaoProductoPresentacion();
        List<ProductoPresentacion> listaProductoPresentacion = daoProductoPresentacion.findAll(ProductoPresentacion.class);
        List<Presentacion> listaPresentaciones = new ArrayList<Presentacion>();

        try {

            for (ProductoPresentacion productoPresentacion: listaProductoPresentacion){

                if(productoPresentacion.get_producto().get_id() == id){

                    long idFk = productoPresentacion.get_presentacion().get_id();
                    DaoPresentacion daoPresentacion = new DaoPresentacion();
                    Presentacion presentacion = daoPresentacion.find(idFk, Presentacion.class);
                    listaPresentaciones.add(presentacion);
                }

            }
            return listaPresentaciones;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    //Este metodo agrega ProductoPresentacion
    @POST
    @Path("/addProductoPresentacion")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public ProductoPresentacionDto addProductoPresentacion(ProductoPresentacionDto productoPresentacionDto){

        ProductoPresentacionDto resultado = new ProductoPresentacionDto();

        try {

            DaoProductoPresentacion daoProductoPresentacion = new DaoProductoPresentacion();
            ProductoPresentacion productoPresentacion = new ProductoPresentacion();

            productoPresentacion.set_estatus(productoPresentacionDto.get_estatus());
            Producto producto = new Producto(productoPresentacionDto.get_productoDto().getId());
            productoPresentacion.set_producto(producto);
            Presentacion presentacion = new Presentacion(productoPresentacionDto.get_presentacionDto().getId());
            productoPresentacion.set_presentacion(presentacion);
            ProductoPresentacion resul = daoProductoPresentacion.insert(productoPresentacion);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

        return resultado;
    }

    //Actualizar el estatus de ProductoPresentacion
    @PUT
    @Path("/updateEstatusProductoPresentacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateEstatusProductoPresentacion(@PathParam("id") long id, ProductoPresentacionDto productoPresentacionDto){

        DaoProductoPresentacion daoProductoPresentacion = new DaoProductoPresentacion();
        ProductoPresentacion productoPresentacion_modificar = daoProductoPresentacion.find(id, ProductoPresentacion.class);

        if(productoPresentacion_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            productoPresentacion_modificar.set_estatus(productoPresentacionDto.get_estatus());
            daoProductoPresentacion.update(productoPresentacion_modificar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(productoPresentacion_modificar).build();
    }

    //Eliminar un ProductoPresentacion
    @DELETE
    @Path("/deleteProductoPresentacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProductoPresentacion(@PathParam("id") long id){

        DaoProductoPresentacion daoProductoPresentacion = new DaoProductoPresentacion();
        ProductoPresentacion productoPresentacion_eliminar = daoProductoPresentacion.find(id, ProductoPresentacion.class);

        if(productoPresentacion_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoProductoPresentacion.delete(productoPresentacion_eliminar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(productoPresentacion_eliminar).build();

    }
}
