package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoMedioComunicacion;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.dtos.MedioComunicacionDto;
import ucab.dsw.entidades.Informacion;
import ucab.dsw.entidades.MedioComunicacion;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/medioComunicacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class MedioComunicacionServicio extends AplicacionBase{
    private static Logger logger = LoggerFactory.getLogger(MedioComunicacionServicio.class);

    /**
     * Este método permite obtener todos los medios de comunicación.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de MedioComunicacion y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allMedioComunicacion")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarMedioComunicacion() {
        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        JsonObject dataObject;

        try {
            List<MedioComunicacion> listaMediosComunicacion= daoMedioComunicacion.findAll(MedioComunicacion.class);
            return Response.status(Response.Status.OK).entity(listaMediosComunicacion).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }


    /**
     * Este método permite obtener un medio de comunicación.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el medio consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del medio de comunicación que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarMedioComunicacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarMedioComunicacion(@PathParam("id") long id) {
        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        JsonObject dataObject;

        try {
            MedioComunicacion medioComunicacion = daoMedioComunicacion.find(id, MedioComunicacion.class);
            return Response.status(Response.Status.OK).entity(medioComunicacion).build();

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

    //Agregar un medio
    @POST
    @Path("/addMedioComunicacion")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addMedioComunicacion(MedioComunicacionDto medioComunicacionDto){

        MedioComunicacionDto resultado = new MedioComunicacionDto();
        JsonObject dataObject;

        try {


            DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
            MedioComunicacion medioComunicacion = new MedioComunicacion();
            DaoInformacion daoInformacion = new DaoInformacion();
            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

            medioComunicacion.set_tipoDeMedio(medioComunicacionDto.getTipoDeMedio());
            medioComunicacion.set_estatus(medioComunicacionDto.getEstatus());
            Informacion informacion = daoInformacion.find(medioComunicacionDto.getInformacionDto().getId(), Informacion.class);
            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(medioComunicacionDto.getSolicitudEstudioDto().getId(), SolicitudEstudio.class);
            medioComunicacion.set_informacion(informacion);
            medioComunicacion.set_solicitudEstudio(solicitudEstudio);
            MedioComunicacion resul = daoMedioComunicacion.insert(medioComunicacion);
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
                    .add("excepcion", "No se ha encontrado el medio de comunicación: " + ex.getMessage())
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
     * Este método permite modificar un medio de comunicación
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el medio de comunicación modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un medio de comunicación duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param medioComunicacionDto el objeto categoria que el sistema desea modificar.
     * @param id el id del medio de comunicación a modificar
     */
    @PUT
    @Path("/updateMedioComunicacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateMedioComunicacion(@PathParam("id") long id, MedioComunicacionDto medioComunicacionDto){

        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        MedioComunicacion medioComunicacion_modificar = daoMedioComunicacion.find(id, MedioComunicacion.class);
        JsonObject dataObject;

            try {

                medioComunicacion_modificar.set_tipoDeMedio(medioComunicacionDto.getTipoDeMedio());
                medioComunicacion_modificar.set_estatus(medioComunicacionDto.getEstatus());
                daoMedioComunicacion.update(medioComunicacion_modificar);
                return Response.status(Response.Status.OK).entity(medioComunicacion_modificar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado ningún medio de comunicación: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }

    }

    /**
     * Este método permite eliminar un medio de comunicación
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del medio de comunicación a eliminar
     */
    @DELETE
    @Path("/deleteMedioComunicacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarMedioComunicacion(@PathParam("id") long id){

        DaoMedioComunicacion daoMedioComunicacion = new DaoMedioComunicacion();
        MedioComunicacion medioComunicacion_eliminar = daoMedioComunicacion.find(id, MedioComunicacion.class);
        JsonObject dataObject;
            try {
                daoMedioComunicacion.delete(medioComunicacion_eliminar);
                return Response.status(Response.Status.OK).entity(medioComunicacion_eliminar).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", "No se ha encontrado el medio de comunicación: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            }



    }

}
