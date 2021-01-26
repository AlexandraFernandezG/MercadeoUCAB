package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoMarca;
import ucab.dsw.dtos.MarcaDto;
import ucab.dsw.entidades.Marca;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path( "/marca" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MarcaServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas las marcas.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de Marcas y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allMarca")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarMarcas() {
        DaoMarca daoMarca = new DaoMarca();
        JsonObject dataObject;

        try {
            List<Marca> listaMarcas = daoMarca.findAll(Marca.class);
            return Response.status(Response.Status.OK).entity(listaMarcas).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una marca.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la marca consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la marca que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarMarca/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarMarca(@PathParam("id") long id) {
        DaoMarca daoMarca = new DaoMarca();
        JsonObject dataObject;

        try {
            Marca marca_consultada = daoMarca.find(id, Marca.class);
            return Response.status(Response.Status.OK).entity(marca_consultada).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la marca: " + ex.getMessage())
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
     * Este método permite obtener todas las marcas activas.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con las marcas activas y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando no se encuentra ninguna marca activa
     *
     */
    @GET
    @Path("/mostrarMarcasActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response marcasActivas() {

        DaoMarca daoMarca = new DaoMarca();
        JsonObject dataObject;
        List<Marca> listaMarca = daoMarca.findAll(Marca.class);
        List<Marca> listaMarcaActivas = new ArrayList<Marca>();

        try {

            for (Marca marca : listaMarca) {

                if (marca.get_estatus().equals("Activo")) {
                    listaMarcaActivas.add(marca);
                }
            }

            return Response.status(Response.Status.OK).entity(listaMarca).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado ninguna marca activa: " + ex.getMessage())
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
     * Este método permite insertar una marca
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la marca insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando ha fallado la inserción
     * @throws PersistenceException si se inserta una marca duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param marcaDto el objeto de tipo marca que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addMarca")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addMarca(MarcaDto marcaDto){

        MarcaDto resultado = new MarcaDto();
        JsonObject dataObject;

        try {

            DaoMarca daoMarca = new DaoMarca();
            Marca marca = new Marca();

            marca.set_nombre(marcaDto.getNombre());
            marca.set_estatus(marcaDto.getEstatus());
            marca.set_descripcion(marcaDto.getDescripcion());
            daoMarca.insert(marca);
            Marca resul = daoMarca.insert(marca);
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
                    .add("excepcion", "No se ha encontrado la marca: " + ex.getMessage())
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
     * Este método permite modificar una marca
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la marca modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una marca duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param marcaDto el objeto categoria que el sistema desea modificar.
     * @param id el id de la categoria a modificar
     */
    @PUT
    @Path("/updateMarca/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateMarca(@PathParam("id") long id, MarcaDto marcaDto){

        DaoMarca daoMarca = new DaoMarca();
        Marca marca_modificar = daoMarca.find(id, Marca.class);
        JsonObject dataObject;

            try {

                marca_modificar.set_nombre(marcaDto.getNombre());
                marca_modificar.set_descripcion(marcaDto.getDescripcion());
                marca_modificar.set_estatus(marcaDto.getEstatus());
                daoMarca.update(marca_modificar);
                return Response.status(Response.Status.OK).entity(marca_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la marca: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar una marca
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la marca a eliminar
     */
    @DELETE
    @Path("/deleteMarca/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteMarca(@PathParam("id") long id){

        DaoMarca daoMarca = new DaoMarca();
        Marca marca_eliminar = daoMarca.find(id, Marca.class);
        JsonObject dataObject;

            try {
                daoMarca.delete(marca_eliminar);
                return Response.status(Response.Status.OK).entity(marca_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la marca: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

        }
}
