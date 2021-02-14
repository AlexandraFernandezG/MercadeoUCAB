package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Estudio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio (){
        super (_handler);
    }

    public List<Object[]> listarEstudiosEncuestado(String genero, String estadoCivil, int cantidadPersonas, int edad){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._observaciones as observaciones, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estado as estado, e._estatus as estatus " +
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

        return listaEstudios;
    }

    public List<Object[]> listarEstudiosClientes(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._observaciones as observaciones, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estado as estado, e._estatus as estatus " +
                "FROM Estudio as e, Usuario as u, SolicitudEstudio as se WHERE e._solicitudEstudio._id = se._id and " +
                "se._usuario._id = u._id and u._id = :id";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Object[]> listaEstudios = query.getResultList();

        return listaEstudios;
    }

    public List<Object[]> listarEstudiosAnalista(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._observaciones as observaciones, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estado as estado, e._estatus as estatus, e._solicitudEstudio._id " +
                "FROM Estudio as e, Usuario as u " +
                "WHERE e._usuario._id = u._id and u._id = :id";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Object[]> listaEstudios = query.getResultList();

        return listaEstudios;
    }

    public List<Object[]> detallesEstudio(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT sol._descripcion, pro._nombre, sub._nombre, sol._edadMinima, sol._edadMaxima, sol._genero, " +
                "sol._estadoCivil, ne._descripcion, na._descripcion, ocup._nombre, sol._disponibilidadEnLinea " +
                "FROM SolicitudEstudio as sol, Estudio as est, Producto as pro, Subcategoria as sub, NivelAcademico as na, NivelEconomico as ne, " +
                "Ocupacion as ocup " +
                "WHERE (sol._id = est._solicitudEstudio._id and est._id = :id) and (sol._producto._id = pro._id and pro._subcategoria._id = sub._id) " +
                "and (sol._nivelAcademico._id = na._id) and (sol._nivelEconomico._id = ne._id) and (sol._ocupacion._id = ocup._id)";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Object[]> detalles = query.getResultList();

        return detalles;
    }

    public List<Object[]> consultarEstudioCliente(long idE, long idU){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._observaciones as observaciones, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estado as estado, e._estatus as estatus " +
                "FROM Estudio as e, Usuario as u, SolicitudEstudio as se WHERE e._solicitudEstudio._id = se._id and " +
                "se._usuario._id = u._id and u._id = :idU and e._id = :idE";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("idE", idE);
        query.setParameter("idU", idU);

        List<Object[]> estudio = query.getResultList();

        return estudio;
    }
}
