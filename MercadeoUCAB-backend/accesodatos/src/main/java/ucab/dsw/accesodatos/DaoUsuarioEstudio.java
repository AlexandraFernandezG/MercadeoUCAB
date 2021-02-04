package ucab.dsw.accesodatos;

import ucab.dsw.entidades.UsuarioEstudio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DaoUsuarioEstudio extends Dao<UsuarioEstudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoUsuarioEstudio(){
        super (_handler);
    }

    public List<Object[]> listarEstudiosEncuestado(long id){

        String SQL = null;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        SQL = "SELECT DISTINCT e._id as idEstudio, e._nombre as nombre, e._tipoInstrumento as tipoInstrumento, e._observaciones as observaciones, e._fechaInicio as fechaInicio, e._fechaFin as fechaFin, e._estado as estado, e._estatus as estatus " +
                "FROM Estudio as e, Usuario as u, UsuarioEstudio as ue " +
                "WHERE e._id = ue._estudio._id and ue._usuario._id = u._id and u._id = :id";

        Query query = entitymanager.createQuery(SQL);
        query.setParameter("id", id);

        List<Object[]> listaEstudios = query.getResultList();

        return listaEstudios;
    }

    public List<Object[]> listarEncuestadosEstudio(long id){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mercadeoUcabPU");
        EntityManager entitymanager = factory.createEntityManager();

        String sqlQuery = "SELECT DISTINCT u._id, u._nombre, u._codigoRecuperacion, u._correoelectronico, u._estatus " +
                "FROM Usuario as u, Estudio as e, UsuarioEstudio as ue " +
                "WHERE u._id = ue._usuario._id and ue._estudio._id = e._id and e._id = :id";

        Query query = entitymanager.createQuery(sqlQuery);
        query.setParameter("id", id);

        List<Object[]> estudioUsuarioResponseList = query.getResultList();

        return estudioUsuarioResponseList;
    }

}
