package ucab.dsw.accesodatos;

import ucab.dsw.entidades.RespuestaPregunta;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoRespuestaPregunta extends Dao<RespuestaPregunta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuestaPregunta(){
        super (_handler);
    }

    public List<Object[]> listaRespuestaEncuesta(long id){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();


        String sqlQuery = "SELECT RP._preguntaEncuesta._id AS id, RP._nombre AS pregunta" +
                " FROM PreguntaEncuesta AS PE, PreguntaEstudio AS PES, RespuestaPregunta AS RP WHERE " +
                "PE._id = PES._preguntaEncuesta._id AND PE._id = RP._preguntaEncuesta._id AND " +
                "PES._estudio._id =:id " +
                "ORDER BY PE._id";

        Query query = entitymanager.createQuery( sqlQuery );
        query.setParameter("id", id);

        List<Object[]> respuestas = query.getResultList();

        return respuestas;
    }

}
