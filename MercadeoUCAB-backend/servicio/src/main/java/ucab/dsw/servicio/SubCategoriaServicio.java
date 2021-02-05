package ucab.dsw.servicio;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.comando.Subcategoria.*;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperSubcategoria;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/subcategoria" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SubCategoriaServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas las subcategorias.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de subcategorias y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allSubcategoria")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarSubCategorias() {

        JsonObject dataObject;

        try {

            ListarSubCategoriasComando comando = Fabrica.crear(ListarSubCategoriasComando.class);
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
     * Este método permite obtener una subcategoria.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la subcategoria consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la subcategoria que se quiere consultar.
     *
     */
    @GET
    @Path ("/consultarSubCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarSubCategoria(@PathParam("id") long id) {


        JsonObject dataObject;

        try {

            ConsultarSubCategoriaComando comando = Fabrica.crearComandoConId(ConsultarSubCategoriaComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion",  ex.getMessage())
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
     * Este método permite obtener todas las subcategorias activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de subcategorias activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSubCategoriasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response subcategoriasActivas() {

        JsonObject dataObject;

        try {

            MostrarSubCategoriasActivasComando comando = Fabrica.crear(MostrarSubCategoriasActivasComando.class);
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
     * Este método permite obtener una las preguntas de una subcategorias.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la subcategoria consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la subcategoria que se quiere consultar.
     *
     */
    @GET
    @Path("/listarPreguntasSubcategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPreguntasSubcategoria(@PathParam("id") long id) {

        JsonObject dataObject;

        try {

            ListarPreguntasSubCategoriaComando comando = Fabrica.crearComandoConId(ListarPreguntasSubCategoriaComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la subcategoria: " + ex.getMessage())
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
     * Este método permite insertar una subcategoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la subcategoria insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una subcategoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param subcategoriaDto el objeto subcategoria que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addSubCategoria")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addSubCategoria(SubcategoriaDto subcategoriaDto) throws Exception {

        JsonObject dataObject;

        try {

            Subcategoria subcategoria = MapperSubcategoria.mapDtoToEntityInsert(subcategoriaDto);
            AddSubCategoriaComando comando = Fabrica.crearComandoConEntity(AddSubCategoriaComando.class, subcategoria);
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
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

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
                    .add("codigo", 600).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InvocationTargetException ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 600).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
        }
    }

    /**
     * Este método permite modificar el estatus una subcategoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la subcategoria modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param subcategoriaDto el objeto subcategoria que el sistema desea modificar.
     * @param id el id de la subcategoria a modificar
     */
    @PUT
    @Path("/updateSubCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarSubCategoria(@PathParam("id") long id, SubcategoriaDto subcategoriaDto) throws Exception {

        JsonObject dataObject;

            try {

                Subcategoria subcategoria = MapperSubcategoria.mapDtoToEntityUpdate(id, subcategoriaDto);
                ModificarSubCategoriaComando comando = Fabrica.crearComandoBoth(ModificarSubCategoriaComando.class, id, subcategoria);
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
                        .add("codigo", 400).build();

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
                        .add("codigo", 600).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InvocationTargetException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 600).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
            }

    }

    /**
     * Este método permite eliminar una subcategoria
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un producto duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la subcategoria a eliminar
     */
    @DELETE
    @Path("/deleteSubCategoria/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarSubCategoria(@PathParam("id") long id) throws Exception {

        JsonObject dataObject;

                try {

                    EliminarSubCategoriaComando comando = Fabrica.crearComandoConId(EliminarSubCategoriaComando.class, id);
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
                            .add("excepcion", "No se ha encontrado la subcategoria: " + ex.getMessage())
                            .add("codigo", 400).build();

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
                            .add("codigo", 600).build();

                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();

                    dataObject = Json.createObjectBuilder()
                            .add("estado", "Error")
                            .add("excepcion", ex.getMessage())
                            .add("codigo", 600).build();

                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();
                }

    }
}


