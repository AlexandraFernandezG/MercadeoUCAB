package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;

import ucab.dsw.accesodatos.DaoHijo;
import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.dtos.HijoDto;
import ucab.dsw.entidades.Hijo;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path( "/hijo" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class HijoServicio extends AplicacionBase{

    /**
     * Este método permite obtener todos los hijos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json
     * con los hijos consultados o una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando no se encuentra ningún hijo en el registro
     */
    @GET
    @Path("/allHijos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarHijos() throws NullPointerException {

        DaoHijo daoHijo = new DaoHijo();
        JsonObject dataObject;

        try {
            List<Hijo> listaHijos = daoHijo.findAll(Hijo.class);

            return Response.status(Response.Status.OK).entity(listaHijos).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado ningún hijo: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 404).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener un hijo dado un id.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json
     * con los hijos consultados o una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando no se encuentra ningún hijo en el registro
     */
    @GET
    @Path("/consultarHijo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarHijo(@PathParam("id") long id) throws NullPointerException {

        DaoHijo daoHijo = new DaoHijo();
        JsonObject dataObject;

        try {
            Hijo hijoConsultado = daoHijo.find(id, Hijo.class);
            return Response.status(Response.Status.OK).entity(hijoConsultado).build();

        } catch (NullPointerException ex){

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado ningún hijo: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 404).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método inserta un solo registro de Hijo, en la BD.
     *
     * @param hijoDto Hijo a insertar en la BD.
     * */
    @POST
    @Path("/addHijo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHijo(HijoDto hijoDto) {

        JsonObject dataObject;
        HijoDto resultado = new HijoDto();

        try {

            DaoHijo dao = new DaoHijo();
            Hijo hijo = new Hijo();

            hijo.set_genero(hijoDto.getGenero());
            hijo.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo.set_estatus(hijoDto.getEstatus());

            DaoInformacion daoInformacion = new DaoInformacion();
            Informacion informacion = daoInformacion.find(hijoDto.get_informacionDto().getId(), Informacion.class);
            hijo.set_informacion(informacion);

            Hijo resul = dao.insert(hijo);
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
     * Inserta varios registros de Hijo, en la BD.
     *
     * Recorre la lista pasada por parámetro con los hijos de un
     * mismo padre/madre y los inserta en la BD usando el método addHijo,
     * de esta misma API.
     *
     * @param listaHijoDto Lista de HijoDto que se desea insertar en la BD.
     * */
    @POST
    @Path("/addVariosHijos")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public List<Response> addVariosHijos(List<HijoDto> listaHijoDto){
        List<Response> listaHijo = new ArrayList<Response>();

        try {
            int n = 1;
            System.out.println("Hijo n° " + n);
            for (HijoDto hijoDto: listaHijoDto) {
                listaHijo.add(addHijo(hijoDto));
            }

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);

        }

        return listaHijo;
    }

    /**
     * Este método permite modificar la data de un hijo
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el hijo modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una categoria duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param hijoDto el objeto hijo que el sistema desea modificar.
     * @param id el id del hijo a modificar
     */
    @PUT
    @Path("/updateHijo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateHijo(@PathParam("id") long id, HijoDto hijoDto){

        DaoHijo daoHijo = new DaoHijo();
        JsonObject dataObject;

        try {
            Hijo hijo_modificar = daoHijo.find(id, Hijo.class);
            hijo_modificar.set_fechaNacimiento(hijoDto.getFechaNacimiento());
            hijo_modificar.set_genero(hijoDto.getGenero());
            hijo_modificar.set_estatus("Activo");
            daoHijo.update(hijo_modificar);
            return Response.status(Response.Status.OK).entity(hijo_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
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
     * Este método permite eliminar un hijo
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de éxito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un hijo duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del hijo a eliminar
     */
    @DELETE
    @Path("/deleteHijo/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response deleteHijo(@PathParam("id") long id){

        JsonObject dataObject;
        DaoHijo daoHijo = new DaoHijo();

        try {
            Hijo hijo_eliminar = daoHijo.find(id, Hijo.class);
            daoHijo.delete(hijo_eliminar);
            return Response.status(Response.Status.OK).entity(hijo_eliminar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
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
