package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoProductoTipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ProductoTipoDto;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.ProductoTipo;
import ucab.dsw.entidades.Tipo;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/productoTipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoTipoAPI extends AplicacionBase{

    //Este metodo lista todos los tipos de un producto
    @GET
    @Path("/listarTiposProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Tipo> listarProductoTipo(@PathParam("id") long id) throws NullPointerException {

        DaoProductoTipo daoProductoTipo = new DaoProductoTipo();
        List<ProductoTipo> listaProductoTipo = daoProductoTipo.findAll(ProductoTipo.class);
        List<Tipo> listaTipos = new ArrayList<Tipo>();

        try {

            for (ProductoTipo productoTipo: listaProductoTipo){

                if(productoTipo.get_producto().get_id() == id){

                    long idFk = productoTipo.get_tipo().get_id();
                    DaoTipo daoTipo = new DaoTipo();
                    Tipo tipo = daoTipo.find(idFk, Tipo.class);
                    listaTipos.add(tipo);
                }

            }
            return listaTipos;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    //Este metodo agrega ProductoTipo
    @POST
    @Path("/addProductoTipo")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public ProductoTipoDto addProductoTipo(ProductoTipoDto productoTipoDto){

        ProductoTipoDto resultado = new ProductoTipoDto();

        try {

            DaoProductoTipo daoProductoTipo = new DaoProductoTipo();
            ProductoTipo productoTipo = new ProductoTipo();

            productoTipo.set_estatus(productoTipoDto.getEstatus());
            Producto producto = new Producto(productoTipoDto.getProductoDto().getId());
            productoTipo.set_producto(producto);
            Tipo tipo = new Tipo(productoTipoDto.getTipoDto().getId());
            productoTipo.set_tipo(tipo);
            ProductoTipo resul = daoProductoTipo.insert(productoTipo);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

        return resultado;
    }

    //Actualizar el estatus de ProductoTipo
    @PUT
    @Path("/updateEstatusProductoTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateEstatusProductoTipo(@PathParam("id") long id, ProductoTipoDto productoTipoDto){

        DaoProductoTipo daoProductoTipo = new DaoProductoTipo();
        ProductoTipo productoTipo_modificar = daoProductoTipo.find(id, ProductoTipo.class);

        if(productoTipo_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            productoTipo_modificar.set_estatus(productoTipoDto.getEstatus());
            daoProductoTipo.update(productoTipo_modificar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(productoTipo_modificar).build();
    }

    //Eliminar ProductoTipo
    @DELETE
    @Path("/deleteProductoTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProductoTipo(@PathParam("id") long id){

        DaoProductoTipo daoProductoTipo = new DaoProductoTipo();
        ProductoTipo productoTipo_eliminar = daoProductoTipo.find(id, ProductoTipo.class);

        if(productoTipo_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            daoProductoTipo.delete(productoTipo_eliminar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(productoTipo_eliminar).build();

    }

}
