package ucab.dsw.servicio;

import lombok.extern.java.Log;
import ucab.dsw.response.EstudiosResponse;
import ucab.dsw.response.PreguntasResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.dtos.EstudioDto;
import ucab.dsw.dtos.PreguntaEncuestaDto;
import ucab.dsw.dtos.PreguntaEstudioDto;
import ucab.dsw.entidades.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path( "/sugerencias" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SugerenciasServicio extends AplicacionBase {


    /**
     * Este método permite transformar la fecha de date a string debido a una exigencia del Json
     * @author Emanuel Di Cristofaro
     * @param fecha Parsear la fecha de date a string para poder enviar el Json.
     */
    public String devolverFecha(Date fecha){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_nac = sdf.format(fecha);

        return fecha_nac;
    }

    /**
     * Este método permite obtener las preguntas recomendadas en base a un estudio seleccionado
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para recomendar las preguntas.
     */
    @GET
    @Path("/preguntasEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPreguntasEstudioRecomendadas(@PathParam("id") long id) {

        JsonObject dataObject;

        try {

            DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
            List<Object[]> listaPreguntas = daoPreguntaEncuesta.listarPreguntasEstudio(id);
            List<PreguntasResponse> listaPreguntasRecomendadas = new ArrayList<>(listaPreguntas.size());
            JsonArrayBuilder preguntasArrayJson = Json.createArrayBuilder();

            for (Object[] pre: listaPreguntas){

                listaPreguntasRecomendadas.add(new PreguntasResponse((long)pre[0], (String)pre[1], (String)pre[2], (String)pre[3]));

            }

            for (PreguntasResponse pr: listaPreguntasRecomendadas){

                JsonObject pregunta = Json.createObjectBuilder()
                        .add("id", pr.getIdPregunta())
                        .add("descripcion", pr.getDescripcionPregunta())
                        .add("tipoPregunta", pr.getTipoPregunta())
                        .add("estatus", pr.getEstatusPregunta()).build();

                preguntasArrayJson.add(pregunta);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operacion realizada con éxito")
                    .add("codigo", 200)
                    .add("Preguntas recomendadas", preguntasArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();


        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener una lista de estudios recomendados acorde a una solicitud de estudio
     * @author Emanuel Di Cristofaro
     * @param id id de la solicitud de estudio para recomendar estudios.
     * @throws NullPointerException si no se encuentra la solicitud de estudio
     */
    @GET
    @Path("/solicitudEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosRecomendados(@PathParam("id") long id) {

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;
        JsonArrayBuilder estudiosArrayJson = Json.createArrayBuilder();

        try {

            SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

                String genero = solicitudEstudio.get_genero();
                String estadoCivil = solicitudEstudio.get_estadoCivil();
                int cantidadPersonas = solicitudEstudio.get_cantidadPersonas();

                List<Object[]> listaEstudios = daoEstudio.listaEstudiosSolicitud(genero, estadoCivil, cantidadPersonas);
                List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

                for (Object[] est : listaEstudios) {

                    listaEstudiosRecomendados.add(new EstudiosResponse((long)est[0], (String)est[1], (String)est[2], (Date)est[3], (Date)est[4], (String)est[5], (String)est[6]));
                }

                for (EstudiosResponse er: listaEstudiosRecomendados){

                    JsonObject estudio = Json.createObjectBuilder()
                            .add("id", er.getIdEstudio())
                            .add("nombre", er.getNombreEstudio())
                            .add("tipoInstrumento", er.getTipoInstrumentoEstudio())
                            .add("fechaInicio", devolverFecha(er.getFechaInicioEstudio()))
                            .add("fechaFin", devolverFecha(er.getFechaFinEstudio()))
                            .add("estado", er.getEstadoEstudio())
                            .add("estatus", er.getEstatusEstudio()).build();

                    estudiosArrayJson.add(estudio);
                }

                dataObject = Json.createObjectBuilder()
                        .add("estado", "Operacion realizada con éxito")
                        .add("codigo", 200)
                        .add("Estudios recomendados", estudiosArrayJson).build();

                return Response.status(Response.Status.OK).entity(dataObject).build();


        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado la solicitud " + ex.getMessage())
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
     * Este método permite obtener los estudios recomendados partiendo de un encuestado
     * @author Emanuel Di Cristofaro
     * @param id id del usuario Encuestado que permite obtener los estudios recomendados
     */
    @GET
    @Path("/estudiosEncuestado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosEncuestado(@PathParam("id") long id) {

        DaoInformacion daoInformacion = new DaoInformacion();
        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;
        JsonArrayBuilder estudiosArrayJson = Json.createArrayBuilder();
        String genero = null;
        Date fechaNacimiento = null;
        String estadoCivil = null;
        int cantidadPersonas = 0;
        int edad = 0;

        try {

            List<Informacion> listaInformacion = daoInformacion.findAll(Informacion.class);

                for (Informacion informacion: listaInformacion) {

                    if(informacion.get_usuario().get_id() == id) {

                        genero = informacion.get_genero();
                        fechaNacimiento = informacion.get_fechaNacimiento();
                        estadoCivil = informacion.get_estadoCivil();
                        cantidadPersonas = informacion.get_cantidadPersonas();

                        //Primero pasamos la fecha de nacimiento a string
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String fecha_nac = sdf.format(fechaNacimiento);

                        //Formato de la fecha para la operacion de la edad
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        //Parseamos la fecha y obtener la fecha actual.
                        LocalDate fechaNac = LocalDate.parse(fecha_nac, fmt);
                        LocalDate ahora = LocalDate.now();

                        //Calcular la edad
                        Period periodo = Period.between(fechaNac, ahora);

                        //Edad de la persona
                        edad = periodo.getYears();

                    }
                }

                List<Object[]> listaEstudios = daoEstudio.listarEstudiosEncuestado(genero, estadoCivil, cantidadPersonas, edad);

                List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

                for (Object[] est : listaEstudios) {

                    listaEstudiosRecomendados.add(new EstudiosResponse((long) est[0], (String)est[1], (String)est[2], (Date)est[3], (Date)est[4], (String)est[5], (String)est[6]));
                }

            for (EstudiosResponse er: listaEstudiosRecomendados){

                JsonObject estudio = Json.createObjectBuilder()
                        .add("id", er.getIdEstudio())
                        .add("nombre", er.getNombreEstudio())
                        .add("tipoInstrumento", er.getTipoInstrumentoEstudio())
                        .add("fechaInicio", devolverFecha(er.getFechaInicioEstudio()))
                        .add("fechaFin", devolverFecha(er.getFechaFinEstudio()))
                        .add("estado", er.getEstadoEstudio())
                        .add("estatus", er.getEstatusEstudio()).build();

                estudiosArrayJson.add(estudio);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operacion realizada con éxito")
                    .add("codigo", 200)
                    .add("Estudios recomendados", estudiosArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }

    }

    /**
     * Este método permite duplicar un estudio recomendado en base a una solicitud.
     * @author Emanuel Di Cristofaro
     * @param idSE: Es el id de la solicitud de estudio inicial
     * @param idE: Es el id del estudio recomendado seleccionado
     * @param idU: Es el id del usuario para el estudio (En este caso el analista o al admin)
     * @throws NullPointerException si no se encuentra el estudio recomendado
     */
    @POST
    @Path("/insertarEstudioRecomendado/{idSE}/{idE}/{idU}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response insertarEstudioRecomendado(@PathParam("idSE") long idSE, @PathParam("idE") long idE, @PathParam("idU") long idU) throws Exception{

        JsonObject dataObject;
        DaoEstudio daoEstudio = new DaoEstudio();

        try {

            //Encontrar el estudio recomendado
            Estudio estudio_recomendado = daoEstudio.find(idE, Estudio.class);
            Estudio estudio_nuevo = new Estudio();

            estudio_nuevo.set_nombre(estudio_recomendado.get_nombre());
            estudio_nuevo.set_tipoInstrumento(estudio_recomendado.get_tipoInstrumento());
            estudio_nuevo.set_fechaInicio(estudio_recomendado.get_fechaInicio());
            estudio_nuevo.set_fechaFin(estudio_recomendado.get_fechaFin());
            estudio_nuevo.set_estado(estudio_recomendado.get_estado());
            estudio_nuevo.set_estatus(estudio_recomendado.get_estatus());
            SolicitudEstudio solicitudEstudio = new SolicitudEstudio(idSE);
            estudio_nuevo.set_solicitudEstudio(solicitudEstudio);
            Usuario usuario = new Usuario(idU);
            estudio_nuevo.set_usuario(usuario);

            //Insertar nuevo estudio
            daoEstudio.insert(estudio_nuevo);

            //Obtener las preguntas del estudio recomendado
            PreguntasEstudioServicio servicio = new PreguntasEstudioServicio();

            List<PreguntaEncuesta> listaPreguntasRecomendado = servicio.listarPreguntasEstudio(idE);

            //Insertar las preguntas recomendadas en el nuevo estudio
            List<Estudio> allEstudios = daoEstudio.findAll(Estudio.class);
            PreguntaEstudioDto preguntaEstudiodto = new PreguntaEstudioDto();

            Estudio estudio = allEstudios.get(allEstudios.size() - 1);

            if(estudio.get_solicitudEstudio().get_id() == idSE){

                long idEN = estudio.get_id();

                for (PreguntaEncuesta preguntaEncuesta: listaPreguntasRecomendado){

                    long idPR = preguntaEncuesta.get_id();
                    PreguntaEncuestaDto preguntaEncuesta_insert = new PreguntaEncuestaDto(idPR);
                    preguntaEstudiodto.setPreguntaEncuestaDto(preguntaEncuesta_insert);
                    EstudioDto estudio_insert = new EstudioDto(idEN);
                    preguntaEstudiodto.setEstudioDto(estudio_insert);
                    preguntaEstudiodto.setEstatus("Activo");
                    servicio.addPreguntaEstudio(preguntaEstudiodto);
                }
            }

            JsonObject estudioRecomendado = Json.createObjectBuilder()
                    .add("nombre", estudio_recomendado.get_nombre())
                    .add("tipoInstrumento", estudio_recomendado.get_tipoInstrumento())
                    .add("fechaInicio", devolverFecha(estudio_recomendado.get_fechaInicio()))
                    .add("fechaFin", devolverFecha(estudio_recomendado.get_fechaInicio()))
                    .add("estado", estudio_recomendado.get_estado())
                    .add("estatus", estudio_recomendado.get_estatus()).build();


            dataObject = Json.createObjectBuilder()
                    .add("estado", "El estudio se ha insertado correctamente")
                    .add("codigo", 200)
                    .add("Estudios recomendados", estudioRecomendado).build();


            return Response.status(Response.Status.OK).entity(dataObject).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha encontrado el estudio " + ex.getMessage())
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
     * Este método permite obtener los estudios recomendados partiendo de un cliente
     * @author Emanuel Di Cristofaro
     * @param id id del usuario Cliente que permite obtener los estudios recomendados
     */
    @GET
    @Path("/estudiosCliente/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarEstudiosCliente(@PathParam("id") long id) throws NullPointerException{

        DaoEstudio daoEstudio = new DaoEstudio();
        JsonObject dataObject;
        JsonArrayBuilder estudiosArrayJson = Json.createArrayBuilder();

        try {

            List<Object[]> listaEstudios = daoEstudio.listarEstudiosClientes(id);

            List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

            for (Object[] eC: listaEstudios){

                listaEstudiosRecomendados.add(new EstudiosResponse((long)eC[0], (String)eC[1], (String)eC[2], (Date)eC[3], (Date)eC[4], (String)eC[5], (String)eC[6]));
            }

            for (EstudiosResponse er: listaEstudiosRecomendados){

                JsonObject estudio = Json.createObjectBuilder()
                        .add("id", er.getIdEstudio())
                        .add("nombre", er.getNombreEstudio())
                        .add("tipoInstrumento", er.getTipoInstrumentoEstudio())
                        .add("fechaInicio", devolverFecha(er.getFechaInicioEstudio()))
                        .add("fechaFin", devolverFecha(er.getFechaFinEstudio()))
                        .add("estado", er.getEstadoEstudio())
                        .add("estatus", er.getEstatusEstudio()).build();

                estudiosArrayJson.add(estudio);
            }

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Operacion realizada con éxito")
                    .add("codigo", 200)
                    .add("Estudios recomendados", estudiosArrayJson).build();

            return Response.status(Response.Status.OK).entity(dataObject).build();


        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

}
