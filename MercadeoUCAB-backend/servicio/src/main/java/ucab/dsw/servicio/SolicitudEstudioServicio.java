package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.SolicitudEstudio.AddSolicitudEstudioComando;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperSolicitudEstudio;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/solicitudEstudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SolicitudEstudioServicio extends AplicacionBase{

    private static Logger logger = LoggerFactory.getLogger(SolicitudEstudioServicio.class);
    /**
     * Este método permite obtener todas las solicitudes de estudio.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de solicitudes de estudio y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allSolicitudEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarSolicitudes() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las solicitudes");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        JsonObject dataObject;

        try {

            List<SolicitudEstudio> listaSolicitudes =  daoSolicitudEstudio.findAll(SolicitudEstudio.class);
            logger.debug("Saliendo del método que lista todas las solicitudes");
            return Response.status(Response.Status.OK).entity(listaSolicitudes).build();

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
     * Este método permite obtener una solicitud de estudio.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud de estudio consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la solicitud que se quiere consultar.
     */
    @GET
    @Path("/consultarSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarSolicitud(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que consulta una solicitud");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        JsonObject dataObject;

        try {

            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);
            logger.debug("Saliendo del método que consulta una solicitud");
            return Response.status(Response.Status.OK).entity(solicitudEstudio).build();

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
     * Este método permite obtener todas las solicitudes en espera para ser procesadas
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de solicitudes en espera y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSolicitudesEnEspera")
    @Produces( MediaType.APPLICATION_JSON )
    public Response mostrarSolicitudesEnEspera() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que muestra solicitudes en espera");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        List<SolicitudEstudio> listaSolicitud = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
        List<SolicitudEstudio> listaSolicitudesEnEspera = new ArrayList<SolicitudEstudio>();
        JsonObject dataObject;

        try {

            for (SolicitudEstudio solicitudEstudio : listaSolicitud) {

                if (solicitudEstudio.get_estado().equals("En espera")) {
                    listaSolicitudesEnEspera.add(solicitudEstudio);
                }
            }

            logger.debug("Saliendo del método que muestra solicitudes en espera");
            return Response.status(Response.Status.OK).entity(listaSolicitudesEnEspera).build();

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
     * Este método permite obtener todas las solicitudes de un cliente
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de solicitudes en espera y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSolicitudesCliente/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response mostrarSolicitudesCliente(@PathParam("id") long id) {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las solicitudes del cliente");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        List<SolicitudEstudio> listaSolicitud = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
        List<SolicitudEstudio> listaSolicitudesCliente = new ArrayList<SolicitudEstudio>();
        JsonObject dataObject;

        try {

            for (SolicitudEstudio solicitudEstudio : listaSolicitud) {

                if (solicitudEstudio.get_estado().equals("En espera") && solicitudEstudio.get_usuario().get_id() == id) {
                    listaSolicitudesCliente.add(solicitudEstudio);
                }
            }

            logger.debug("Saliendo del método que lista todas las solicitudes del cliente");
            return Response.status(Response.Status.OK).entity(listaSolicitudesCliente).build();

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
     * Este método permite obtener todas las solicitudes activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de solicitudes activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSolicitudesActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response mostrarSolicitudesActivas() {

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que lista todas las solicitudes activas");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        List<SolicitudEstudio> listaSolicitud = daoSolicitudEstudio.findAll(SolicitudEstudio.class);
        List<SolicitudEstudio> listaSolicitudesActivas = new ArrayList<SolicitudEstudio>();
        JsonObject dataObject;

        try {

            for (SolicitudEstudio solicitudEstudio : listaSolicitud) {

                if (solicitudEstudio.get_estatus().equals("Activo")) {
                    listaSolicitudesActivas.add(solicitudEstudio);
                }
            }
            logger.debug("Saliendo del método que lista todas las solicitudes activas");
            return Response.status(Response.Status.OK).entity(listaSolicitudesActivas).build();

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
     * Este método permite insertar una solicitud
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una solicitud duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param solicitudEstudioDto el objeto solicitud que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addSolicitudEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addSolicitudEstudio(SolicitudEstudioDto solicitudEstudioDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que añade una solicitud");
        JsonObject dataObject;

        try {

            SolicitudEstudio solicitudEstudio = MapperSolicitudEstudio.mapDtoToEntityInsert(solicitudEstudioDto);
            AddSolicitudEstudioComando comando = Fabrica.crearComandoConEntity(AddSolicitudEstudioComando.class, solicitudEstudio);
            comando.execute();
            logger.debug("Saliendo del método que añade una solicitud");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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

        } catch (PruebaExcepcion ex) {

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException ex) {

            logger.error("Código de error: " + 601 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 601).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InstantiationException ex) {

            logger.error("Código de error: " + 602 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 602).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InvocationTargetException ex) {
            logger.error("Código de error: " + 603 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 603).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (Exception ex) {
            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite modificar el estatus una solicitud con sus estudios
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una solicitud duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param solicitudEstudioDto el objeto solicitud que el sistema desea modificar.
     * @param id el id de la solicitud a modificar
     */
    @PUT
    @Path("/estatusSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstatusSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualiza el estatus de la solicitud");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);
        JsonObject dataObject;

            try {

                solicitudEstudio_modificar.set_estatus(solicitudEstudioDto.getEstatus());
                daoSolicitudEstudio.update(solicitudEstudio_modificar);
                DaoEstudio daoEstudio = new DaoEstudio();

                if (solicitudEstudio_modificar.get_estatus().equals("Inactivo")) {

                    List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

                    for (Estudio estudio : listaEstudio) {

                        if (estudio.get_solicitudEstudio().get_id() == id) {
                            estudio.set_estatus("Inactivo");
                            daoEstudio.update(estudio);
                        }
                    }
                } else if (solicitudEstudio_modificar.get_estatus().equals("Activo")) {

                    List<Estudio> listaEstudio = daoEstudio.findAll(Estudio.class);

                    for (Estudio estudio : listaEstudio) {

                        if (estudio.get_solicitudEstudio().get_id() == id) {
                            estudio.set_estatus("Activo");
                            daoEstudio.update(estudio);
                        }
                    }
                }

                logger.debug("Saliendo del método que actualiza el estatus de la solicitud");
                return Response.status(Response.Status.OK).entity(solicitudEstudio_modificar).build();

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
     * Este método permite modificar  una solicitud estudio
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la solicitud modificada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica una solicitud duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param solicitudEstudioDto el objeto solicitud que el sistema desea modificar.
     * @param id el id de la solicitud a modificar
     */
    @PUT
    @Path("/updateSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarSolicitudEstudio(@PathParam("id") long id, SolicitudEstudioDto solicitudEstudioDto){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que actualizar una solicitud");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio_modificar = daoSolicitudEstudio.find(id, SolicitudEstudio.class);
        JsonObject dataObject;

            try {

                solicitudEstudio_modificar.set_descripcion(solicitudEstudioDto.getDescripcion());
                solicitudEstudio_modificar.set_genero(solicitudEstudioDto.getGenero());
                solicitudEstudio_modificar.set_edadMaxima(solicitudEstudioDto.getEdadMaxima());
                solicitudEstudio_modificar.set_edadMinima(solicitudEstudioDto.getEdadMinima());
                solicitudEstudio_modificar.set_estadoCivil(solicitudEstudioDto.getEstadoCivil());
                solicitudEstudio_modificar.set_disponibilidadEnLinea(solicitudEstudioDto.getDisponibilidadEnLinea());
                solicitudEstudio_modificar.set_cantidadPersonas(solicitudEstudioDto.getCantidadPersonas());
                solicitudEstudio_modificar.set_cantidadHijos(solicitudEstudioDto.getCantidadHijos());
                solicitudEstudio_modificar.set_generoHijos(solicitudEstudioDto.getGeneroHijos());
                solicitudEstudio_modificar.set_edadMinimaHijos(solicitudEstudioDto.getEdadMinimaHijos());
                solicitudEstudio_modificar.set_edadMaximaHijos(solicitudEstudioDto.getEdadMaximaHijos());
                solicitudEstudio_modificar.set_estado(solicitudEstudioDto.getEstado());
                solicitudEstudio_modificar.set_estatus(solicitudEstudioDto.getEstatus());
                daoSolicitudEstudio.update(solicitudEstudio_modificar);

                logger.debug("Saliendo del método que actualizar una solicitud");
                return Response.status(Response.Status.OK).entity(solicitudEstudio_modificar).build();

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
     * Este método permite eliminar una solicitud
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un solicitud duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la solicituda eliminar
     */
    @DELETE
    @Path("/deleteSolicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarSolicitudEstudio(@PathParam("id") long id){

        BasicConfigurator.configure();
        logger.debug("Ingresando al método que elimina una solicitud");
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);
        JsonObject dataObject;

            try {

                daoSolicitudEstudio.delete(solicitudEstudio);
                logger.debug("Saliendo del método que elimina una solicitud");
                return Response.status(Response.Status.OK).entity(solicitudEstudio).build();

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
