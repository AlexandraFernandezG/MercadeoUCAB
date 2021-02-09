package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Lugar;

import javax.persistence.*;
import java.util.List;

public class DaoLugar extends Dao<Lugar>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoLugar (){
        super (_handler);
    }

    public List<String> lugarEstadoEstudio(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT lug._nombre FROM SolicitudEstudio as sol, Lugar as lug, Estudio as est, SolicitudEstudioLugar solL " +
                "WHERE (sol._id = est._solicitudEstudio._id and est._id = :id) and (sol._id = solL._SolicitudEstudio._id) " +
                "and (solL._lugar._id = lug._id) and (lug._tipo = 'Estado')";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<String> listaEstudios = query.getResultList();

        return listaEstudios;
    }

    public List<String> lugarMunicipioEstudio(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT lug._nombre FROM SolicitudEstudio as sol, Lugar as lug, Estudio as est, SolicitudEstudioLugar solL " +
                "WHERE (sol._id = est._solicitudEstudio._id and est._id = :id) and (sol._id = solL._SolicitudEstudio._id) " +
                "and (solL._lugar._id = lug._id) and (lug._tipo = 'Municipio')";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<String> listaEstudios = query.getResultList();

        return listaEstudios;
    }

    public List<Long> lugarEstado(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT lug._lugar._id FROM Informacion as info, Lugar as lug " +
                "WHERE (:id = info._usuario._id) and (info._lugar._id = lug._id)";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Long> lugares = query.getResultList();

        return lugares;
    }

    public List<Long> lugarMunicipio(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT lug._id FROM Informacion as info, Lugar as lug " +
                "WHERE (:id = info._usuario._id) and (info._lugar._id = lug._id)";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Long> lugares = query.getResultList();

        return lugares;
    }

    public List<String> nombreLugar(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT lug._nombre FROM Lugar as lug " +
                "WHERE lug._id = :id";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<String> lugares = query.getResultList();

        return lugares;
    }
}
