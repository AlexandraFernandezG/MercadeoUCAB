package ucab.dsw.servicio;

import ucab.dsw.Response.RespuestasAbiertasResponse;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.accesodatos.DaoPreguntaEstudio;
import ucab.dsw.accesodatos.DaoRespuesta;
import ucab.dsw.entidades.PreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEstudio;
import ucab.dsw.entidades.Respuesta;

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
public class ReportesService extends AplicacionBase {

    @GET
    @Path("respuestasPregunta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<RespuestasAbiertasResponse> listarRespuestasAbiertas(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este método permite obtener las respuestas de las preguntas abiertas
         *
         * NOTA: Hay que realizar.
         */

        try {

            return null;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;

        }
    }

    @GET
    @Path("/porcentajeVF/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Object[]> porcentajeVeraderosFalsos(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este método permite obtener un poncentaje de respuestas de verdadero y
         * falso para un estudio.
         *
         * NOTA: Hay que acomodarlo separando ambos querys en dos metodos distintos
         */

        try {

            //Obtener la cantidad de registros V/F acorde al estudio suministrado
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoPreguntaEstudio daoPreguntaEstudio = new DaoPreguntaEstudio();
            List<Respuesta> listaRespuestas = daoRespuesta.findAll(Respuesta.class);
            int contador_registros = 0;

            for (Respuesta respuesta: listaRespuestas){

                if(respuesta.get_verdaderoFalso() != null){

                    long idRe = respuesta.get_preguntaEstudio().get_id();
                    PreguntaEstudio preguntaEstudio = daoPreguntaEstudio.find(idRe, PreguntaEstudio.class);

                    if(preguntaEstudio.get_estudio().get_id() == id){

                        contador_registros = contador_registros + 1;
                    }
                }
            }

            // Obtener el porcentaje de verdaderos y falsos de ese estudio

            String SQL_Verdadero = null;
            String SQL_Falso = null;

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            //Obtener el porcentaje de verdadero

            SQL_Verdadero = "SELECT COUNT(re._verdaderoFalso)*100/:contador_registros as Porcentaje FROM Respuesta as re, PreguntaEstudio as pe, Estudio as es " +
                    "WHERE es._id = pe._estudio._id and pe._id = re._preguntaEstudio._id and re._verdaderoFalso = 'Verdadero' and es._id = :id";

            Query query1 = entitymanager.createQuery(SQL_Verdadero);
            query1.setParameter("id", id);
            query1.setParameter("contador_registros", contador_registros);

            List<Object[]> porcentajeVerdadero = query1.getResultList();

            //Obtener el porcentaje falso

            SQL_Falso = "SELECT COUNT(re._verdaderoFalso)*100/:contador_registros as Porcentaje FROM Respuesta as re, PreguntaEstudio as pe, Estudio as es " +
                    "WHERE es._id = pe._estudio._id and pe._id = re._preguntaEstudio._id and re._verdaderoFalso = 'Falso' and es._id = :id";

            Query query2 = entitymanager.createQuery(SQL_Falso);
            query2.setParameter("id", id);
            query2.setParameter("contador_registros", contador_registros);

            List<Object[]> porcentajeFalso = query2.getResultList();

            List<Object[]> listaDefinitiva = new ArrayList<>();

            listaDefinitiva.add(porcentajeVerdadero.get(0));
            listaDefinitiva.add(porcentajeFalso.get(0));

            return listaDefinitiva;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;

        }

    }

}
