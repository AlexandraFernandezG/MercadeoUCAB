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
         * NOTA: Este metodo funciona correctamente.
         */

        String SQL = null;

        try {

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
            EntityManager entitymanager = factory.createEntityManager();

            SQL = "SELECT pe._id as idPregunta, pe._descripcion as descripcion, pe._tipoPregunta as tipoPregunta, pe._estatus as estatus FROM PreguntaEncuesta as pe" +
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
         * NOTA: Esta casi listo, falta detalles.
         */

        String SQL = null;
	    
        // Busca la solicitud de estudio
        DaoSolicitudEstudio daoSolicitudEstudio = new DaoSolicitudEstudio();
        SolicitudEstudio solicitudEstudio = daoSolicitudEstudio.find(id, SolicitudEstudio.class);

        try {

            if(solicitudEstudio != null) {

                String genero = solicitudEstudio.get_genero();
                String estadoCivil = solicitudEstudio.get_estadoCivil();
                int cantidadPersonas = solicitudEstudio.get_cantidadPersonas();

                EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
                EntityManager entitymanager = factory.createEntityManager();

                SQL = "SELECT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estatus as estatus " +
                        "FROM Estudio as e, Usuario as u, Informacion as inf " +
                        "WHERE e._usuario._id = u._id and u._id = inf._usuario._id and " +
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
	@Path("/suggestionsEstudiosEncuestado/{id}")
	@Produces( MediaType.APPLICATION_JSON )
	public List<EstudiosResponse> listarEstudiosCliente(@PathParam("id") long id) throws NullPointerException{
		List<EstudiosResponse> listaEstudiosRecomendados = new ArrayList<>();
		// Para guardar los estudios ya devueltos por las consultas y, así, evitar repeticiones.
		List<Long> idEstudios = new ArrayList<>();
		
		
		// Búsqueda del usuario
		UsuarioAPI servicio = new UsuarioAPI();
		Usuario usuario = servicio.consultarUsuario(id);
		
		if (usuario != null) {
			String rol = usuario.get_rol().get_nombre();
			String estatus = usuario.get_estatus();
			
			// El usuario debe tener rol de Cliente y estar activo.
			boolean esClienteActivo = rol.equals("Cliente")
				                    && estatus.equals("Activo");
			
			if (esClienteActivo) {
				try {
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
					EntityManager entitymanager = factory.createEntityManager();
					
					// Construcción de consultas:
					
				/*
				* 1ra consulta: SOLICITUDES RECIENTES de un mismo cliente
				* Toma los últimos tres (3) estudios solicitados por el cliente
				* */
					String sqlSolicitudReciente =
						"SELECT e._id as idEstudio " +
//							", e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, " +
//							"e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estatus as estatus " +
//							", e._solicitudEstudio._id as idSolicitudEstudio, e._usuario._id as idUsuario " +
						"FROM Usuario  u " +
							"inner join SolicitudEstudio se on u._id = se._usuario._id " +
							"inner join Estudio e on se._id = e._solicitudEstudio._id " +
						"WHERE u._id = :id " +
							"AND e._estatus = 'Activo' " +
						"ORDER BY e._id DESC "
						;
					
					// Ejecución de la consulta
					Query querySolicitudReciente = entitymanager.createQuery(sqlSolicitudReciente);
					// Estableciendo los parámetros de query
					querySolicitudReciente.setParameter("id", id);
					
					// Obteniendo los resultados de la consulta
					List<Long> listaIdEstudiosSolicitudReciente = querySolicitudReciente.setMaxResults(3).getResultList();
					
					// Agregar los estudios de la consulta anterior a atributo que será devuelto
					idEstudios.addAll(listaIdEstudiosSolicitudReciente);
					
				/*
				* 2da consulta: ESTUDIOS MÁS SOLICITADOS POR EL CLIENTE
				* Devuelve los tres estudios más solicitados por el cliente.
				* */
					// Búsqueda de los id de los estudios solicitados previamente por el cliente
					String sqlIdEstudiosMasSolicitados =
						"SELECT e._id as filtro " +
						"FROM Usuario u " +
							"inner join SolicitudEstudio se on u._id = se._usuario._id " +
							"inner join Estudio e on se._id = e._solicitudEstudio._id " +
						"WHERE u._id = :id " +
							"AND e._estatus = 'Activo' " +
							"AND  e._id NOT IN :estudios " +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC "
						;
					
					// Ejecución de la consulta
					Query queryIdEstudiosMasSolicitados = entitymanager.createQuery(sqlIdEstudiosMasSolicitados);
					// Estableciendo los parámetros de query
					queryIdEstudiosMasSolicitados.setParameter("id", id);
					queryIdEstudiosMasSolicitados.setParameter("estudios", idEstudios);
					
					// Obteniendo los resultados de la consulta
					List<Long> listaIdEstudiosMasSolicitados = queryIdEstudiosMasSolicitados.setMaxResults(3).getResultList();
					
					// Agregar los nuevos ID obtenidos de la consulta anterior a la lista de id.
					for (long idEst: listaIdEstudiosMasSolicitados) {
						if (!idEstudios.contains(idEst)) {
							idEstudios.add(idEst);
						}
					}
					
				/*
				 * 3ra consulta: Filtros de búsqueda
				 * Devuelve los estudios más usados según los filtros:
				 * marca, subcategoria, categoria, tipo, presentación
				 * más solicitados en el sistema.
				 * */
					
					// Obtención del id de los filtros
					
					// Marca
					String sqlIdMarcaMasSolicitada =
						"SELECT p._marca._id as filtro " +
						"FROM SolicitudEstudio se " +
							"inner join Producto p on se._producto._id = p._id " +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC"
						;
					
					// Ejecución de la consulta
					Query queryIdMarcaMasSolicitada = entitymanager.createQuery(sqlIdMarcaMasSolicitada);
					
					// Obteniendo los resultados de la consulta
					long idMarcaMasSolicitada = queryIdMarcaMasSolicitada.getFirstResult();
					
					// Subcategoria
					String sqlIdSubcategoriaMasSolicitada =
						"SELECT p._subcategoria._id as filtro " +
						"FROM SolicitudEstudio se " +
							"inner join Producto p on se._producto._id = p._id " +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC"
						;
					
					// Ejecución de la consulta
					Query queryIdSubcategoriaMasSolicitada = entitymanager.createQuery(sqlIdSubcategoriaMasSolicitada);
					
					// Obteniendo los resultados de la consulta
					long idSubcategoriaMasSolicitada = queryIdSubcategoriaMasSolicitada.getFirstResult();
					
					// Categoria
					String sqlIdCategoriaMasSolicitada =
						"SELECT s._categoria._id as filtro " +
						"FROM SolicitudEstudio se " +
							"inner join Producto p on se._producto._id = p._id " +
							"inner join Subcategoria s on p._subcategoria._id = s._id " +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC"
						;
					
					// Ejecución de la consulta
					Query queryIdCategoriaMasSolicitada = entitymanager.createQuery(sqlIdCategoriaMasSolicitada);
					
					// Obteniendo los resultados de la consulta
					long idCategoriaMasSolicitada = queryIdCategoriaMasSolicitada.getFirstResult();
					
					// Tipo
					String sqlIdTipoMasSolicitada =
						"SELECT ppt._tipo._id as filtro " +
						"FROM SolicitudEstudio se " +
							"inner join Producto p on se._producto._id = p._id " +
							"inner join ProductoPresentacionTipo ppt on p._id = ppt._producto._id " +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC"
						;
					
					// Ejecución de la consulta
					Query queryIdTipoMasSolicitada = entitymanager.createQuery(sqlIdTipoMasSolicitada);
					
					// Obteniendo los resultados de la consulta
					long idTipoMasSolicitada = queryIdTipoMasSolicitada.getFirstResult();
					
					// Presentacion
					String sqlIdPresentacionMasSolicitada =
						"SELECT ppt._presentacion._id as filtro " +
						"FROM SolicitudEstudio se " +
							"inner join Producto p on se._producto._id = p._id " +
							"inner join ProductoPresentacionTipo ppt on p._id = ppt._producto._id " +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC"
						;
					
					// Ejecución de la consulta
					Query queryIdPresentacionMasSolicitada = entitymanager.createQuery(sqlIdPresentacionMasSolicitada);
					
					// Obteniendo los resultados de la consulta
					long idPresentacionMasSolicitada = queryIdPresentacionMasSolicitada.getFirstResult();
					
					// Búsqueda de los id de los estudios de acuerdo a los filtros más solicitados
					String sqlIdEstudiosFiltros =
						"SELECT e._id as filtro " +
						"FROM Estudio e " +
							"inner join SolicitudEstudio se on se._id = e._solicitudEstudio._id " +
							"inner join Producto p on se._producto._id = p._id " +
							"inner join Subcategoria s on p._subcategoria._id = s._id " +
							"inner join ProductoPresentacionTipo ppt on p._id = ppt._producto._id " +
						"WHERE e._estatus = 'Activo' " +
							"AND  e._id NOT IN :estudios " +
							"AND (" +
							"p._marca._id = :marca " +
							"OR  p._subcategoria._id = :subcategoria " +
							"OR  s._categoria._id = :categoria " +
							"OR  ppt._tipo._id = :tipo " +
							"OR  ppt._presentacion._id = :presentacion " +
							")" +
						"GROUP BY filtro " +
						"ORDER BY count(filtro) DESC "
						;
					
					// Ejecución de la consulta
					Query queryIdEstudiosFiltros = entitymanager.createQuery(sqlIdEstudiosFiltros);
					// Estableciendo los parámetros de query
					queryIdEstudiosFiltros.setParameter("estudios", idEstudios);
					queryIdEstudiosFiltros.setParameter("marca", idMarcaMasSolicitada);
					queryIdEstudiosFiltros.setParameter("subcategoria", idSubcategoriaMasSolicitada);
					queryIdEstudiosFiltros.setParameter("categoria", idCategoriaMasSolicitada);
					queryIdEstudiosFiltros.setParameter("tipo", idTipoMasSolicitada);
					queryIdEstudiosFiltros.setParameter("presentacion", idPresentacionMasSolicitada);
					
					// Obteniendo los resultados de la consulta
					List<Long> listaIdEstudiosFiltros = queryIdEstudiosFiltros.setMaxResults(9).getResultList();
					
					// Agregar los nuevos ID obtenidos de la consulta anterior a la lista de id.
					for (long idEst: listaIdEstudiosFiltros) {
						if (!idEstudios.contains(idEst)) {
							idEstudios.add(idEst);
						}
					}
					
					// Generar la lista de estudios con los id de estudios obtenidos anteriormente.
					for (long idEst: idEstudios) {
						Estudio listaEstudiosMasSolicitados = new EstudioAPI().consultarEstudio(idEst);
						
						listaEstudiosRecomendados.add(new EstudiosResponse(
							listaEstudiosMasSolicitados.get_id(),
							listaEstudiosMasSolicitados.get_nombre(),
							listaEstudiosMasSolicitados.get_tipoInstrumento(),
							listaEstudiosMasSolicitados.get_fechaInicio(),
							listaEstudiosMasSolicitados.get_fechaFin(),
							listaEstudiosMasSolicitados.get_estatus()));
					}
				} catch (NullPointerException ex) {
					
					String mensaje = ex.getMessage();
					System.out.print(mensaje);
					return null;
				}
			}
		}
		
		return listaEstudiosRecomendados;
	}
}
