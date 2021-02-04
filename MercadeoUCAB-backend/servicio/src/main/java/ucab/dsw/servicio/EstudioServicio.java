package ucab.dsw.servicio;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.*;
import ucab.dsw.comando.Estudio.*;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.fabrica.Fabrica;
import ucab.dsw.mappers.MapperEstudio;
import ucab.dsw.response.PreguntasResponse;
import ucab.dsw.response.UsuarioResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/estudio" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EstudioServicio extends AplicacionBase {


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

        JsonObject dataObject;

        try {

            ListarEstudiosComando comando = Fabrica.crear(ListarEstudiosComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();


        } catch (Exception ex){

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

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

        JsonObject dataObject;

        try {

            ConsultarEstudioComando comando = Fabrica.crearComandoConId(ConsultarEstudioComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
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
     * Este método permite obtener todas los estudios activos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de estudios activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarEstudiosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response estudiosActivos() {

        JsonObject dataObject;
        
        try {

            MostrarEstudiosActivosComando comando = Fabrica.crear(MostrarEstudiosActivosComando.class);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

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

        JsonObject dataObject;

        try {

            ListarSolicitudEncuestadosComando comando = Fabrica.crearComandoConId(ListarSolicitudEncuestadosComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        }  catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el estudio: " + ex.getMessage())
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

         JsonObject dataObject;

         try {

             InsertarEncuestadosEstudioComando comando = Fabrica.crearComandoListIdUsuarioResponse(InsertarEncuestadosEstudioComando.class, id, listaEncuestados);
             comando.execute();

             return Response.status(Response.Status.OK).entity(comando.getResult()).build();

         } catch (Exception ex) {

             dataObject = Json.createObjectBuilder()
                     .add("estado", "Error")
                     .add("excepcion", ex.getMessage())
                     .add("codigo", 400).build();

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

        JsonObject dataObject;

        try {

            InsertarPreguntasEstudioComando comando = Fabrica.crearComandoListIdPreguntasResponse(InsertarPreguntasEstudioComando.class, id, listaPreguntas);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
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

        JsonObject dataObject;


        try {

            Estudio estudio = MapperEstudio.mapDtoToEntityInsert(estudioDto);
            AddEstudioComando comando = Fabrica.crearComandoConEntity(AddEstudioComando.class, estudio);
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
                    .add("excepcion", "No se ha encontrado el estudio: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }  catch (PruebaExcepcion ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
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


        JsonObject dataObject;

            try {

                Estudio estudio = MapperEstudio.mapDtoToEntityUpdate(id, estudioDto);
                ModificarEstudioComando comando = Fabrica.crearComandoBoth(ModificarEstudioComando.class, id, estudio);
                comando.execute();

                return Response.status(Response.Status.OK).entity(comando.getResult()).build();

            } catch (PersistenceException | DatabaseException ex){

                dataObject= Json.createObjectBuilder()
                        .add("estado","Error")
                        .add("mensaje", ex.getMessage())
                        .add("codigo",500).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();

            } catch (NullPointerException ex) {

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (IllegalAccessException | PruebaExcepcion ex) {

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
                        .add("codigo", 600).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InvocationTargetException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 600).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (Exception ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 400).build();

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


        JsonObject dataObject;

        try {

            ModificarEstadoEstudioComando comando = Fabrica.crearComandoConId(ModificarEstadoEstudioComando.class, id);
            comando.execute();

            return Response.status(Response.Status.OK).entity(comando.getResult()).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el estudio: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        } catch (IllegalAccessException | PruebaExcepcion ex) {

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
                    .add("codigo", 600).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (InvocationTargetException ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 600).build();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

        } catch (Exception ex) {
            ex.printStackTrace();

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

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

        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;
            try {

                EliminarEstudioComando comando = Fabrica.crearComandoConId(EliminarEstudioComando.class, id);
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
                        .add("excepcion", "No se ha encontrado el estudio: " + ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

            } catch (IllegalAccessException | PruebaExcepcion ex) {

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
                        .add("codigo", 600).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (InvocationTargetException ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 600).build();

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(dataObject).build();

            } catch (Exception ex) {
                ex.printStackTrace();

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Error")
                        .add("excepcion", ex.getMessage())
                        .add("codigo", 400).build();

                return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
            }

    }

    /**
     * Este método permite verificar si todos los encuestados respondieron las encuestas de un estudio
     * @author Emanuel Di Cristofaro
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de resultado exitoso y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     */
    @PUT
    @Path("/updateUsuarioEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response cambiarEstatusUsuarioEstudio(@PathParam("id") long id){

        JsonObject dataObject;
        DaoUsuarioEstudio daoUsuarioEstudio = new DaoUsuarioEstudio();
        DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
        DaoRespuesta daoRespuesta = new DaoRespuesta();
        List<UsuarioEstudio> listaUsuarioEstudio = daoUsuarioEstudio.findAll(UsuarioEstudio.class);
        List<PreguntaEstudio> listaPreguntas = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
        List<Respuesta> listaRespuestas = daoRespuesta.findAll(Respuesta.class);
        int cantidadPreguntas = 0;
        int cantidadRespuesta = 0;
        int cantidadMultiples = 0;
        long fkB = 0;

        try {

            //Calcular cantidad de preguntas del estudio
            for(PreguntaEstudio preguntaEstudio: listaPreguntas){

                if(preguntaEstudio.get_estudio().get_id() == id){

                    cantidadPreguntas = cantidadPreguntas + 1;
                }
            }

            for (UsuarioEstudio usuarioEncuestado: listaUsuarioEstudio){

                if(usuarioEncuestado.get_estudio().get_id() == id){

                    for(Respuesta respuesta: listaRespuestas){

                        if(respuesta.get_usuario().get_id() == usuarioEncuestado.get_usuario().get_id()){

                            for(PreguntaEstudio preguntaEstudioR: listaPreguntas){

                                if(preguntaEstudioR.get_id() == respuesta.get_preguntaEstudio().get_id() && preguntaEstudioR.get_estudio().get_id() == id){

                                    if(respuesta.get_respuestaMultiple() != null) {

                                        if(respuesta.get_preguntaEstudio().get_id() != fkB) {

                                            cantidadMultiples = cantidadMultiples + 1;
                                            fkB = respuesta.get_preguntaEstudio().get_id();
                                        }

                                    } else {

                                        cantidadRespuesta = cantidadRespuesta + 1;
                                    }
                                }
                            }

                        }
                    }
                }

            if(cantidadPreguntas == (cantidadRespuesta+cantidadMultiples)) {

                cantidadRespuesta = 0;
                cantidadMultiples = 0;
                UsuarioEstudio usuarioEstudio_modificar = daoUsuarioEstudio.find(usuarioEncuestado.get_id(), UsuarioEstudio.class);
                usuarioEstudio_modificar.set_estatus("Respondido");
                daoUsuarioEstudio.update(usuarioEstudio_modificar);

            } else {

                cantidadRespuesta = 0;
                cantidadMultiples = 0;
            }

            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "operacion realizada con éxito")
                    .add("Cantidad Respuestas", 200).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (PersistenceException | DatabaseException ex){

            dataObject= Json.createObjectBuilder()
                    .add("estado","Error")
                    .add("mensaje", ex.getMessage())
                    .add("codigo",500).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el estudio: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

        }


    }
}
