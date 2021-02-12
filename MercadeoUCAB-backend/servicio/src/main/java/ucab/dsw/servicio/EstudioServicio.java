package ucab.dsw.servicio;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.comando.Estudio.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperEstudio;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.Response.UsuarioResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EstudioServicio extends AplicacionBase {

    private static Logger logger = LoggerFactory.getLogger(EstudioServicio.class);
    /**
     * Este método permite obtener todas los estudios.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de estudios y en tal caso obtener una excepción si aplica.
     */
    @GET
    @Path("/allEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudios() {

        logger.debug("Ingresando al método que muestra todos los estudios");
        JsonObject dataObject;

        try {

            ListarEstudiosComando comando = Fabrica.crear(ListarEstudiosComando.class);
            comando.execute();

            logger.debug("Saliendo del método que muestra todos los estudios");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();


        } catch (Exception ex){

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 404).build();

            logger.error("Código de error: " + 404 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener un estudio.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * * @return Este metodo retorna un objeto de tipo Json con el
     * con el estudio consultado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     * @param id el id del estudio que se quiere consultar.
     *
     */
    @GET
    @Path("/consultarEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response consultarEstudio(@PathParam("id") long id) {
        logger.debug("Ingresando al método que consulta un estudio");
        JsonObject dataObject;

        try {

            ConsultarEstudioComando comando = Fabrica.crearComandoConId(ConsultarEstudioComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 404).build();

            logger.error("Código de error: " + 404 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
        
    }

    /**
     * Este método permite obtener todas los estudios activos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de estudios activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarEstudiosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response estudiosActivos() {

        logger.debug("Ingresando al método que muestra los estudios activos");
        JsonObject dataObject;
        
        try {

            MostrarEstudiosActivosComando comando = Fabrica.crear(MostrarEstudiosActivosComando.class);
            comando.execute();

            logger.debug("Saliendo del método que muestra todos los estudios activos");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 404).build();

            logger.error("Código de error: " + 404 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener todas los estudios en espera.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de estudios en espera y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarEstudiosEnEspera")
    @Produces( MediaType.APPLICATION_JSON )
    public Response estudiosEnEspera() {

        logger.debug("Ingresando al método que muestra los estudios en espera");
        JsonObject dataObject;

        try {

            MostrarEstudiosEnEsperaComando comando = Fabrica.crear(MostrarEstudiosEnEsperaComando.class);
            comando.execute();

            logger.debug("Saliendo del método que muestra los estudios en espera");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }
    }

    /**
     * Este método permite obtener los encuestados en base a una solicitud de estudio.
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de encuestados de una solicitud y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     */
    @GET
    @Path("/solicitudEncuestados/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEncuestadosSolicitud(@PathParam("id") long id){

        logger.debug("Ingresando al método que permite obtener a los encuestados en base a una solicitud");
        JsonObject dataObject;

        try {

            ListarSolicitudEncuestadosComando comando = Fabrica.crearComandoConId(ListarSolicitudEncuestadosComando.class, id);
            comando.execute();

            logger.debug("Saliendo del método que permite obtener a los encuestados en base a una solicitud");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }  catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
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

    /**
     * Este método permite insertar los encuestados de un estudio creado
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * un mensaje de exito y en tal caso obtener una excepcion si aplica.
     */
     @POST
     @Path("/estudioEncuestados/{id}")
     @Consumes( MediaType.APPLICATION_JSON )
     @Produces( MediaType.APPLICATION_JSON )
     public Response insertarEncuestadosEstudio(@PathParam("id") long id, List<UsuarioResponse> listaEncuestados){

         logger.debug("Ingresando al método que muestra a los encuestados en base a un estudio");
         JsonObject dataObject;

         try {

             InsertarEncuestadosEstudioComando comando = Fabrica.crearComandoListIdUsuarioResponse(InsertarEncuestadosEstudioComando.class, id, listaEncuestados);
             comando.execute();

             logger.debug("Saliendo del método que muestra a los encuestados en base a un estudio");
             return Response.status(Response.Status.OK).entity(comando.getResult()).build();

         } catch (Exception ex) {

             dataObject = Json.createObjectBuilder()
                     .add("estado", "Error")
                     .add("excepcion", ex.getMessage())
                     .add("codigo", 400).build();
             logger.error("Código de error: " + 400 +  ", Mensaje de error: " + ex.getMessage());
             return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
         }
     }

    /**
     * Este método permite insertar las preguntas sugeridas/añadidas de un estudio creado
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * un mensaje de exito y en tal caso obtener una excepcion si aplica.
     */
    @POST
    @Path("/estudioPreguntas/{id}")
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response insertarPreguntasEstudio(@PathParam("id") long id, List<PreguntasResponse> listaPreguntas){

        logger.debug("Ingresando al método que permite insertar preguntas a un estudio");
        JsonObject dataObject;

        try {

            InsertarPreguntasEstudioComando comando = Fabrica.crearComandoListIdPreguntasResponse(InsertarPreguntasEstudioComando.class, id, listaPreguntas);
            comando.execute();

            logger.debug("Saliendo del método que permite insertar preguntas a un estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
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

    /**
     * Este método permite insertar un estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el estudio insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param estudioDto el objeto dto a insertar del estudio que se quiere crear.
     */
    @POST
    @Path("/addEstudio")
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response addEstudios(EstudioDto estudioDto) {

        logger.debug("Ingresando al método que agrega un estudio");
        JsonObject dataObject;

        try {

            Estudio estudio = MapperEstudio.mapDtoToEntityInsert(estudioDto);
            AddEstudioComando comando = Fabrica.crearComandoConEntity(AddEstudioComando.class, estudio);
            comando.execute();

            logger.debug("Saliendo del método que agrega un estudio");
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

        }  catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 402).build();

            logger.error("Código de error: " + 402 +  ", Mensaje de error: " + ex.getMessage());
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

    /**
     * Este método permite modificar un estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el objeto modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param estudioDto el objeto estudio que el sistema desea modificar.
     * @param id el id del estudio a modificar
     */
    @PUT
    @Path("/updateEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstudio(@PathParam("id") long id, EstudioDto estudioDto){

        logger.debug("Ingresando al método que actualiza un estudio");
        JsonObject dataObject;

            try {

                Estudio estudio = MapperEstudio.mapDtoToEntityUpdate(id, estudioDto);
                ModificarEstudioComando comando = Fabrica.crearComandoBoth(ModificarEstudioComando.class, id, estudio);
                comando.execute();

                logger.debug("Saliendo del método que actualiza un estudio");
                return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 401).build();

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (IllegalAccessException | PruebaExcepcion ex) {

                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 604).build();

                logger.error("Código de error: " + 604 +  ", Mensaje de error: " + ex.getMessage());
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
     * Este método permite modificar un el estado de un estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el objeto modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del estudio a modificar
     */
    @PUT
    @Path("/updateEstadoEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstadoEstudio(@PathParam("id") long id){

        logger.debug("Ingresando al método que actualiza el estado de un estudio");
        JsonObject dataObject;

        try {

            ModificarEstadoEstudioComando comando = Fabrica.crearComandoConId(ModificarEstadoEstudioComando.class, id);
            comando.execute();

            logger.debug("Saliendo del método que actualiza el estado de un estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException | PruebaExcepcion ex) {

            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 604).build();

            logger.error("Código de error: " + 604 +  ", Mensaje de error: " + ex.getMessage());
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
     * Este método permite eliminar un estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el mensaje de éxito y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del estudio a eliminar
     */
    @DELETE
    @Path("/deleteEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response eliminarEstudio(@PathParam("id") long id){
        logger.debug("Ingresando al método que permite eliminar un estudio");
        JsonObject dataObject;
            try {

                EliminarEstudioComando comando = Fabrica.crearComandoConId(EliminarEstudioComando.class, id);
                comando.execute();

                logger.debug("Saliendo del método que permite eliminar un estudio");
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

                logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (IllegalAccessException | PruebaExcepcion ex) {

                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 604).build();

                logger.error("Código de error: " + 604 +  ", Mensaje de error: " + ex.getMessage());
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
     * Este método permite obtener los detalles de un estudio
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de detalles de un estudio y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe
     */
    @GET
    @Path("/detallesEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response detallesEstudio(@PathParam("id") long id){

        logger.debug("Ingresando al método que permite obtener los detalles de un estudio");
        JsonObject dataObject;

        try {

            DetallesEstudioComando comando = Fabrica.crearComandoConId(DetallesEstudioComando.class, id);
            comando.execute();

            logger.debug("Saliendo del método que permite obtener los detalles de un estudio");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
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

    /**
     * Este método permite colocar una observacion
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de resultado exitoso y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se modifica un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     */
    @PUT
    @Path("/ingresarObservacion/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.TEXT_PLAIN )
    public Response colocarObservacionAnalista(@PathParam("id") long id, String observacion){

        logger.debug("Ingresando al método que permite al analista añadir una observación");
        JsonObject dataObject;

        try {

            ColocarObservacionAnalistaComando comando = Fabrica.crearComandoIdString(ColocarObservacionAnalistaComando.class, id, observacion);
            comando.execute();

            logger.debug("Saliendo del método que permite al analista añadir una observación");
            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

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
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 401).build();

            logger.error("Código de error: " + 401 +  ", Mensaje de error: " + ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException | PruebaExcepcion ex) {

            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 604).build();

            logger.error("Código de error: " + 604 +  ", Mensaje de error: " + ex.getMessage());
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

}
