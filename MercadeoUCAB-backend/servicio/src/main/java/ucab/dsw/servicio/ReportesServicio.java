package ucab.dsw.servicio;

import ucab.dsw.response.RespuestasAbiertasResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.entidades.*;
import ucab.dsw.response.SimpleMultipleEscalaResponse;
import ucab.dsw.response.VerdaderoFalsoResponse;

import java.util.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "/reportes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ReportesServicio extends AplicacionBase {

    /**
     * Este reporte permite obtener solo las respuestas de las preguntas abiertas.
     * @author Emanuel Di Cristofaro y Gregg Spinetti
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las preguntas.
     */
    @GET
    @Path("respuestasPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarRespuestasAbiertas(@PathParam("id") long id) {

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        JsonObject dataObject;

        try {

            List<Object[]> respuestas = daoPreguntaEncuesta.obtenerPreguntasAbiertas(id);

            List<RespuestasAbiertasResponse> ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {
                ResponseListUpdate.add(new RespuestasAbiertasResponse((long)r[0], (String)r[1], (String)r[2]));
            }

            return Response.status(Response.Status.OK).entity(ResponseListUpdate).build();

        } catch (Exception ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", ex.getMessage())
                    .add("codigo", 400).build();

            return Response.status(Response.Status.BAD_REQUEST).entity(dataObject).build();
        }
    }

    /**
     * Este método permite obtener las cantidades de respuestas de verdadero y falso por pregunta
     * para un estudio.
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las estadisticas.
     * @throws NullPointerException si hay algun problema en el analisis
     */
    @GET
    @Path("/preguntasCantidadVF/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response preguntasCantidadVF(@PathParam("id") long id){

        JsonObject dataObject;
        long verdadero_result = 0;
        long falso_result = 0;

        try {

            //Listar todas las preguntas del estudio seleccionado
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            List<PreguntaEstudio> listaEstudioPregunta = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
            List<PreguntaEncuesta> listaPreguntasEstudio = new ArrayList<PreguntaEncuesta>();

            for (PreguntaEstudio preguntaEstudio : listaEstudioPregunta) {

                if (preguntaEstudio.get_estudio().get_id() == id) {

                    long idFk = preguntaEstudio.get_preguntaEncuesta().get_id();
                    DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
                    PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idFk, PreguntaEncuesta.class);
                    listaPreguntasEstudio.add(preguntaEncuesta);
                }
            }

            //Listar solamente las preguntas de tipo verdadero/falso
            List<PreguntaEncuesta> listaPreguntasVF = new ArrayList<>();

            for (PreguntaEncuesta preguntaEncuesta: listaPreguntasEstudio){

                if(preguntaEncuesta.get_tipoPregunta().equals("VerdaderoFalso")){

                    listaPreguntasVF.add(preguntaEncuesta);
                }
            }

            //Obtener las cantidades de verdadero y falso de cada pregunta
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            List<VerdaderoFalsoResponse> Cantidades = new ArrayList<>();

            for (PreguntaEncuesta preguntaEncuestaVF: listaPreguntasVF){

                List<Long> verdaderos = daoRespuesta.cantidadVerdaderosPregunta(preguntaEncuestaVF.get_id());
                List<Long> falsos = daoRespuesta.cantidadFalsosPregunta(preguntaEncuestaVF.get_id());

                verdadero_result = verdaderos.get(0);
                falso_result = falsos.get(0);

                Cantidades.add(new VerdaderoFalsoResponse(preguntaEncuestaVF.get_descripcion(), (int)verdadero_result, (int)falso_result));

                verdaderos.clear();
                falsos.clear();

            }

            return Response.status(Response.Status.OK).entity(Cantidades).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha podido ejecutar el análisis " + ex.getMessage())
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
     * Este método permite obtener las cantidades de respuestas simples por pregunta
     * para un estudio.
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las estadisticas.
     * @throws NullPointerException si hay algun problema en el analisis
     */
    @GET
    @Path("/preguntasCantidadSimple/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response preguntasCantidadSimple(@PathParam("id") long id){

        JsonObject dataObject;
        long cantidad_opcion = 0;

        try {

            //Listar todas las preguntas del estudio seleccionado
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            List<PreguntaEstudio> listaEstudioPregunta = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
            List<PreguntaEncuesta> listaPreguntasEstudio = new ArrayList<PreguntaEncuesta>();

            for (PreguntaEstudio preguntaEstudio : listaEstudioPregunta) {

                if (preguntaEstudio.get_estudio().get_id() == id) {

                    long idFk = preguntaEstudio.get_preguntaEncuesta().get_id();
                    DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
                    PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(idFk, PreguntaEncuesta.class);
                    listaPreguntasEstudio.add(preguntaEncuesta);
                }
            }

            //Listar solamente las preguntas de tipo Simple
            List<PreguntaEncuesta> listaPreguntasSimples = new ArrayList<>();

            for (PreguntaEncuesta preguntaEncuesta: listaPreguntasEstudio){

                if(preguntaEncuesta.get_tipoPregunta().equals("Simple")){

                    listaPreguntasSimples.add(preguntaEncuesta);
                }
            }

            //Obtener la cantidad de selecciones de las preguntas simples
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoRespuestaPregunta daoRespuestaPregunta = new DaoRespuestaPregunta();
            List<RespuestaPregunta> listaRespuestasPregunta = daoRespuestaPregunta.findAll(RespuestaPregunta.class);
            List<Long> cantidadOpcion = new ArrayList<>();
            //List<String> opciones = new ArrayList<>();
            //List<Integer> opcionesInt = new ArrayList<>();
            List<SimpleMultipleEscalaResponse> Cantidades = new ArrayList<>();

            for (PreguntaEncuesta preguntaEncuestaSimple: listaPreguntasSimples){

                //Guardar todas las opciones de la pregunta
                for (RespuestaPregunta respuestaPregunta: listaRespuestasPregunta){

                    if(respuestaPregunta.get_preguntaEncuesta().get_id() == preguntaEncuestaSimple.get_id()){

                        //opciones.add(respuestaPregunta.get_nombre());
                        cantidadOpcion = daoRespuesta.cantidadSimple(preguntaEncuestaSimple.get_id(), respuestaPregunta.get_nombre());
                        cantidad_opcion = cantidadOpcion.get(0);
                        //opcionesInt.add((int)cantidad_opcion);
                        Cantidades.add(new SimpleMultipleEscalaResponse(preguntaEncuestaSimple.get_descripcion(), respuestaPregunta.get_nombre(), (int)cantidad_opcion));
                    }
                    cantidadOpcion.clear();

                }
            }

            return Response.status(Response.Status.OK).entity(Cantidades).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha podido ejecutar el análisis " + ex.getMessage())
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
     * Este método permite obtener un poncentaje de respuestas segun los generos del encuestado
     * para un estudio.
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las estadisticas.
     * @throws NullPointerException si hay algun problema en el analisis
     */
    @GET
    @Path("/porcentajeGenero/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response listarPorcentajesGenero(@PathParam("id") long id) {

        JsonObject dataObject;

        try {

            //Obtener las respuestas del estudio

            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            List<Respuesta> allRespuesta = daoRespuesta.findAll(Respuesta.class);
            List<PreguntaEstudio> allPreguntaEstudio = daoPreguntaEstudio.findAll(PreguntaEstudio.class);
            List<Respuesta> listaRespuestasEstudio = new ArrayList<>();

            for (PreguntaEstudio preguntaEstudio: allPreguntaEstudio){

                if (preguntaEstudio.get_estudio().get_id() == id){

                    long idPE = preguntaEstudio.get_id();

                    for (Respuesta respuesta: allRespuesta){

                        if (respuesta.get_preguntaEstudio().get_id() == idPE){

                            listaRespuestasEstudio.add(respuesta);
                        }
                    }

                }
            }

            //Ahora calculamos la cantidad de personas por genero con las respuestas

            DaoUsuario daoUsuario = new DaoUsuario();
            DaoInformacion daoInformacion = new DaoInformacion();
            List<Usuario> allUsuario = daoUsuario.findAll(Usuario.class);
            List<Informacion> allInformacion = daoInformacion.findAll(Informacion.class);
            int contador_registros = 0;
            int contador_masculino = 0;
            int contador_femenino = 0;

            for (Respuesta respuesta: listaRespuestasEstudio){

                long idU = respuesta.get_usuario().get_id();

                for(Usuario usuario: allUsuario){

                    if(usuario.get_id() == idU){

                        for(Informacion informacion: allInformacion){

                            if(informacion.get_usuario().get_id() == idU){

                                if(informacion.get_genero().equals("Masculino")){

                                    contador_registros = contador_registros + 1;
                                    contador_masculino = contador_masculino + 1;
                                }
                                else if (informacion.get_genero().equals("Femenino")){

                                    contador_registros = contador_registros + 1;
                                    contador_femenino = contador_femenino + 1;
                                }
                            }
                        }
                    }
                }
            }

            //Calculamos los porcentajes
            float porcentajeMasculino = 0;
            float porcentajeFemenino = 0;

            if (contador_registros != 0) {

                porcentajeMasculino = Math.round((contador_masculino * 100) / contador_registros);
                porcentajeFemenino = Math.round((contador_femenino * 100) / contador_registros);

            }

            JsonObject dataAnalisis = Json.createObjectBuilder()
                    .add("Porcentaje Hombres", porcentajeMasculino)
                    .add("Porcentaje Mujeres", porcentajeFemenino).build();

            return Response.status(Response.Status.OK).entity(dataAnalisis).build();

        } catch (NullPointerException ex) {

            dataObject = Json.createObjectBuilder()
                    .add("estado", "Error")
                    .add("excepcion", "No se ha podido ejecutar el análisis " + ex.getMessage())
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
}
