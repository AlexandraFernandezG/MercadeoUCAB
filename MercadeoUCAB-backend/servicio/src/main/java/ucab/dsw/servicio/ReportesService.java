package ucab.dsw.servicio;

import ucab.dsw.Response.RespuestasAbiertasResponse;
import ucab.dsw.accesodatos.DaoPreguntaEncuesta;
import ucab.dsw.entidades.PreguntaEncuesta;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
    @Path("respuestasPreguntaAbierta/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<RespuestasAbiertasResponse> listarRespuestasAbiertas(@PathParam("id") long id) throws NullPointerException {

        /**
         * Este método permite obtener las respuestas abiertas de una pregunta
         *
         * NOTA: Este metodo esta en proceso
         */

        String SQL = null;

        DaoPreguntaEncuesta daoPreguntaEncuesta = new DaoPreguntaEncuesta();
        PreguntaEncuesta preguntaEncuesta = daoPreguntaEncuesta.find(id, PreguntaEncuesta.class);

        try {

            String tipoPregunta = preguntaEncuesta.get_tipoPregunta();

            if(tipoPregunta.equals("Abierta")){

                EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
                EntityManager entitymanager = factory.createEntityManager();

                SQL = "SELECT re._id as id, re._respuestaAbierta as respuestaAbierta FROM Respuesta as re, PreguntaEstudio as pes, PreguntaEncuesta as pe " +
                        "WHERE pe._id = pes._preguntaEncuesta._id and pes._id = re._preguntaEstudio._id and pe._tipoPregunta = :tipoPregunta and pe._id = :id";

                Query query = entitymanager.createQuery(SQL);
                query.setParameter("id", id);
                query.setParameter("tipoPregunta", tipoPregunta);

                List<Object[]> listaRespuestas = query.getResultList();

                List<RespuestasAbiertasResponse> listaRespuestasAbiertas = new ArrayList<>(listaRespuestas.size());

                for (Object[] res: listaRespuestas){

                    listaRespuestasAbiertas.add(new RespuestasAbiertasResponse((long)res[0], (String)res[1]));
                }

                return listaRespuestasAbiertas;

            } else {

                List<RespuestasAbiertasResponse> vacio = new ArrayList<>();
                return vacio;
            }

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }
}
