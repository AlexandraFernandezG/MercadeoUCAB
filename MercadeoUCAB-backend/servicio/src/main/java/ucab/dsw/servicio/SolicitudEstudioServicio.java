package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.SolicitudEstudioDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;

import java.util.ArrayList;
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

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        JsonObject dataObject;

        try {

            List<SolicitudEstudio> listaSolicitudes =  daoSolicitudEstudio.findAll(SolicitudEstudio.class);

            return Response.status(Response.Status.OK).entity(listaSolicitudes).build();

        } catch (Exception ex) {

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

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        JsonObject dataObject;

        try {

            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

            return Response.status(Response.Status.OK).entity(solicitudEstudio).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la solicitud: " + ex.getMessage())
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
     * Este método permite obtener todas las solicitudes activas.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de solicitudes activas y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarSolicitudesActivas")
    @Produces( MediaType.APPLICATION_JSON )
    public Response mostrarSolicitudesActivas() {

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

            return Response.status(Response.Status.OK).entity(listaSolicitudesActivas).build();

        } catch (Exception ex) {

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

        SolicitudEstudioDto resultado = new SolicitudEstudioDto();
        JsonObject dataObject;

        try {

            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
            SolicitudEstudio solicitudEstudio = new SolicitudEstudio();
            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            DaoUsuario daoUsuario = new DaoUsuario();
            DaoProducto daoProducto = new DaoProducto();
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();

            solicitudEstudio.set_descripcion(solicitudEstudioDto.getDescripcion());
            solicitudEstudio.set_genero(solicitudEstudioDto.getGenero());
            solicitudEstudio.set_edadMaxima(solicitudEstudioDto.getEdadMaxima());
            solicitudEstudio.set_edadMinima(solicitudEstudioDto.getEdadMinima());
            solicitudEstudio.set_estadoCivil(solicitudEstudioDto.getEstadoCivil());
            solicitudEstudio.set_disponibilidadEnLinea(solicitudEstudioDto.getDisponibilidadEnLinea());
            solicitudEstudio.set_cantidadPersonas(solicitudEstudioDto.getCantidadPersonas());
            solicitudEstudio.set_cantidadHijos(solicitudEstudioDto.getCantidadHijos());
            solicitudEstudio.set_generoHijos(solicitudEstudioDto.getGeneroHijos());
            solicitudEstudio.set_edadMinimaHijos(solicitudEstudioDto.getEdadMinimaHijos());
            solicitudEstudio.set_edadMaximaHijos(solicitudEstudioDto.getEdadMaximaHijos());
            solicitudEstudio.set_estado(solicitudEstudioDto.getEstado());
            solicitudEstudio.set_estatus(solicitudEstudioDto.getEstatus());
            NivelEconomico nivelEconomico = daoNivelEconomico.find(solicitudEstudioDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
            Usuario usuario = daoUsuario.find(solicitudEstudioDto.getUsuarioDto().getId(), Usuario.class);
            Producto producto = daoProducto.find(solicitudEstudioDto.getProductoDto().getId(), Producto.class);
            Ocupacion ocupacion = daoOcupacion.find(solicitudEstudioDto.getOcupacionDto().getId(), Ocupacion.class);
            NivelAcademico nivelAcademico = daoNivelAcademico.find(solicitudEstudioDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
            solicitudEstudio.set_nivelEconomico(nivelEconomico);
            solicitudEstudio.set_usuario(usuario);
            solicitudEstudio.set_producto(producto);
            solicitudEstudio.set_ocupacion(ocupacion);
            solicitudEstudio.set_nivelAcademico(nivelAcademico);
            SolicitudEstudio resul = daoSolicitudEstudio.insert(solicitudEstudio);
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

                return Response.status(Response.Status.OK).entity(solicitudEstudio_modificar).build();

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

                return Response.status(Response.Status.OK).entity(solicitudEstudio_modificar).build();

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

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);
        JsonObject dataObject;

            try {

                daoSolicitudEstudio.delete(solicitudEstudio);

                return Response.status(Response.Status.OK).entity(solicitudEstudio).build();

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

            }
    }
}
