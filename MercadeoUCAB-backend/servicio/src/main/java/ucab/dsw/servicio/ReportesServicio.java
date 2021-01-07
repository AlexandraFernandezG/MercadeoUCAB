package ucab.dsw.servicio;

import ucab.dsw.response.ReporteMFResponse;
import ucab.dsw.response.ReporteVFResponse;
import ucab.dsw.response.RespuestasAbiertasResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.entidades.*;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/reportes" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ReportesServicio extends AplicacionBase {

    @GET
    @Path("respuestasPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<RespuestasAbiertasResponse> listarRespuestasAbiertas(@PathParam("id") long id) throws NullPointerException{


        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();


            String sqlQuery = "SELECT R._id AS idRespuestaAbierta, R._respuestaAbierta AS respuestaAbierta, PE._descripcion AS Pregunta" +
                    " FROM Respuesta AS R, PreguntaEstudio AS PES, PreguntaEncuesta AS PE WHERE " +
                    "R._preguntaEstudio._id = PES._id AND R._respuestaAbierta IS NOT NULL AND PE._id = PES._preguntaEncuesta._id AND " +
                    "PES._estudio._id =:id " +
                    "ORDER BY PES._id";
            Query query = entitymanager.createQuery( sqlQuery );
            query.setParameter("id", id);

            List<Object[]> respuestas = query.getResultList();
            List<RespuestasAbiertasResponse> ResponseListUpdate = new ArrayList<>(respuestas.size());

            for (Object[] r : respuestas) {
                ResponseListUpdate.add(new RespuestasAbiertasResponse((long)r[0], (String)r[1], (String)r[2]));
            }

            return ResponseListUpdate;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;

        }
    }

    @GET
    @Path("/porcentajeVF/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<ReporteVFResponse> porcentajeVeraderoFalso(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este método permite obtener un poncentaje de respuestas de verdadero y falso
         * para un estudio.
         *
         * NOTA: Este método funciona correctamente.
         */

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

            String porcentajeV = "Porcentaje Verdadero: ";
            String porcentajeF = "Porcentaje Falso: ";
            List<ReporteVFResponse> listaPorcentajes = new ArrayList<>();

            if (contador_registros != 0) {

                float porcentajeVerdadero = Math.round((contador_verdaderos * 100) / contador_registros);
                float porcentajeFalso = Math.round((contador_falsos * 100) / contador_registros);

                listaPorcentajes.add(new ReporteVFResponse(porcentajeV, porcentajeVerdadero, porcentajeF, porcentajeFalso));

            }
            else {

                listaPorcentajes.add(new ReporteVFResponse(porcentajeV, 0, porcentajeF, 0));
            }

            return listaPorcentajes;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;

        }

    }

    @GET
    @Path("/porcentajeGenero/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<ReporteMFResponse> listarPorcentajesGenero(@PathParam("id") long id) throws NullPointerException{

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

            String porcentajeM = "Porcentaje Masculino: ";
            String porcentajeF = "Porcentaje Femenino: ";
            List<ReporteMFResponse> listaPorcentajes = new ArrayList<>();

            if (contador_registros != 0){

                float porcentajeMasculino = Math.round((contador_masculino * 100) / contador_registros);
                float porcentajeFemenino = Math.round((contador_femenino * 100) / contador_registros);

                listaPorcentajes.add(new ReporteMFResponse(porcentajeM, porcentajeMasculino, porcentajeF, porcentajeFemenino));

            }
            else {

                listaPorcentajes.add(new ReporteMFResponse(porcentajeM, 0, porcentajeF, 0));
            }

            return listaPorcentajes;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;

        }

    }
}
