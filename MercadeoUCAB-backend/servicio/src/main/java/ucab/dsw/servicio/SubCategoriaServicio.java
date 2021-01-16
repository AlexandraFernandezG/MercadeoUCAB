package ucab.dsw.servicio;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoCategoria;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoSubcategoria;
import ucab.dsw.dtos.SubcategoriaDto;
import ucab.dsw.entidades.Categoria;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.Subcategoria;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
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

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        JsonObject dataObject;

        try {

            List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);

            return Response.status(Response.Status.OK).entity(listaSubcategorias).build();

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

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        JsonObject dataObject;

        try {

            Subcategoria subcategoria = daoSubcategoria.find(id, Subcategoria.class);

            return Response.status(Response.Status.OK).entity(subcategoria).build();

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
     * Este método permite obtener todas las subcategorias activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de subcategorias activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSubCategoriasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response subcategoriasActivas() {

        DaoSubcategoria daoSubcategoria = new DaoSubcategoria();
        List<Subcategoria> listaSubcategorias = daoSubcategoria.findAll(Subcategoria.class);
        List<Subcategoria> listaSubCategoriasActivas = new ArrayList<Subcategoria>();
        JsonObject dataObject;

        try {

            for (Subcategoria subcategoria : listaSubcategorias) {

                if (subcategoria.get_estatus().equals("Activo")) {
                    listaSubCategoriasActivas.add(subcategoria);
                }
            }

            return Response.status(Response.Status.OK).entity(listaSubCategoriasActivas).build();

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
    public Response listarPreguntasSubcategoria(@PathParam("id") long id) throws NullPointerException{

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        List<PreguntaEncuesta> listarPreguntas = daoPreguntaEncuesta.findAll(PreguntaEncuesta.class);
        List<PreguntaEncuesta> listaPreguntasSubcategoria = new ArrayList<PreguntaEncuesta>();
        JsonObject dataObject;

        try {

            for (PreguntaEncuesta preguntaEncuesta: listarPreguntas){

                if(preguntaEncuesta.get_subcategoria().get_id() == id){

                    listaPreguntasSubcategoria.add(preguntaEncuesta);
                }
            }

            return Response.status(Response.Status.OK).entity(listaPreguntasSubcategoria).build();

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
    public Response addSubCategoria(SubcategoriaDto subcategoriaDto)
    {
        SubcategoriaDto resultado = new SubcategoriaDto();
        JsonObject dataObject;

        try {

            DaoSubcategoria dao = new DaoSubcategoria();
            Subcategoria subcategoria = new Subcategoria();
            DaoCategoria daoCategoria = new DaoCategoria();

            subcategoria.set_nombre(subcategoriaDto.getNombre());
            subcategoria.set_descripcion(subcategoriaDto.getDescripcion());
            subcategoria.set_estatus(subcategoriaDto.getEstatus());
            Categoria categoria = daoCategoria.find(subcategoriaDto.getCategoriaDto().getId(), Categoria.class);
            subcategoria.set_categoria(categoria);
            Subcategoria resul = dao.insert(subcategoria);
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
                    .add("excepcion", "No se ha encontrado la subcategoria: " + ex.getMessage())
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
    public Response modificarSubCategoria(@PathParam("id") long id, SubcategoriaDto subcategoriaDto){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_modificar = dao.find(id, Subcategoria.class);
        JsonObject dataObject;

            try {

                subcategoria_modificar.set_nombre(subcategoriaDto.getNombre());
                subcategoria_modificar.set_descripcion(subcategoriaDto.getDescripcion());
                subcategoria_modificar.set_estatus(subcategoriaDto.getEstatus());
                dao.update(subcategoria_modificar);

                return Response.status(Response.Status.OK).entity(subcategoria_modificar).build();

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
    public Response eliminarSubCategoria(@PathParam("id") long id){

        DaoSubcategoria dao = new DaoSubcategoria();
        Subcategoria subcategoria_eliminar = dao.find(id, Subcategoria.class);
        JsonObject dataObject;

                try {

                    dao.delete(subcategoria_eliminar);
                    return Response.status(Response.Status.OK).entity(subcategoria_eliminar).build();

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

                }

    }
}


