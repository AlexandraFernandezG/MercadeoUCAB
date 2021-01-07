package ucab.dsw.servicio;

import ucab.dsw.accesodatos.DaoPresentacion;
import ucab.dsw.accesodatos.DaoProducto;
import ucab.dsw.accesodatos.DaoProductoPresentacionTipo;
import ucab.dsw.accesodatos.DaoTipo;
import ucab.dsw.dtos.ProductoPresentacionTipoDto;
import ucab.dsw.entidades.Presentacion;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.ProductoPresentacionTipo;
import ucab.dsw.entidades.Tipo;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/ProductoPresentacionTipo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoPresentacionTipoServicio extends AplicacionBase{

    //Obtener el o los tipos de un producto
    @GET
    @Path("/productoTipoLista/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Tipo> listarTiposProducto(@PathParam("id") long id) throws NullPointerException {

        DaoProductoPresentacionTipo dao = new DaoProductoPresentacionTipo();
        List<ProductoPresentacionTipo> listaProductoPT = dao.findAll(ProductoPresentacionTipo.class);
        List<Tipo> listaTiposProducto = new ArrayList<Tipo>();

        try {

            for (ProductoPresentacionTipo productoPresentacionTipo : listaProductoPT) {

                if (productoPresentacionTipo.get_producto().get_id() == id) {

                    long idFK = productoPresentacionTipo.get_tipo().get_id();
                    DaoTipo daoTipo = new DaoTipo();
                    Tipo tipo = daoTipo.find(idFK, Tipo.class);
                    listaTiposProducto.add(tipo);
                }
            }

            return listaTiposProducto;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    //Obtener la o las presentaciones de un producto
    @GET
    @Path("/productoPresentacionLista/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Presentacion> listarPresentacionesProducto(@PathParam("id") long id) throws NullPointerException {

        DaoProductoPresentacionTipo dao = new DaoProductoPresentacionTipo();
        List<ProductoPresentacionTipo> listaProductoPT = dao.findAll(ProductoPresentacionTipo.class);
        List<Presentacion> listaPresentacionProducto = new ArrayList<Presentacion>();

        try {

            for (ProductoPresentacionTipo productoPresentacionTipo : listaProductoPT) {

                if (productoPresentacionTipo.get_producto().get_id() == id) {

                    long idFK = productoPresentacionTipo.get_presentacion().get_id();
                    DaoPresentacion daoPresentacion = new DaoPresentacion();
                    Presentacion presentacion = daoPresentacion.find(idFK, Presentacion.class);
                    listaPresentacionProducto.add(presentacion);
                }
            }

            return listaPresentacionProducto;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    //Agregar el tipo y la presentacion a un producto
    @POST
    @Path("/addProductoPresentacionTipo")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public ProductoPresentacionTipoDto addProductoPresentacionTipo(ProductoPresentacionTipoDto productoPresentacionTipoDto){

        ProductoPresentacionTipoDto resultado = new ProductoPresentacionTipoDto();

        try {

            DaoProductoPresentacionTipo dao = new DaoProductoPresentacionTipo();
            ProductoPresentacionTipo productoPresentacionTipo = new ProductoPresentacionTipo();
            DaoProducto daoProducto = new DaoProducto();
            DaoPresentacion daoPresentacion = new DaoPresentacion();
            DaoTipo daoTipo = new DaoTipo();

            productoPresentacionTipo.set_estatus(productoPresentacionTipoDto.getEstatus());
            Producto producto = daoProducto.find(productoPresentacionTipoDto.getProductoDto().getId(), Producto.class);
            Presentacion presentacion = daoPresentacion.find(productoPresentacionTipoDto.getPresentacionDto().getId(), Presentacion.class);
            Tipo tipo = daoTipo.find(productoPresentacionTipoDto.getTipoDto().getId(), Tipo.class);
            productoPresentacionTipo.set_producto(producto);
            productoPresentacionTipo.set_presentacion(presentacion);
            productoPresentacionTipo.set_tipo(tipo);
            ProductoPresentacionTipo resul = dao.insert(productoPresentacionTipo);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }
        return resultado;
    }

    //Actualizar el estatus de la tabla
    @PUT
    @Path("/updateProductoPresentacionTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateEstatusProductoPresentacionTipo(@PathParam("id") long id, ProductoPresentacionTipoDto productoPresentacionTipoDto){

           DaoProductoPresentacionTipo dao = new DaoProductoPresentacionTipo();
           ProductoPresentacionTipo productoPresentacionTipo_modificar = dao.find(id, ProductoPresentacionTipo.class);

           if (productoPresentacionTipo_modificar == null){

               return Response.status(Response.Status.NOT_FOUND).build();
           }

           try {

               productoPresentacionTipo_modificar.set_estatus(productoPresentacionTipoDto.getEstatus());
               dao.update(productoPresentacionTipo_modificar);

           } catch (Exception ex){

               return Response.status(Response.Status.EXPECTATION_FAILED).build();
           }

        return Response.ok().entity(productoPresentacionTipo_modificar).build();
    }

    //Eliminar la relacion
    @DELETE
    @Path("/deleteProductoPresentacionTipo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProductoPresentacionTipo(@PathParam("id") long id){

        DaoProductoPresentacionTipo dao = new DaoProductoPresentacionTipo();
        ProductoPresentacionTipo productoPresentacionTipo_eliminar = dao.find(id, ProductoPresentacionTipo.class);

        if (productoPresentacionTipo_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {

            dao.delete(productoPresentacionTipo_eliminar);

        } catch (Exception ex){

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

        return Response.ok().entity(productoPresentacionTipo_eliminar).build();
    }
}
