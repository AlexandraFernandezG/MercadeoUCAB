package ucab.dsw.accesodatos;

import ucab.dsw.entidades.Respuesta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoRespuesta extends Dao<Respuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuesta (){
        super (_handler);
    }

    public List<Long> cantidadVerdaderosPregunta(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL ="SELECT COUNT(r._verdaderoFalso) " +
                "FROM Respuesta as r, PreguntaEstudio as pes, PreguntaEncuesta as pe " +
                "WHERE r._preguntaEstudio._id = pes._id and pes._preguntaEncuesta._id = pe._id and pe._id = :id and r._verdaderoFalso = 'Verdadero'";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Long> verdaderos = query.getResultList();

        return verdaderos;
    }

    public List<Long> cantidadFalsosPregunta(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL ="SELECT COUNT(r._verdaderoFalso) " +
                "FROM Respuesta as r, PreguntaEstudio as pes, PreguntaEncuesta as pe " +
                "WHERE r._preguntaEstudio._id = pes._id and pes._preguntaEncuesta._id = pe._id and pe._id = :id and r._verdaderoFalso = 'Falso'";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Long> falsos = query.getResultList();

        return falsos;
    }

    public List<Long> cantidadSimple(long id, String opcion){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL ="SELECT COUNT(r._respuestaSimple) " +
                "FROM Respuesta as r, PreguntaEstudio as pes, PreguntaEncuesta as pe " +
                "WHERE r._preguntaEstudio._id = pes._id and pes._preguntaEncuesta._id = pe._id and pe._id = :id and r._respuestaSimple = :opcion";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);
        query.setParameter("opcion", opcion);

        List<Long> opcion_list = query.getResultList();

        return opcion_list;
    }

}
