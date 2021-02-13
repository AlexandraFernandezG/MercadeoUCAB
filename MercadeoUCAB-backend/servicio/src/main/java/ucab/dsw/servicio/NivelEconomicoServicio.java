package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoNivelEconomico;
import ucab.dsw.dtos.NivelEconomicoDto;
import ucab.dsw.entidades.NivelEconomico;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/nivelEconomico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class NivelEconomicoServicio extends AplicacionBase{
    private static Logger logger = LoggerFactory.getLogger(NivelEconomicoServicio.class);

    /**
     * Este método permite obtener todas los niveles económicos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de NivelAcademico y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allNivelEconomico")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarNivelEconomico() {
        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        JsonObject dataObject;
        try {
            List<NivelEconomico> listaNivelEconomico = daoNivelEconomico.findAll(NivelEconomico.class);
            return Response.status(Response.Status.OK).entity(listaNivelEconomico).build();
        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener un nivel económico.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el nivel económico consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del nivel económico que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarNivelEconomico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarNivelEconomico(@PathParam("id") long id) {
        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        JsonObject dataObject;

        try {
            NivelEconomico nivelEconomico_consultado = daoNivelEconomico.find(id, NivelEconomico.class);
            return Response.status(Response.Status.OK).entity(nivelEconomico_consultado).build();
        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el nivel económico: " + ex.getMessage())
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
     * Este método permite insertar un nivel económico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el nivel económico insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando ha fallado la inserción
     * @throws PersistenceException si se inserta un nivel económico duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param nivelEconomicoDto el objeto de tipo nivel económico que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addNivelEconomico")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addNivelEconomico(NivelEconomicoDto nivelEconomicoDto){

        NivelEconomicoDto resultado = new NivelEconomicoDto();
        JsonObject dataObject;

        try {

            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            NivelEconomico nivelEconomico = new NivelEconomico();

            nivelEconomico.set_descripcion(nivelEconomicoDto.getDescripcion());
            nivelEconomico.set_estatus(nivelEconomicoDto.getEstatus());
            NivelEconomico resul = daoNivelEconomico.insert(nivelEconomico);
            resultado.setId(resul.get_id());

            return Response.status(Response.Status.OK).entity(resultado).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha podido insertar el nivel económico: " + ex.getMessage())
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
     * Este método permite modificar un nivel económico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el nivel económico y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param nivelEconomicoDto el objeto nivel económico que el sistema desea modificar.
     * @param id el id del nivel económico a modificar
     */
    @PUT
    @Path("/updateNivelEconomico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateNivelEconomico(@PathParam("id") long id, NivelEconomicoDto nivelEconomicoDto){

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico_modificar = daoNivelEconomico.find(id, NivelEconomico.class);
        JsonObject dataObject;
            try {

                nivelEconomico_modificar.set_descripcion(nivelEconomicoDto.getDescripcion());
                nivelEconomico_modificar.set_estatus(nivelEconomicoDto.getEstatus());
                daoNivelEconomico.update(nivelEconomico_modificar);
                return Response.status(Response.Status.OK).entity(nivelEconomico_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el nivel económico: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }


    }

    /**
     * Este método permite eliminar un nivel económico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del nivel económico a eliminar
     */
    @DELETE
    @Path("/deleteNivelEconomico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarNivelEconomico(@PathParam("id") long id){

        DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
        NivelEconomico nivelEconomico_eliminar = daoNivelEconomico.find(id, NivelEconomico.class);
        JsonObject dataObject;

            try {
                daoNivelEconomico.delete(nivelEconomico_eliminar);
                return Response.status(Response.Status.OK).entity(nivelEconomico_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado  el nivel económico: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }
}