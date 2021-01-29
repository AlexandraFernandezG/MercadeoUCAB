package ucab.dsw.servicio;
import org.eclipse.persistence.exceptions.DatabaseException;
import ucab.dsw.accesodatos.DaoEstudio;
import ucab.dsw.accesodatos.DaoInformacion;
import ucab.dsw.accesodatos.DaoSolicitudEstudio;
import ucab.dsw.accesodatos.DaoUsuario;
import ucab.dsw.dtos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.excepciones.PruebaExcepcion;
import ucab.dsw.response.PreguntasResponse;
import ucab.dsw.response.UsuarioResponse;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
    public Response listarEstudios() throws NullPointerException {

        JsonObject dataObject;
        JsonArrayBuilder estudiosArrayJson = Json.createArrayBuilder();
        DaoEstudio daoEstudio = new DaoEstudio();

        try {
            List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);

            return Response.status(Response.Status.OK).entity(listaEstudios).build();


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
    public Response consultarEstudio(@PathParam("id") long id) throws NullPointerException{

        JsonObject dataObject;
        DaoEstudio daoEstudio = new DaoEstudio();

        try {
            Estudio estudio_consultado = daoEstudio.find(id, Estudio.class);
            return Response.status(Response.Status.OK).entity(estudio_consultado).build();

        } catch (NullPointerException ex) {

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
     * Este método permite obtener todas los estudios activos.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * arreglo de estudios activos y en tal caso obtener una excepcion si aplica.
     */
    @GET
    @Path("/mostrarEstudiosActivos")
    @Produces( MediaType.APPLICATION_JSON )
    public Response estudiosActivos() {
        DaoEstudio daoEstudio = new DaoEstudio();
        List<Estudio> listaEstudios = daoEstudio.findAll(Estudio.class);
        List<Estudio> listaEstudiosActivos = new ArrayList<Estudio>();
        JsonObject dataObject;
        
        try {

            for (Estudio estudio : listaEstudios) {

                if (estudio.get_estatus().equals("Activo")) {
                    listaEstudiosActivos.add(estudio);
                }
            }
            return Response.status(Response.Status.OK).entity(listaEstudiosActivos).build();

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
        List<UsuarioResponse> listaEncuestadosSolicitud = new ArrayList<>();
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        DaoUsuario daoUsuario = new DaoUsuario();
        DaoInformacion daoInformacion = new DaoInformacion();

        try {

            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

            //Listar todos los usuarios encuestados
            List<Object[]> listaUsuariosEncuestados = daoUsuario.listarEncuestadosEstudio();
            List<UsuarioResponse> listaUsuariosEncuestadosResult = new ArrayList<>(listaUsuariosEncuestados.size());
            List<Informacion> listaInformacion = daoInformacion.findAll(Informacion.class);

            for (Object[] user : listaUsuariosEncuestados) {

                listaUsuariosEncuestadosResult.add(new UsuarioResponse((long) user[0], (String) user[1], (String) user[2], (String) user[3], (String) user[4]));
            }

            //Recorremos la lista de encuestados y hacemos el match
            for (UsuarioResponse usuarioEncuestado: listaUsuariosEncuestadosResult) {

                for (Informacion informacion : listaInformacion) {

                    if(solicitudEstudio.get_genero().equals(informacion.get_genero()) && solicitudEstudio.get_estadoCivil().equals(informacion.get_estadoCivil()) &&
                            solicitudEstudio.get_cantidadPersonas() == informacion.get_cantidadPersonas() && informacion.get_usuario().get_id() == usuarioEncuestado.getId()) {

                        listaEncuestadosSolicitud.add(usuarioEncuestado);
                    }
                }
            }

            return Response.status(Response.Status.OK).entity(listaEncuestadosSolicitud).build();

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
     * Este método permite insertar un estudio con sus respectivos encuestados y preguntas
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el estudio insertado y en tal caso obtener una excepcion si aplica.
     * @throws PruebaExcepcion esta excepcion permite obtener errores generales.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param estudioDto el objeto categoria que el sistema desea insertar o crear.
     */
    @POST
    @Path("/addEstudio")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addEstudios(EstudioDto estudioDto, List<UsuarioResponse> listaEncuestados, List<PreguntasResponse> listaPreguntas) {

        JsonObject dataObject;
        EstudioDto resultado = new EstudioDto();
        ucab.dsw.servicio.UsuarioEstudioServicio servicio = new ucab.dsw.servicio.UsuarioEstudioServicio();
        ucab.dsw.servicio.PreguntasEstudioServicio servicio1 = new ucab.dsw.servicio.PreguntasEstudioServicio();

        try {

            DaoEstudio daoEstudio = new DaoEstudio();
            Estudio estudio = new Estudio();

            DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();

            estudio.set_nombre(estudioDto.getNombre());
            estudio.set_tipoInstrumento(estudioDto.getTipoInstrumento());
            estudio.set_fechaInicio(estudioDto.getFechaInicio());
            estudio.set_fechaFin(estudioDto.getFechaFin());
            estudio.set_estado(estudioDto.getEstado());
            estudio.set_estatus(estudioDto.getEstatus());
            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(estudioDto.getSolicitudEstudioDto().getId(), SolicitudEstudio.class);
            Usuario usuario = daoUsuario.find(estudioDto.getUsuarioDto().getId(), Usuario.class);
            estudio.set_solicitudEstudio(solicitudEstudio);
            estudio.set_usuario(usuario);
            Estudio resul = daoEstudio.insert(estudio);
            resultado.setId(resul.get_id());

            //Insertar encuestados al estudio
            UsuarioEstudioDto usuarioEstudioDto = new UsuarioEstudioDto();

            //Recorremos la lista de encuestados y insertamos
            for (UsuarioResponse usuarioEncuestado: listaEncuestados) {

                usuarioEstudioDto.setEstatus("Activo");
                EstudioDto idEstudio = new EstudioDto(resul.get_id());
                usuarioEstudioDto.setEstudioDto(idEstudio);
                UsuarioDto idUsuario = new UsuarioDto(usuarioEncuestado.getId());
                usuarioEstudioDto.setUsuarioDto(idUsuario);
                servicio.addUsuarioEstudio(usuarioEstudioDto);

            }

            //Recorremos e insertamos las preguntas con el estudio
            PreguntaEstudioDto preguntaEstudioDto = new PreguntaEstudioDto();

            for(PreguntasResponse preguntaEncuesta: listaPreguntas){

                preguntaEstudioDto.setEstatus("Activo");
                EstudioDto idEstudio2 = new EstudioDto(resul.get_id());
                preguntaEstudioDto.setEstudioDto(idEstudio2);
                PreguntaEncuestaDto idPregunta = new PreguntaEncuestaDto(preguntaEncuesta.getIdPregunta());
                preguntaEstudioDto.setPreguntaEncuestaDto(idPregunta);
                servicio1.addPreguntaEstudio(preguntaEstudioDto);
            }

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
                    .add("excepcion", "No se ha encontrado el estudio: " + ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();

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

        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;
            try {
                Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

                estudio_modificar.set_nombre(estudioDto.getNombre());
                estudio_modificar.set_tipoInstrumento(estudioDto.getTipoInstrumento());
                estudio_modificar.set_fechaInicio(estudioDto.getFechaInicio());
                estudio_modificar.set_fechaFin(estudioDto.getFechaFin());
                estudio_modificar.set_estado(estudioDto.getEstado());
                estudio_modificar.set_estatus(estudioDto.getEstatus());
                daoEstudio.update(estudio_modificar);

                return Response.status(Response.Status.OK).entity(estudio_modificar).build();

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

    /**
     * Este método permite modificar un el estado de un estudio
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @return Este metodo retorna un objeto de tipo Json con el
     * con el objeto modificado y en tal caso obtener una excepcion si aplica.
     * @throws NullPointerException esta excepcion se aplica cuando se pasa un id que no existe.
     * @throws PersistenceException si se inserta un estudio duplicado.
     * @throws DatabaseException Si existe algun problema con la conexion de la base de datos.
     * @param id el id del estudio a modificar
     */
    @PUT
    @Path("/updateEstadoEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response modificarEstadoEstudio(@PathParam("id") long id){

        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;
        try {
            Estudio estudio_modificar = daoEstudio.find(id, Estudio.class);

            if (estudio_modificar.get_estado() == "En espera")
                estudio_modificar.set_estado("En proceso");
            else if (estudio_modificar.get_estado() == "en espera")
                estudio_modificar.set_estado("en proceso");
            else if (estudio_modificar.get_estado() == "En proceso")
                estudio_modificar.set_estado("Finalizado");
            else if (estudio_modificar.get_estado() == "en proceso")
                estudio_modificar.set_estado("finalizado");
            daoEstudio.update(estudio_modificar);

            return Response.status(Response.Status.OK).entity(estudio_modificar).build();

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
                Estudio estudio_eliminar = daoEstudio.find(id, Estudio.class);
                daoEstudio.delete(estudio_eliminar);
                return Response.status(Response.Status.OK).entity(estudio_eliminar).build();

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

            }

    }
}
