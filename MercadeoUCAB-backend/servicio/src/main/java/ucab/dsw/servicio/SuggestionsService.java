package ucab.dsw.servicio;

import ucab.dsw.Response.EstudiosEncuestadoResponse;
import ucab.dsw.Response.EstudiosResponse;
import ucab.dsw.Response.PreguntasResponse;
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
         * NOTA: Este metodo funciona correctamente.
         */

        String SQL = null;

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            SQL = "SELECT DISTINCT pe._id as idPregunta, pe._descripcion as descripcion, pe._tipoPregunta as tipoPregunta, pe._estatus as estatus FROM PreguntaEncuesta as pe" +
                    ", Estudio as e, PreguntaEstudio as pes WHERE e._id = pes._estudio._id and pes._preguntaEncuesta._id = pe._id and pe._subcategoria._id in " +
                    "(SELECT sc._id FROM Subcategoria as sc, Producto as p, SolicitudEstudio as so, Estudio as e WHERE sc._id = p._subcategoria._id and p._id = so._producto._id and so._id = e._solicitudEstudio._id and e._id = :id and pe._id not in " +
                    "(SELECT pes._preguntaEncuesta._id FROM PreguntaEstudio as pes, Estudio as e WHERE pes._estudio._id = e._id and e._id = :id))";

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
         * NOTA: Este metodo funciona correctamente.
         */

        String SQL = null;

        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        try {

            if(solicitudEstudio != null) {

                String genero = solicitudEstudio.get_genero();
                String estadoCivil = solicitudEstudio.get_estadoCivil();
                int cantidadPersonas = solicitudEstudio.get_cantidadPersonas();

                EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
                EntityManager entitymanager = factory.createEntityManager();

                SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estatus as estatus " +
                        "FROM Estudio as e, Informacion as inf, SolicitudEstudio as se, MedioComunicacion as me " +
                        "WHERE e._solicitudEstudio._id = se._id and se._id = me._solicitudEstudio._id and me._informacion._id = inf._id and " +
                        "inf._genero = :genero and " +
                        "(inf._estadoCivil = :estadoCivil or inf._estadoCivil is null) and " +
                        "(inf._cantidadPersonas = :cantidadPersonas or inf._cantidadPersonas is null)";

                Query query = entitymanager.createQuery(SQL);
                query.setParameter("genero", genero);
                query.setParameter("estadoCivil", estadoCivil);
                query.setParameter("cantidadPersonas", cantidadPersonas);

                List<Object[]> listaEstudios = query.getResultList();
                List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

                for (Object[] est : listaEstudios) {

                    listaEstudiosRecomendados.add(new EstudiosResponse((long) est[0], (String) est[1], (String) est[2], (Date) est[3], (Date) est[4], (String) est[5]));
                }

                return listaEstudiosRecomendados;

            } else {

                return null;
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

            if(informacion != null) {

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
                        "se._genero = :genero " +
                        "and (se._estadoCivil = :estadoCivil or se._estadoCivil is null) " +
                        "and (se._cantidadPersonas = :cantidadPersonas or se._cantidadPersonas is null) " +
                        "and (se._edadMinima <= :edad and se._edadMaxima > :edad)";

                Query query = entitymanager.createQuery(SQL);
                query.setParameter("genero", genero);
                query.setParameter("estadoCivil", estadoCivil);
                query.setParameter("cantidadPersonas", cantidadPersonas);
                query.setParameter("edad", edad);

                List<Object[]> listaEstudios = query.getResultList();

                List<EstudiosEncuestadoResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

                for (Object[] est : listaEstudios) {

                    listaEstudiosRecomendados.add(new EstudiosEncuestadoResponse((long) est[0], (String) est[1], (String) est[2], (Date) est[3], (Date) est[4], (String) est[5]));
                }

                return listaEstudiosRecomendados;

            } else {

                return null;
            }

        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }


    }

    @POST
    @Path("/insertarEstudioRecomendado/{idSE}/{idE}/{idU}")
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public void insertarEstudioRecomendado(@PathParam("idSE") long idSE, @PathParam("idE") long idE, @PathParam("idU") long idU) throws Exception{

        /**
         * En este metodo es importante pasar la solicitud de estudio de la busqueda sugerida (SolicitudEstudio)
         * , el estudio recomendado de la lista de recomendados y el id del usuario conectado en el momento
         *
         * Nota: Esta metodo funciona perfectamente.
         */

        try {

            //Encontrar el estudio recomendado
            DaoEstudio daoEstudio = new DaoEstudio();
            Estudio estudio_recomendado = daoEstudio.find(idE, Estudio.class);
            Estudio estudio_nuevo = new Estudio();

            estudio_nuevo.set_nombre(estudio_recomendado.get_nombre());
            estudio_nuevo.set_tipoInstrumento(estudio_recomendado.get_tipoInstrumento());
            estudio_nuevo.set_fechaInicio(estudio_recomendado.get_fechaInicio());
            estudio_nuevo.set_fechaFin(estudio_recomendado.get_fechaFin());
            estudio_nuevo.set_estatus(estudio_recomendado.get_estatus());
            SolicitudEstudio solicitudEstudio = new SolicitudEstudio(idSE);
            estudio_nuevo.set_solicitudEstudio(solicitudEstudio);
            Usuario usuario = new Usuario(idU);
            estudio_nuevo.set_usuario(usuario);

            //Insertar nuevo estudio
            daoEstudio.insert(estudio_nuevo);

            //Obtener las preguntas del estudio recomendado
            ucab.dsw.servicio.PreguntasEstudioAPI servicio = new ucab.dsw.servicio.PreguntasEstudioAPI();

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

        } catch (Exception ex){

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
        }

    }

    /**
     * Listar estudios recomendados para el cliente.
     *
     * Este método busca los estudios recientemente solicitados por un cliente,
     * los estudios más solicitados por él, y los estudios con la marca, la
     * subcategoría, la categoría, el tipo y/o la presentación más solicitadas
     * del sistema.
     *
     * @param id ID del cliente que desea generar la solicitud.
     * @throws NullPointerException Error
     * @return Lista de estudios recomendados para un usuario en específico.
     */
    @GET
    @Path("/suggestionsEstudiosCliente/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public List<EstudiosResponse> listarEstudiosClientes(@PathParam("id") long id) throws NullPointerException{

        /**
         * En este metodo se permite obtener el estudio en base a un cliente.
         *
         * Nota: Este metodo funciona correctamente.
         */

        String SQL = null;

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estatus as estatus " +
                    "FROM Estudio as e, Usuario as u, SolicitudEstudio as se WHERE e._solicitudEstudio._id = se._id and " +
                    "se._usuario._id = u._id and u._id = :id";

            Query query = entitymanager.createQuery(SQL);
            query.setParameter("id", id);

            List<Object[]> listaEstudios = query.getResultList();

            List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>(listaEstudios.size());

            for (Object[] eC: listaEstudios){

                listaEstudiosRecomendados.add(new EstudiosResponse((long)eC[0], (String)eC[1], (String)eC[2], (Date)eC[3], (Date)eC[4], (String)eC[5]));
            }

            return listaEstudiosRecomendados;


        } catch (NullPointerException ex) {

            String mensaje = ex.getMessage();
            System.out.print(mensaje);
            return null;
        }
    }

}
