package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoNivelAcademico;
import ucab.dsw.dtos.NivelAcademicoDto;
import ucab.dsw.entidades.NivelAcademico;
import ucab.dsw.excepciones.PruebaExcepcion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path( "/nivelAcademico" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class NivelAcademicoServicio extends AplicacionBase{

    /**
     * Este método permite obtener todas los niveles académicos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de NivelAcademico y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allNivelAcademico")
    @Produces( MediaType.APPLICATION_JSON )
    public  Response listarNivelAcademico() {
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        JsonObject dataObject;

        try {
            List<NivelAcademico> listaNivelAcademico = daoNivelAcademico.findAll(NivelAcademico.class);
            return Response.status(Response.Status.OK).entity(listaNivelAcademico).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener un nivel académico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el nivel académico consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del nivel académico que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarNivelAcademico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarNivelAcademico(@PathParam("id") long id) {
        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        JsonObject dataObject;

        try {
            NivelAcademico nivelAcademico_consultado = daoNivelAcademico.find(id, NivelAcademico.class);
            return Response.status(Response.Status.OK).entity(nivelAcademico_consultado).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el nivel académico: " + ex.getMessage())
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
     * Este método permite insertar un nivel académico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el nivel académico insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando ha fallado la inserción
     * @throws PersistenceException si se inserta un nivel académico duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param nivelAcademicoDto el objeto de tipo nivel académico que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addNivelAcademico")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addNivelAcademico(NivelAcademicoDto nivelAcademicoDto){

        NivelAcademicoDto resultado = new NivelAcademicoDto();
        JsonObject dataObject;

        try {

            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            NivelAcademico nivelAcademico = new NivelAcademico();

            nivelAcademico.set_descripcion(nivelAcademicoDto.getDescripcion());
            nivelAcademico.set_estatus(nivelAcademicoDto.getEstatus());
            NivelAcademico resul = daoNivelAcademico.insert(nivelAcademico);
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
                    .add("excepcion", "No se ha podido insertar el nivel académico: " + ex.getMessage())
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
     * Este método permite modificar un nivel académico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el nivel académico y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param nivelAcademicoDto el objeto nivel académico que el sistema desea modificar.
     * @param id el id del nivel académico a modificar
     */
    @PUT
    @Path("/updateNivelAcademico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateNivelAcademico(@PathParam("id") long id, NivelAcademicoDto nivelAcademicoDto){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico_modificar = daoNivelAcademico.find(id, NivelAcademico.class);
        JsonObject dataObject;

                try {
                    nivelAcademico_modificar.set_descripcion(nivelAcademicoDto.getDescripcion());
                    nivelAcademico_modificar.set_estatus(nivelAcademicoDto.getEstatus());
                    daoNivelAcademico.update(nivelAcademico_modificar);
                    return Response.status(Response.Status.OK).entity(nivelAcademico_modificar).build();

                } catch (PersistenceException | DatabaseException ex){

                    dataObject= Json.createObjectBuilder()
                            .add("estado","Error")
                            .add("mensaje", ex.getMessage())
                            .add("codigo",500).build();

                    return Response.status(Response.Status.OK).entity(dataObject).build();

                } catch (NullPointerException ex) {

                    dataObject = Json.createObjectBuilder()
                            .add("estado", "Error")
                            .add("excepcion", "No se ha encontrado el nivel académico: " + ex.getMessage())
                            .add("codigo", 400).build();

                    return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

                }

    }


    /**
     * Este método permite eliminar un nivel académico
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del nivel académico a eliminar
     */
    @DELETE
    @Path("/deleteNivelAcademico/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarNivelAcademico(@PathParam("id") long id){

        DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
        NivelAcademico nivelAcademico_eliminar = daoNivelAcademico.find(id, NivelAcademico.class);
        JsonObject dataObject;

            try {
                daoNivelAcademico.delete(nivelAcademico_eliminar);
                return Response.status(Response.Status.OK).entity(nivelAcademico_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado  el nivel académico: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }
    }
}
