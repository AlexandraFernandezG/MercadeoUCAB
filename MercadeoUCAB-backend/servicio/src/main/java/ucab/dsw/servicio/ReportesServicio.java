package ucab.dsw.servicio;

import ucab.dsw.response.RespuestasAbiertasResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.entidades.*;

import java.util.*;
import javax.json.Json;
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
     * Este método permite obtener un poncentaje de respuestas de verdadero y falso
     * para un estudio.
     * @author Emanuel Di Cristofaro
     * @param id id del estudio seleccionado por el administrador o el analista para obtener las estadisticas.
     * @throws NullPointerException si hay algun problema en el analisis
     */
    @GET
    @Path("/porcentajeVF/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Response porcentajeVeraderoFalso(@PathParam("id") long id) throws NullPointerException{

        JsonObject dataObject;

        try {

            //Obtener la cantidad de registros V/F acorde al estudio suministrado
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            List<Respuesta> listaRespuestas = daoRespuesta.findAll(Respuesta.class);
            int contador_registros = 0;
            int contador_verdaderos = 0;
            int contador_falsos = 0;

            for (Respuesta respuesta: listaRespuestas){

                if(respuesta.get_verdaderoFalso() != null){

                    long idRe = respuesta.get_preguntaEstudio().get_id();
                    PreguntaEstudio preguntaEstudio = daoPreguntaEstudio.find(idRe, PreguntaEstudio.class);

                    if(preguntaEstudio.get_estudio().get_id() == id && respuesta.get_verdaderoFalso().equals("Verdadero")){

                        contador_verdaderos = contador_verdaderos + 1;
                        contador_registros = contador_registros + 1;
                    }
                    else if(preguntaEstudio.get_estudio().get_id() == id && respuesta.get_verdaderoFalso().equals("Falso")){

                        contador_falsos = contador_falsos + 1;
                        contador_registros = contador_registros + 1;
                    }
                }
            }

            //Calcular los porcentajes de verdaderos y falsos
            float porcentajeVerdadero = 0;
            float porcentajeFalso = 0;

            if (contador_registros != 0) {

                porcentajeVerdadero = Math.round((contador_verdaderos * 100) / contador_registros);
                porcentajeFalso = Math.round((contador_falsos * 100) / contador_registros);

            }

            JsonObject dataAnalisis = Json.createObjectBuilder()
                    .add("Porcentaje Verdadero", porcentajeVerdadero)
                    .add("Porcentaje Falso", porcentajeFalso).build();

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
    public Response listarPorcentajesGenero(@PathParam("id") long id) throws NullPointerException{

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
