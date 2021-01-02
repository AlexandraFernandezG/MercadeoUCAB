package ucab.dsw.servicio;

import ucab.dsw.Response.EstudiosEncuestadoResponse;
import ucab.dsw.Response.EstudiosResponse;
import ucab.dsw.Response.PreguntasResponse;
import ucab.dsw.accesodatos.*;
import ucab.dsw.entidades.*;

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

@Path( "/suggestions" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class SuggestionsService extends AplicacionBase {

    @GET
    @Path("suggestionsPreguntasEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<PreguntasResponse> listarPreguntasEstudioRecomendadas(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este método permite obtener preguntas sugeridas a través
         * de un estudio seleccionado con correlación a un subcategoria (Se debe pasar el id del estudio)
         *
         * NOTA: Este método funciona correctamente.
         */

        String SQL = null;

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            SQL = "SELECT pe._id as idPregunta, pe._descripcion as descripcion, pe._tipoPregunta as tipoPregunta, pe._estatus as estatus FROM PreguntaEncuesta as pe" +
                    ", Estudio as e, PreguntaEstudio as pes WHERE e._id = pes._estudio._id and pes._preguntaEncuesta._id = pe._id and pe._subcategoria._id in " +
                    "(SELECT sc._id FROM Subcategoria as sc, Producto as p, SolicitudEstudio as so, Estudio as e WHERE sc._id = p._subcategoria._id and p._id = so._producto._id and so._id = e._solicitudEstudio._id and e._id = :id)";

            Query query = entitymanager.createQuery(SQL);
            query.setParameter("id", id);

            List<Object[]> listaPreguntas = query.getResultList();
            List<PreguntasResponse> listaPreguntasRecomendadas = new ArrayList<>(listaPreguntas.size());

            for (Object[] pre: listaPreguntas){

                listaPreguntasRecomendadas.add(new PreguntasResponse((long)pre[0], (String)pre[1], (String)pre[2], (String)pre[3]));
            }

            return listaPreguntasRecomendadas;

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    @GET
    @Path("/suggestionsEstudio/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<EstudiosResponse> listarEstudiosRecomendados(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este metodo permite obtener los estudios recomendados en base a una
         * solicitud de estudio (Estudios recomendados)
         *
         * NOTA: Casi listo, faltaria mejorar.
         */

        String SQL = null;

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        try {

            if (solicitudEstudio != null) {

                String genero = solicitudEstudio.get_genero();
                String estadoCivil = solicitudEstudio.get_estadoCivil();
                int cantidadPersonas = solicitudEstudio.get_cantidadPersonas();

                EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
                EntityManager entitymanager = factory.createEntityManager();

                SQL = "SELECT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estatus as estatus " +
                        "FROM Estudio as e, Usuario as u, Informacion as inf " +
                        "WHERE e._usuario._id = u._id and u._id = inf._usuario._id and " +
                        "inf._genero = :genero or inf._estadoCivil = :estadoCivil or inf._cantidadPersonas = :cantidadPersonas";

                Query query = entitymanager.createQuery(SQL);
                query.setParameter("genero", genero);
                query.setParameter("estadoCivil", estadoCivil);
                query.setParameter("cantidadPersonas", cantidadPersonas);

                List<Object[]> listaEstudios = query.getResultList();
                List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

                for (Object[] est : listaEstudios) {

                    listaEstudiosRecomendados.add(new EstudiosResponse((long)est[0], (String)est[1], (String)est[2], (Date)est[3], (Date)est[4], (String)est[5]));
                }

                return listaEstudiosRecomendados;

            } else {

                List<EstudiosResponse> vacio = new ArrayList<>();
                return vacio;
            }

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

    }

    //Listar estudios recomendado para el encuestado.
    @GET
    @Path("/suggestionsEstudiosEncuestado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<EstudiosEncuestadoResponse> listarEstudiosEncuestado(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este método filtra los estudios que hagan referencia a la persona
         * que vaya a realizar una encuesta (Estudios recomendados para un encuestado).
         *
         * NOTA: Este método funciona correctamente.
         */

        String SQL = null;

        DaoInformacion daoInformacion = new DaoInformacion();
        Informacion informacion = daoInformacion.find(id, Informacion.class);

        try {

            if (informacion != null) {

                String genero = informacion.get_genero();
                Date fechaNacimiento = informacion.get_fechaNacimiento();
                String estadoCivil = informacion.get_estadoCivil();
                int cantidadPersonas = informacion.get_cantidadPersonas();

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
                int edad = periodo.getYears();

                //Ahora hacemos el query para el match

                EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
                EntityManager entitymanager = factory.createEntityManager();

                SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estatus as estatus " +
                        "FROM Estudio as e, SolicitudEstudio as se " +
                        "WHERE e._solicitudEstudio._id = se._id and " +
                        "se._genero = :genero or se._estadoCivil = :estadoCivil or se._cantidadPersonas = :cantidadPersonas or " +
                        "(se._edadMinima <= :edad and se._edadMaxima > :edad)";

                Query query = entitymanager.createQuery(SQL);
                query.setParameter("genero", genero);
                query.setParameter("estadoCivil", estadoCivil);
                query.setParameter("cantidadPersonas", cantidadPersonas);
                query.setParameter("edad", edad);

                List<Object[]> listaEstudios = query.getResultList();

                List<EstudiosEncuestadoResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

                for (Object[] est : listaEstudios) {

                    listaEstudiosRecomendados.add(new EstudiosEncuestadoResponse((long)est[0], (String)est[1], (String)est[2], (Date)est[3], (Date)est[4], (String)est[5]));
                }

                return listaEstudiosRecomendados;

            } else {

                List<EstudiosEncuestadoResponse> vacio = new ArrayList<>();
                return vacio;
            }

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }


    }

    //Listar estudios recomendado para el cliente.
    @GET
    @Path("/suggestionsEstudiosEncuestado/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<Estudio> listarEstudiosCliente(@PathParam("id") long id) throws NullPointerException{

        /**
         * Este método filtra los estudios que hagan referencia a la persona
         * que realizo una solicitud (Estudios recomendados).
         *
         * NOTA: Hay que hacer
         */

        try {



        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }

        return null;
    }

}
