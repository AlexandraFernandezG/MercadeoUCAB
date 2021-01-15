package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.ProductoDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.response.EstudiosResponse;
import ucab.dsw.response.ProductoResponse;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/producto" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ProductoServicio extends AplicacionBase{

    /**
     * Este método permite obtener todos los productos.
     * @author Emanuel Di Cristofaro
     * @return Este método retorna un objeto de tipo Json con el
     * arreglo de productos y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allProductos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarProductos() {

        JsonObject dataObject;
        DaoProducto daoProducto = new DaoProducto();

        try {

            List<Producto> listaProductos = daoProducto.findAll(Producto.class);

            return Response.status(Response.Status.OK).entity(listaProductos).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

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
        DaoProducto daoProducto = new DaoProducto();

        try {

            List<Object[]> listaProductos = daoProducto.listarProductosCliente(id);

            List<ProductoResponse> listaProductosCliente = new ArrayList<>(listaProductos.size());

            for (Object[] pc: listaProductos){

                listaProductosCliente.add(new ProductoResponse((long)pc[0], (String)pc[1], (String)pc[2], (String)pc[3]));
            }

            return Response.status(Response.Status.OK).entity(listaProductosCliente).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener un producto.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el  producto consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del producto que se quiere consultar
     *
     */
    @GET
    @Path("/consultarProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarProducto(@PathParam("id") long id) {

        JsonObject dataObject;
        DaoProducto daoProducto = new DaoProducto();

        try {

            Producto producto_consultado = daoProducto.find(id, Producto.class);

            return Response.status(Response.Status.OK).entity(producto_consultado).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el producto: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener todos los productos activos.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de productos activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarProductosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response productosActivos() {

        DaoProducto daoProducto = new DaoProducto();
        List<Producto> listaProducto = daoProducto.findAll(Producto.class);
        List<Producto> listaProductosActivos = new ArrayList<Producto>();
        JsonObject dataObject;

        try {

            for (Producto producto : listaProducto) {

                if (producto.get_estatus().equals("Activo")) {
                    listaProductosActivos.add(producto);
                }
            }

            return Response.status(Response.Status.OK).entity(listaProductosActivos).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite insertar un producto
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el producto insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param productoDto el objeto producto que el sistema desea insertar o crear.
     *
     */
    @POST
    @Path("/addProducto")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addProducto(ProductoDto productoDto){

        JsonObject dataObject;
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

            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el producto: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite modificar un producto
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el producto modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param productoDto el objeto producto que el sistema desea modificar.
     * @param id el id del producto que se desee modificar.
     */
    @PUT
    @Path("/updateProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarProducto(@PathParam("id") long id, ProductoDto productoDto){

        JsonObject dataObject;
        DaoProducto daoProducto = new DaoProducto();

            try {

                Producto producto_modificar = daoProducto.find(id, Producto.class);

                producto_modificar.set_nombre(productoDto.getNombre());
                producto_modificar.set_descripcion(productoDto.getDescripcion());
                producto_modificar.set_estatus(productoDto.getEstatus());
                daoProducto.update(producto_modificar);

                return Response.status(Response.Status.OK).entity(producto_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el producto: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar un producto.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del producto que se desee eliminar.
     */
    @DELETE
    @Path("/deleteProducto/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteProducto(@PathParam("id") long id){

        JsonObject dataObject;
        DaoProducto daoProducto = new DaoProducto();

            try {

                Producto producto_eliminar = daoProducto.find(id, Producto.class);
                daoProducto.delete(producto_eliminar);

                return Response.status(Response.Status.OK).entity(producto_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el producto: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }
}
