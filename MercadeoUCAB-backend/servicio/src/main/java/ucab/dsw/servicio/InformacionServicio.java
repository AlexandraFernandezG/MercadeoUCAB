package ucab.dsw.servicio;

import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.Informacion.AddInformacionComando;
import ucab.dsw.dtos.InformacionDto;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperInformacion;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/informacion" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class InformacionServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(InformacionServicio.class);
    /**
     * Este método permite obtener la información correspondiente a una cédula.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de la información consultado y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/consultarCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInformacionCedula(@PathParam("cedula") int cedula) {
        logger.debug("Ingresando al método que permite obtener la información dada una cédula");
        JsonObject dataObject;
        DaoInformacion daoInformacion = new DaoInformacion();

        try {
            List<Informacion> informacion_consultada = daoInformacion.obtenerInformacion(cedula);

            logger.debug("Saliendo del método que permite obtener la información dada una cédula");
            return Response.status(Response.Status.OK).entity(informacion_consultada).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la categoria: " + ex.getMessage())
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
     * Este método permite obtener todas las informaciones.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de informaciones y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/listarInformacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarInformacionEncuestados() {
        logger.debug("Ingresando al método que permite obtener una lista de todas las informaciones");
        DaoInformacion daoInformacion = new DaoInformacion();
        JsonObject dataObject;

        try {
            List<Informacion> listaInformaciones = daoInformacion.findAll(Informacion.class);

            logger.debug("Saliendo método que permite obtener una lista de todas las informaciones");
            return Response.status(Response.Status.OK).entity(listaInformaciones).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener una información.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la información consultada y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id de la información que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInformacion(@PathParam("id") long id) throws NullPointerException {

        logger.debug("Ingresando al método que permite obtener una información detallada");
        DaoInformacion daoInformacion = new DaoInformacion();
        JsonObject dataObject;

        try {
            Informacion informacion_consultada = daoInformacion.find(id, Informacion.class);

            logger.debug("Saliendo del método que permite obtener una información detallada");
            return Response.status(Response.Status.OK).entity(informacion_consultada).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la información: " + ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

     /** Este método permite insertar una información
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la información insertada y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una información duplicada.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param informacionDto el objeto información que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addInformacion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInformacion(InformacionDto informacionDto) {

        logger.debug("Ingresando al método que permite agregar una información");
        JsonObject dataObject;

        try {

            Informacion informacion = MapperInformacion.mapDtoToEntityInsert(informacionDto);
            AddInformacionComando comando = Fabrica.crearComandoConEntity(AddInformacionComando.class, informacion);
            comando.execute();

            logger.debug("Saliendo del método que permite agregar una información");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException ex) {

            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 601).build();

            logger.error("Código de error: " + 601 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InstantiationException ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 602).build();

            logger.error("Código de error: " + 602 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InvocationTargetException ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 603).build();

            logger.error("Código de error: " + 603 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (Exception ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite modificar una categoria
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con la información modificada y en tal caso obtener una excepción si aplica.
     * @throws NullPointerException esta excepción se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta una información duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param informacionDto el objeto información que el sistema desea modificar.
     * @param id el id de la información a modificar
     */
    @PUT
    @Path("/updateInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarInformacion(@PathParam("id") long id, InformacionDto informacionDto) {

        logger.debug("Ingresando al método que permite actualizar una información");
        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_modificar = daoInformacion.find(id, Informacion.class);
        JsonObject dataObject;

        try {

            DaoUsuario daoUsuario = new DaoUsuario();
            DaoOcupacion daoOcupacion = new DaoOcupacion();
            DaoNivelEconomico daoNivelEconomico = new DaoNivelEconomico();
            DaoNivelAcademico daoNivelAcademico = new DaoNivelAcademico();
            DaoLugar daoLugar = new DaoLugar();

            informacion_modificar.set_cedula(informacionDto.getCedula());
            informacion_modificar.set_primerNombre(informacionDto.getPrimerNombre());
            informacion_modificar.set_segundoNombre(informacionDto.getSegundoNombre());
            informacion_modificar.set_primerApellido(informacionDto.getPrimerApellido());
            informacion_modificar.set_segundoApellido(informacionDto.getSegundoApellido());
            informacion_modificar.set_genero(informacionDto.getGenero());
            informacion_modificar.set_fechaNacimiento(informacionDto.getFechaNacimiento());
            informacion_modificar.set_estadoCivil(informacionDto.getEstadoCivil());
            informacion_modificar.set_disponibilidadEnLinea(informacionDto.getDisponibilidadEnLinea());
            informacion_modificar.set_cantidadPersonas(informacionDto.getCantidadPersonas());
            informacion_modificar.set_estatus(informacionDto.getEstatus());

            Lugar lugar = daoLugar.find(informacionDto.getLugarDto().getId(), Lugar.class);
            informacion_modificar.set_lugar(lugar);

            NivelEconomico nivelEconomico = daoNivelEconomico.find(informacionDto.getNivelEconomicoDto().getId(), NivelEconomico.class);
            informacion_modificar.set_nivelEconomico(nivelEconomico);

            NivelAcademico nivelAcademico = daoNivelAcademico.find(informacionDto.getNivelAcademicoDto().getId(), NivelAcademico.class);
            informacion_modificar.set_nivelAcademico(nivelAcademico);

            Ocupacion ocupacion = daoOcupacion.find(informacionDto.getOcupacionDto().getId(), Ocupacion.class);
            informacion_modificar.set_ocupacion(ocupacion);

            Usuario usuario = daoUsuario.find(informacionDto.getUsuarioDto().getId(), Usuario.class);
            informacion_modificar.set_usuario(usuario);

            daoInformacion.update(informacion_modificar);

            logger.debug("Saliendo del método que permite actualizar una información");
            return Response.status(Response.Status.OK).entity(informacion_modificar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la información: " + ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }

    }

    /**
     * Este método permite eliminar una información
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de exito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id de la categoria a eliminar
     */
    @DELETE
    @Path("/deleteInformacion/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInformacion(@PathParam("id") long id) {
        logger.debug("Ingresando al método que permite eliminar una información");

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion_eliminar = daoInformacion.find(id, Informacion.class);
        JsonObject dataObject;

        try {

            daoInformacion.delete(informacion_eliminar);

            logger.debug("Saliendo del método que permite eliminar una información");
            return Response.status(Response.Status.OK).entity(informacion_eliminar).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            logger.error("Código de error: " + 500 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la información: " + ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }
}