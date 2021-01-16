package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoRol;
import ucab.dsw.dtos.RolDto;
import ucab.dsw.entidades.Rol;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/rol" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RolServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas los roles.
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de roles y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allRoles")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarRoles()  {

        DaoRol daoRol = new DaoRol();
        JsonObject dataObject;

        try {

            List<Rol> listaRoles = daoRol.findAll(Rol.class);

            return Response.status(Response.Status.OK).entity(listaRoles).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener un rol.
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el rol consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del rol que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarRol/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarRol(@PathParam("id") long id){

        DaoRol daoRol = new DaoRol();
        JsonObject dataObject;

        try {

            Rol rol = daoRol.find(id, Rol.class);

            return Response.status(Response.Status.OK).entity(rol).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el rol: " + ex.getMessage())
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
     * Este método permite insertar un rol
     * @author Gregg Spinetti y Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con un rol insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un rol duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param rolDto el objeto rol que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addRol")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addRol(RolDto rolDto)
    {
        RolDto resultado = new RolDto();
        JsonObject dataObject;

        try {

            DaoRol dao = new DaoRol();
            Rol rol = new Rol();

            rol.set_nombre(rolDto.getNombre());
            rol.set_estatus(rol.get_estatus());
            Rol resul = dao.insert(rol);
            resultado.setId(resul.get_id());

            return Response.status(Response.Status.OK).entity(resultado).build();
        }
        catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el rol: " + ex.getMessage())
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
     * Este método permite modificar el rol
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el rol modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un rol duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param rolDto el objeto rol que el sistema desea modificar.
     * @param id el id del rol a modificar
     */
    @PUT
    @Path("/updateRol/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateRol(@PathParam("id") long id, RolDto rolDto){

        DaoRol daoRol = new DaoRol();
        Rol rol_modificar = daoRol.find(id, Rol.class);
        JsonObject dataObject;

        try {

            rol_modificar.set_nombre(rolDto.getNombre());
            rol_modificar.set_estatus(rolDto.getEstatus());
            rolDto.setEstatus(rolDto.getEstatus());
            daoRol.update(rol_modificar);

            return Response.status(Response.Status.OK).entity(rol_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el rol: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }

    /**
     * Este método permite eliminar un rol
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un rol duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del rol a eliminar
     */
    @DELETE
    @Path("/deleteRol/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteRol(@PathParam("id") long id){

        DaoRol daoRol = new DaoRol();
        Rol rol_eliminar = daoRol.find(id, Rol.class);
        JsonObject dataObject;

        try {

            daoRol.delete(rol_eliminar);

            return Response.status(Response.Status.OK).entity(rol_eliminar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el rol: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }
}