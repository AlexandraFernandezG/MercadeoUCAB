package ucab.dsw.servicio;

import ucab.dsw.Response.RespuestasAbiertasResponse;

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
        
        try {

            return null;


        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }
}
