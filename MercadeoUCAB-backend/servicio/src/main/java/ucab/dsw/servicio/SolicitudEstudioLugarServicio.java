package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoSolicitudEstudioLugar;
import ucab.dsw.accesodatos.DaoLugar;
import ucab.dsw.dtos.SolicitudEstudioLugarDto;
import ucab.dsw.entidades.Estudio;
import ucab.dsw.entidades.SolicitudEstudio;
import ucab.dsw.entidades.SolicitudEstudioLugar;
import ucab.dsw.entidades.Lugar;
import ucab.dsw.excepciones.PruebaExcepcion;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/* Esta api esta en revision y puede ser cambiada */

@Path( "/SolicitudestudioLugar" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SolicitudEstudioLugarServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(SolicitudEstudioLugarServicio.class);
    /**
     * Este método permite listar las solicitudes de estudio con sus lugares.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de lugares de una solicitud y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id id de la solicitud que se quiere consultar sus lugares
     */
    @GET
    @Path("/listarLugaresSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarLugaresEstudio(@PathParam("id") long id)  {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista las solicitudes de estudio con lugares");
        DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
        List<SolicitudEstudioLugar> listarLugaresEstudio = daoSolicitudEstudioLugar.findAll(SolicitudEstudioLugar.class);
        List<Lugar> listaLugares = new ArrayList<Lugar>();
        JsonObject dataObject;

        try {

            for (SolicitudEstudioLugar solicitudEstudioLugar : listarLugaresEstudio) {

                if (solicitudEstudioLugar.get_SolicitudEstudio().get_id() == id) {

                    long idFk = solicitudEstudioLugar.get_lugar().get_id();
                    DaoLugar daoLugar = new DaoLugar();
                    Lugar lugar = daoLugar.find(idFk, Lugar.class);
                    listaLugares.add(lugar);
                }

            }

            logger.debug("Saliendo del método que lista las solicitudes de estudio con lugares");
            return Response.status(Response.Status.OK).entity(listaLugares).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite un solicitud de estudio con uno o varios lugares
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una solicitud lugar duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param solicitudEstudioLugarDto el objeto solicitud Lugar que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addEstudioLugar")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addEstudioLugar(SolicitudEstudioLugarDto solicitudEstudioLugarDto){

        SolicitudEstudioLugarDto resultado = new SolicitudEstudioLugarDto();
        JsonObject dataObject;

        try {

            DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
            SolicitudEstudioLugar solicitudEstudioLugar = new SolicitudEstudioLugar();
            DaoLugar daoLugar = new DaoLugar();
            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();

            solicitudEstudioLugar.set_estatus(solicitudEstudioLugarDto.getEstatus());
            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(solicitudEstudioLugarDto.getSolicitudestudioDto().getId(), SolicitudEstudio.class);
            Lugar lugar = daoLugar.find(solicitudEstudioLugarDto.getLugarDto().getId() , Lugar.class);
            solicitudEstudioLugar.set_SolicitudEstudio(solicitudEstudio);
            solicitudEstudioLugar.set_lugar(lugar);
            SolicitudEstudioLugar resul = daoSolicitudEstudioLugar.insert(solicitudEstudioLugar);
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
                    .add("excepcion", "No se ha encontrado la solicitud: " + ex.getMessage())
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
     * Este método permite modificar el estatus una solicitud lugar
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud lugar modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una categoria duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param solicitudEstudioLugarDto el objeto solicitud lugar que el sistema desea modificar.
     * @param id el id de la solicitud lugar a modificar
     */
    @PUT
    @Path("/updateEstatusEstudioLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response updateEstatusEstudioLugar(@PathParam("id") long id, SolicitudEstudioLugarDto solicitudEstudioLugarDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualiza el estatus SolicitudEstudiolugar");
        DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
        SolicitudEstudioLugar solicitudEstudioLugar_modificar = daoSolicitudEstudioLugar.find(id, SolicitudEstudioLugar.class);
        JsonObject dataObject;

        try {

            solicitudEstudioLugar_modificar.set_estatus(solicitudEstudioLugarDto.getEstatus());
            daoSolicitudEstudioLugar.update(solicitudEstudioLugar_modificar);

            logger.debug("Saliendo del método que actualiza el estatus SolicitudEstudiolugar");
            return Response.status(Response.Status.OK).entity(solicitudEstudioLugar_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite eliminar una solicitud lugar
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud lugar eliminada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un solicitud lugar duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la solicitud lugar a modificar
     */
    @DELETE
    @Path("/deleteEstudioLugar/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteEstudiaLugar(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina SolicitudEstudioLugar");
        DaoSolicitudEstudioLugar daoSolicitudEstudioLugar = new DaoSolicitudEstudioLugar();
        SolicitudEstudioLugar solicitudEstudioLugar_eliminar = daoSolicitudEstudioLugar.find(id, SolicitudEstudioLugar.class);
        JsonObject dataObject;

        try {

            daoSolicitudEstudioLugar.delete(solicitudEstudioLugar_eliminar);
            logger.debug("Saliendo del método que elimina SolicitudEstudioLugar");
            return Response.status(Response.Status.OK).entity(solicitudEstudioLugar_eliminar).build();

        } catch (PersistenceException | DatabaseException ex){

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }
}
