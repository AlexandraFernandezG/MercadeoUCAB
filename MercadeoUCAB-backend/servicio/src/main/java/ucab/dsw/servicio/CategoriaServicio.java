package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.Producto;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/categoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class CategoriaServicio extends AplicacionBase {

    /**
     * Este método permite obtener todas las categorias.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de categorias y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allCategoria")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarCategorias() {

        JsonObject dataObject;
        JsonArrayBuilder categoriasArrayJson = Json.createArrayBuilder();
        DaoCategoria categoriaDao = new DaoCategoria();

        try {

            List<Categoria> listaCategorias = categoriaDao.findAll(Categoria.class);

            for(Categoria ca: listaCategorias){

                JsonObject categoria = Json.createObjectBuilder()
                        .add("id", ca.get_id())
                        .add("nombre", ca.get_nombre())
                        .add("descripcion", ca.get_descripcion())
                        .add("estatus", ca.get_estatus()).build();

                categoriasArrayJson.add(categoria);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operación realizada con éxito")
                    .add("codigo", 200)
                    .add("Todas las categorias", categoriasArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una categoria.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la categoria consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la categoria que se quiere consultar.
     *
     */
    @GET
    @Path ("/consultarCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarCategoria(@PathParam("id") long id) throws NullPointerException {

        JsonObject dataObject;
        DaoCategoria categoriaDao = new DaoCategoria();

        try {

            Categoria categoria_consultada = categoriaDao.find(id, Categoria.class);

            JsonObject categoria = Json.createObjectBuilder()
                    .add("id", categoria_consultada.get_id())
                    .add("nombre", categoria_consultada.get_nombre())
                    .add("descripcion", categoria_consultada.get_descripcion())
                    .add("estatus", categoria_consultada.get_estatus()).build();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operación realizada con éxito")
                    .add("codigo", 200)
                    .add("Categoria consultada", categoria).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
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
     * Este método permite obtener todas las categorias activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de categorias activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path ("/mostrarCategoriasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response categoriasActivas()  {

        DaoCategoria daoCategoria = new DaoCategoria();
        List<Categoria> listaCategorias = daoCategoria.findAll(Categoria.class);
        List<Categoria> listaCategoriasActivas = new ArrayList<Categoria>();
        JsonObject dataObject;
        JsonArrayBuilder categoriasArrayJson = Json.createArrayBuilder();

        try {

            for (Categoria categoria : listaCategorias) {

                if (categoria.get_estatus().equals("Activo")) {
                    listaCategoriasActivas.add(categoria);
                }
            }

            for(Categoria ca: listaCategorias){

                JsonObject categoria = Json.createObjectBuilder()
                        .add("id", ca.get_id())
                        .add("nombre", ca.get_nombre())
                        .add("descripcion", ca.get_descripcion())
                        .add("estatus", ca.get_estatus()).build();

                categoriasArrayJson.add(categoria);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operación realizada con éxito")
                    .add("codigo", 200)
                    .add("Todas las categorias activas", categoriasArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener todas las subcategorias de una categoria.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de subcategorias y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSubcategoriasCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarSubcategoriasDeCategoria(@PathParam("id") long id) throws NullPointerException{

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubcategoriasCategoria = new ArrayList<Subcategoria>();
        JsonObject dataObject;
        JsonArrayBuilder subcategoriasArrayJson = Json.createArrayBuilder();

        try {

            for (Subcategoria subcategoria : listaSubcategorias) {

                if (subcategoria.get_categoria().get_id() == id) {
                    listaSubcategoriasCategoria.add(subcategoria);
                }
            }

            for(Subcategoria sub: listaSubcategorias){

                JsonObject subcategoria = Json.createObjectBuilder()
                        .add("id", sub.get_id())
                        .add("nombre", sub.get_nombre())
                        .add("descripcion", sub.get_descripcion())
                        .add("estatus", sub.get_estatus()).build();

                subcategoriasArrayJson.add(subcategoria);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operación realizada con éxito")
                    .add("codigo", 200)
                    .add("Todas las subcategorias", subcategoriasArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite insertar una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la categoria insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param categoriaDto el objeto categoria que el sistema desea insertar o crear.
     *
     */
    @POST
    @Path("/addCategoria")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addCategoria(CategoriaDto categoriaDto)
    {

        JsonObject dataObject;
        CategoriaDto resultado = new CategoriaDto();

        try {

            DaoCategoria dao = new DaoCategoria();
            Categoria categoria = new Categoria();

            categoria.set_nombre(categoriaDto.getNombre());
            categoria.set_descripcion(categoriaDto.getDescripcion());
            categoria.set_estatus(categoriaDto.getEstatus());
            Categoria resul = dao.insert(categoria);
            resultado.setId(resul.get_id());

            JsonObject categoria_insertada = Json.createObjectBuilder()
                    .add("nombre", categoriaDto.getNombre())
                    .add("descripcion", categoriaDto.getDescripcion())
                    .add("estatus", categoriaDto.getEstatus()).build();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operación realizada con éxito")
                    .add("codigo", 200)
                    .add("Categoria insertada", categoria_insertada).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje",ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
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
     * Este método permite modificar el estatus una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la categoria modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param categoriaDto el objeto categoria que el sistema desea modificar.
     * @param id el id de la categoria a modificar
     */
    @PUT
    @Path("/estatusCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusCategoria(@PathParam("id") long id, CategoriaDto categoriaDto){

        JsonObject dataObject;
        DaoCategoria daoCategoria = new DaoCategoria();

            try {

                Categoria categoria_modificar = daoCategoria.find(id, Categoria.class);
                categoria_modificar.set_estatus(categoriaDto.getEstatus());
                daoCategoria.update(categoria_modificar);
                DaoSubcategoria daoSubcategoria = new DaoSubcategoria();

                if (categoria_modificar.get_estatus().equals("Inactivo")) {

                    List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                    for (Subcategoria subcategoria : listaSubcategorias) {

                        if (subcategoria.get_categoria().get_id() == id) {
                            subcategoria.set_estatus("Inactivo");
                            daoSubcategoria.update(subcategoria);
                        }
                    }
                } else if (categoria_modificar.get_estatus().equals("Activo")) {

                    List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

                    for (Subcategoria subcategoria : listaSubcategorias) {

                        if (subcategoria.get_categoria().get_id() == id) {
                            subcategoria.set_estatus("Activo");
                            daoSubcategoria.update(subcategoria);
                        }
                    }
                }

                JsonObject categoria_modificada = Json.createObjectBuilder()
                        .add("nombre", categoriaDto.getNombre())
                        .add("descripcion", categoriaDto.getDescripcion())
                        .add("estatus", categoriaDto.getEstatus()).build();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Operación realizada con éxito")
                        .add("codigo", 200)
                        .add("Categoria insertada", categoria_modificada).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje",ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }

    /**
     * Este método permite modificar una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la categoria modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param categoriaDto el objeto categoria que el sistema desea modificar.
     * @param id el id de la categoria a modificar
     */
    @PUT
    @Path("/updateCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarCategoria(@PathParam("id") long id, CategoriaDto categoriaDto){

        JsonObject dataObject;
        DaoCategoria dao = new DaoCategoria();

            try {

                Categoria categoria_modificar = dao.find(id, Categoria.class);
                categoria_modificar.set_nombre(categoriaDto.getNombre());
                categoria_modificar.set_descripcion(categoriaDto.getDescripcion());
                categoria_modificar.set_estatus(categoriaDto.getEstatus());
                dao.update(categoria_modificar);

                JsonObject categoria_modificada = Json.createObjectBuilder()
                        .add("nombre", categoriaDto.getNombre())
                        .add("descripcion", categoriaDto.getDescripcion())
                        .add("estatus", categoriaDto.getEstatus()).build();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Operación realizada con éxito")
                        .add("codigo", 200)
                        .add("Categoria insertada", categoria_modificada).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje",ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }

    /**
     * Este método permite eliminar una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la categoria a eliminar
     */
    @DELETE
    @Path("/deleteCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarCategoria(@PathParam("id") long id){

        JsonObject dataObject;
        DaoCategoria dao = new DaoCategoria();

            try {

                Categoria categoria_eliminar = dao.find(id, Categoria.class);
                dao.delete(categoria_eliminar);

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Operación realizada con éxito")
                        .add("codigo", 200).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje",ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }
}
   
