package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.comando.Categoria.*;
import ucab.dsw.dtos.CategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperCategoria;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

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

        try {

            ListarCategoriasComando comando = Fabrica.crear(ListarCategoriasComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
    public Response consultarCategoria(@PathParam("id") long id) {

        JsonObject dataObject;

        try {

            ConsultarCategoriaComando comando = Fabrica.crearComandoConId(ConsultarCategoriaComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

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


        JsonObject dataObject;

        try {

            MostrarCategoriasActivasComando comando = Fabrica.crear(MostrarCategoriasActivasComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
    public Response listarSubcategoriasDeCategoria(@PathParam("id") long id) {

        JsonObject dataObject;

        try {

            MostrarSubcategoriasCategoriasComando comando = Fabrica.crearComandoConId(MostrarSubcategoriasCategoriasComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
     * @throws PersistenceException si se inserta una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param categoriaDto el objeto categoria que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addCategoria")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addCategoria(CategoriaDto categoriaDto)
    {

        JsonObject dataObject;

        try {

            Categoria categoria = MapperCategoria.mapDtoToEntityInsert(categoriaDto);
            AddCategoriaComando comando = Fabrica.crearComandoConEntity(AddCategoriaComando.class, categoria);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException ex) {

            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 600).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InstantiationException ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 601).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InvocationTargetException ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 602).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
        }
    }

    /**
     * Este método permite modificar el estatus una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la categoria modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una categoria duplicada.
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

            try {

                Categoria categoria = MapperCategoria.mapDtoToEntityUpdate(id, categoriaDto);
                ModificarEstatusCategoriaComando comando = Fabrica.crearComandoBoth(ModificarEstatusCategoriaComando.class, id, categoria);
                comando.execute();

                return Response.status(Response.Status.OK).entity(comando.getResult()).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException | IllegalAccessException | InvocationTargetException | InstantiationException | PruebaExcepcion ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 700).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            }
    }

    /**
     * Este método permite modificar una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la categoria modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una categoria duplicado.
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

            try {

                Categoria categoria = MapperCategoria.mapDtoToEntityUpdate(id, categoriaDto);
                ModificarCategoriaComando comando = Fabrica.crearComandoBoth(ModificarCategoriaComando.class, id, categoria);
                comando.execute();

                return Response.status(Response.Status.OK).entity(comando.getResult()).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (IllegalAccessException | PruebaExcepcion ex) {

                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 604).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InstantiationException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 602).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InvocationTargetException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 603).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
            }
    }

    /**
     * Este método permite eliminar una categoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la categoria a eliminar
     */
    @DELETE
    @Path("/deleteCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarCategoria(@PathParam("id") long id){

        JsonObject dataObject;

            try {

                EliminarCategoriaComando comando = Fabrica.crearComandoConId(EliminarCategoriaComando.class, id);
                comando.execute();

                return Response.status(Response.Status.OK).entity(comando.getResult()).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
                        .add("codigo", 401).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (IllegalAccessException | PruebaExcepcion ex) {

                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 604).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InstantiationException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 602).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InvocationTargetException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 603).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
            }

    }
}
   
