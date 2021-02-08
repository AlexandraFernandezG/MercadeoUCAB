package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoTelefono;
import ucab.dsw.comando.Telefono.AddTelefonoComando;
import ucab.dsw.dtos.TelefonoDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.Telefono;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperTelefono;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/telefono" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class TelefonoServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas los telefonos.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de telefonos y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allTelefono")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarTelefonos() {

        DaoTelefono daoTelefono = new DaoTelefono();
        JsonObject dataObject;

        try {

            List<Telefono> listaTelefonos = daoTelefono.findAll(Telefono.class);

            return Response.status(Response.Status.OK).entity(listaTelefonos).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener un telefono.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el telefono consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del telefono que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarTelefono/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarTelefono(@PathParam("id") long id) {

        DaoTelefono daoTelefono = new DaoTelefono();
        JsonObject dataObject;

        try {

            Telefono telefono = daoTelefono.find(id, Telefono.class);

            return Response.status(Response.Status.OK).entity(telefono).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el telofono: " + ex.getMessage())
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
     * Este método permite insertar un telefono
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con e telefono insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un telefono duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param telefonoDto el objeto telefono que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addTelefono")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addTelefono(TelefonoDto telefonoDto) throws Exception {

        JsonObject dataObject;

        try {

            Telefono telefono = MapperTelefono.mapDtoToEntityInsert(telefonoDto);
            AddTelefonoComando comando = Fabrica.crearComandoConEntity(AddTelefonoComando.class, telefono);
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

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

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
     * Este método permite modificar un telefono
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el telofono modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una telofono duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param telefonoDto el objeto telefono que el sistema desea modificar.
     * @param id el id del telefono a modificar
     */
    @PUT
    @Path("/updateTelefono/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateTelefono(@PathParam("id") long id, TelefonoDto telefonoDto){

        DaoTelefono daoTelefono = new DaoTelefono();
        Telefono telefono_modificar = daoTelefono.find(id, Telefono.class);
        JsonObject dataObject;

        try {
            telefono_modificar.set_numero(telefonoDto.getNumero());
            telefono_modificar.set_estatus(telefonoDto.getEstatus());
    
            daoTelefono.update(telefono_modificar);

            return Response.status(Response.Status.OK).entity(telefono_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el telefono: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }

    /**
     * Este método permite eliminar un telefono
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un telefono duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del telefono a eliminar
     */
    @DELETE
    @Path("/deleteTelefono/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarTelefono(@PathParam("id") long id){

        DaoTelefono daoTelefono = new DaoTelefono();
        Telefono telefono_eliminar = daoTelefono.find(id, Telefono.class);
        JsonObject dataObject;

            try {

                daoTelefono.delete(telefono_eliminar);

                return Response.status(Response.Status.OK).entity(telefono_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el telefono: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

}
