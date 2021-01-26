package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoOcupacion;
import ucab.dsw.dtos.OcupacionDto;
import ucab.dsw.entidades.Ocupacion;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/ocupacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OcupacionServicio extends AplicacionBase {

    /**
     * Este método permite obtener todas las ocupaciones.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de ocupaciones y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allOcupacion")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarOcupacion() {
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        JsonObject dataObject;

        try {
            List<Ocupacion> listaOcupaciones = daoOcupacion.findAll(Ocupacion.class);
            return Response.status(Response.Status.OK).entity(listaOcupaciones).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una ocupación.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la ocupación consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la ocupación que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarOcupacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarOcupacion(@PathParam("id") long id) {
        DaoOcupacion daoOcupacion = new DaoOcupacion();
        JsonObject dataObject;

        try {
            Ocupacion ocupacion_consultada = daoOcupacion.find(id, Ocupacion.class);
            return Response.status(Response.Status.OK).entity(ocupacion_consultada).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la ocupación: " + ex.getMessage())
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
     * Este método permite insertar una ocupación
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la ocupación insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando ha fallado la inserción
     * @throws PersistenceException si se inserta una ocupación duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param ocupacionDto el objeto de tipo ocupación que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addOcupacion")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addOcupacion(OcupacionDto ocupacionDto){

        OcupacionDto resultado = new OcupacionDto();
        JsonObject dataObject;

        try {

            DaoOcupacion daoOcupacion = new DaoOcupacion();
            Ocupacion ocupacion = new Ocupacion();

            ocupacion.set_nombre(ocupacionDto.getNombre());
            ocupacion.set_estatus(ocupacionDto.getEstatus());
            Ocupacion resul = daoOcupacion.insert(ocupacion);
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
                    .add("excepcion", "No se ha encontrado la ocupación: " + ex.getMessage())
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
     * Este método permite modificar una ocupación
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la ocupación modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param  ocupacionDto el objeto categoria que el sistema desea modificar.
     * @param id el id de la ocupación a modificar
     */
    @PUT
    @Path("/updateOcupacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateOcupacion(@PathParam("id") long id, OcupacionDto ocupacionDto){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion_modificar = daoOcupacion.find(id, Ocupacion.class);
        JsonObject dataObject;

            try {

                ocupacion_modificar.set_nombre(ocupacionDto.getNombre());
                ocupacion_modificar.set_estatus(ocupacionDto.getEstatus());
                daoOcupacion.update(ocupacion_modificar);

                return Response.status(Response.Status.OK).entity(ocupacion_modificar).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la ocupación: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar una ocupación
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la ocupación a eliminar
     */
    @DELETE
    @Path("/deleteOcupacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarOcupacion(@PathParam("id") long id){

        DaoOcupacion daoOcupacion = new DaoOcupacion();
        Ocupacion ocupacion_eliminar = daoOcupacion.find(id, Ocupacion.class);
        JsonObject dataObject;

            try {

                daoOcupacion.delete(ocupacion_eliminar);

                return Response.status(Response.Status.OK).entity(ocupacion_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado la ocupación: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }
}
