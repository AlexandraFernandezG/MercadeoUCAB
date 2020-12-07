package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.*;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/producto" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoAPI extends AplicacionBase{

    @GET
    @Path("/allProductos")
    public List<Producto> listarProductos(){

        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.findAll(Producto.class);
    }

    @GET
    @Path("/consultarProducto/{id}")
    public Producto consultarProducto(@PathParam("id") long id){

        DaoProducto daoProducto = new DaoProducto();
        return daoProducto.find(id, Producto.class);
    }

    @GET
    @Path("/mostrarProductosActivos")
    public List<Producto> productosActivos(){

        DaoProducto daoProducto = new DaoProducto();
        List<Producto> listaProducto = daoProducto.findAll(Producto.class);
        List<Producto> listaProductosActivos = new ArrayList<Producto>();

        for (Producto producto: listaProducto){

            if(producto.get_estatus().equals("Activo")){
                listaProductosActivos.add(producto);
            }
        }
        return listaProductosActivos;
    }

    @POST
    @Path("/addProducto")
    public Producto addProducto(ProductoDto productoDto){

        DaoProducto daoProducto = new DaoProducto();
        Producto producto = new Producto();

            producto.setNombre(productoDto.getNombre());
            producto.setDescripcion(productoDto.getDescripcion());
            producto.set_estatus(productoDto.get_estatus());
            Usuario usuario = new Usuario(productoDto.getUsuarioDto().getId());
            Subcategoria subcategoria = new Subcategoria(productoDto.getSubcategoriaDto().getId());
            Marca marca = new Marca(productoDto.getMarcaDto().getId());
            producto.setUsuario(usuario);
            producto.setSubcategoria(subcategoria);
            producto.setMarca(marca);
            daoProducto.insert(producto);

            return producto;
    }

    @PUT
    @Path("/updateProducto/{id}")
    public Response modificarProducto(@PathParam("id") long id, ProductoDto productoDto){

        DaoProducto daoProducto = new DaoProducto();
        Producto producto_modificar = daoProducto.find(id, Producto.class);

        if (producto_modificar != null){

            producto_modificar.setNombre(productoDto.getNombre());
            producto_modificar.setDescripcion(productoDto.getDescripcion());
            producto_modificar.set_estatus(productoDto.get_estatus());
            daoProducto.update(producto_modificar);
            DaoTipo daoTipo = new DaoTipo();
            DaoPresentacion daoPresentacion = new DaoPresentacion();

            if(producto_modificar.get_estatus() == "Inactivo"){

                List<Tipo> listaTipo = daoTipo.findAll(Tipo.class);
                List<Presentacion> listaPresentacion = daoPresentacion.findAll(Presentacion.class);

                for(Tipo tipo: listaTipo){

                    if(tipo.getProducto().get_id() == id) {
                        tipo.set_estatus("Inactivo");
                        daoTipo.update(tipo);
                    }
                }

                for(Presentacion presentacion: listaPresentacion){

                    if(presentacion.getProducto().get_id() == id){
                        presentacion.set_estatus("Inactivo");
                        daoPresentacion.update(presentacion);
                    }
                }

            } else if(producto_modificar.get_estatus() == "Activo"){

                List<Tipo> listaTipo = daoTipo.findAll(Tipo.class);
                List<Presentacion> listaPresentacion = daoPresentacion.findAll(Presentacion.class);

                for(Tipo tipo: listaTipo){

                    if(tipo.getProducto().get_id() == id) {
                        tipo.set_estatus("Activo");
                        daoTipo.update(tipo);
                    }
                }

                for(Presentacion presentacion: listaPresentacion){

                    if(presentacion.getProducto().get_id() == id){
                        presentacion.set_estatus("Activo");
                        daoPresentacion.update(presentacion);
                    }
                }

            }

            return Response.ok().entity(producto_modificar).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/deleteProducto/{id}")
    public Response deleteProducto(@PathParam("id") long id){

        DaoProducto daoProducto = new DaoProducto();
        Producto producto_eliminar = daoProducto.find(id, Producto.class);

        if(producto_eliminar != null){

            DaoTipo daoTipo = new DaoTipo();
            DaoPresentacion daoPresentacion = new DaoPresentacion();
            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
            List<Tipo> listaTipo = daoTipo.findAll(Tipo.class);
            List<Presentacion> listaPresentacion = daoPresentacion.findAll(Presentacion.class);
            List<SolicitudEstudio> listaSolicitudEstudio = daoSolicitudEstudio.findAll(SolicitudEstudio.class);

            for (Tipo tipo: listaTipo){

                if(tipo.getProducto().get_id() == id){
                    daoTipo.delete(tipo);
                }
            }

            for (Presentacion presentacion: listaPresentacion){

                if(presentacion.getProducto().get_id() == id){
                    daoPresentacion.delete(presentacion);
                }
            }

            for (SolicitudEstudio solicitudEstudio: listaSolicitudEstudio){

                if(solicitudEstudio.get_producto().get_id() == id){
                    daoSolicitudEstudio.delete(solicitudEstudio);
                }
            }

            daoProducto.delete(producto_eliminar);
            return Response.ok().entity(producto_eliminar).build();
        } else {

            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
