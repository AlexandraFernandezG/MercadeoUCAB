package ucab.dsw.servicio;

import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.response.EstudiosResponse;
import ucab.dsw.response.ProductoResponse;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/producto" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoServicio extends AplicacionBase{

    //Listar productos
    @GET
    @Path("/allProductos")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Producto> listarProductos() throws NullPointerException{

        DaoProducto daoProducto = new DaoProducto();

        try {
            return daoProducto.findAll(Producto.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    /**
     * Este método permite obtener los productos de un cliente.
     * @author Emanuel Di Cristofaro
     * @param id El id del cliente.
     */
    @GET
    @Path("/consultarProductosCliente/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarProductosCliente(@PathParam("id") long id){

        JsonObject dataObject;
        JsonArrayBuilder productosArrayJson = Json.createArrayBuilder();
        DaoProducto daoProducto = new DaoProducto();

        try {

            List<Object[]> listaProductos = daoProducto.listarProductosCliente(id);

            List<ProductoResponse> listaProductosCliente = new ArrayList<>(listaProductos.size());

            for (Object[] pc: listaProductos){

                listaProductosCliente.add(new ProductoResponse((long)pc[0], (String)pc[1], (String)pc[2], (String)pc[3]));
            }

            for (ProductoResponse lpc: listaProductosCliente){

                JsonObject producto = Json.createObjectBuilder()
                        .add("id", lpc.getIdProducto())
                        .add("nombre", lpc.getNombreProducto())
                        .add("descripcion", lpc.getDescripcionProducto())
                        .add("estatus", lpc.getEstatusProducto()).build();

                productosArrayJson.add(producto);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operacion realizada con éxito")
                    .add("codigo", 200)
                    .add("Productos del cliente", productosArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    //Consultar un producto
    @GET
    @Path("/consultarProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Producto consultarProducto(@PathParam("id") long id) throws NullPointerException{

        DaoProducto daoProducto = new DaoProducto();

        try {
            return daoProducto.find(id, Producto.class);

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Mostrar productos activos
    @GET
    @Path("/mostrarProductosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Producto> productosActivos() throws NullPointerException{

        DaoProducto daoProducto = new DaoProducto();
        List<Producto> listaProducto = daoProducto.findAll(Producto.class);
        List<Producto> listaProductosActivos = new ArrayList<Producto>();

        try {

            for (Producto producto : listaProducto) {

                if (producto.get_estatus().equals("Activo")) {
                    listaProductosActivos.add(producto);
                }
            }
            return listaProductosActivos;

        } catch (NullPointerException ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

    //Agregar un producto
    @POST
    @Path("/addProducto")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public ProductoDto addProducto(ProductoDto productoDto){

        ProductoDto resultado = new ProductoDto();

        try {

            DaoProducto daoProducto = new DaoProducto();
            Producto producto = new Producto();
            DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
            DaoMarca daoMarca = new DaoMarca();

            producto.set_nombre(productoDto.getNombre());
            producto.set_descripcion(productoDto.getDescripcion());
            producto.set_estatus(productoDto.getEstatus());
            Usuario usuario = new Usuario(productoDto.getUsuarioDto().getId());
            Subcategoria subcategoria = daoSubcategoria.find(productoDto.getSubcategoriaDto().getId(),Subcategoria.class);
            Marca marca = daoMarca.find(productoDto.getMarcaDto().getId(),Marca.class);
            producto.set_usuario(usuario);
            producto.set_subcategoria(subcategoria);
            producto.set_marca(marca);
            Producto resul = daoProducto.insert(producto);
            resultado.setId(resul.get_id());

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

            return resultado;
    }

    //Actualizar Producto
    @PUT
    @Path("/updateProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarProducto(@PathParam("id") long id, ProductoDto productoDto){

        DaoProducto daoProducto = new DaoProducto();
        Producto producto_modificar = daoProducto.find(id, Producto.class);

        if (producto_modificar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {

                producto_modificar.set_nombre(productoDto.getNombre());
                producto_modificar.set_descripcion(productoDto.getDescripcion());
                producto_modificar.set_estatus(productoDto.getEstatus());
                daoProducto.update(producto_modificar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(producto_modificar).build();

    }

    // Eliminar un producto
    @DELETE
    @Path("/deleteProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProducto(@PathParam("id") long id){

        DaoProducto daoProducto = new DaoProducto();
        Producto producto_eliminar = daoProducto.find(id, Producto.class);

        if(producto_eliminar == null){

            return Response.status(Response.Status.NOT_FOUND).build();
        }

            try {

                daoProducto.delete(producto_eliminar);

            } catch (Exception ex){

                return Response.status(Response.Status.EXPECTATION_FAILED).build();
            }

            return Response.ok().entity(producto_eliminar).build();

    }
}
